package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


import misc.Utils;

public class BoardgameData { 
    
    private final static Map<String, Booking> bookingData = new ConcurrentHashMap<>();

    private final static String tableName = "boardgame";
    
    private static final Logger LOG = Logger.getLogger(BoardgameData.class.getName()); 

    public static List<Booking> listBookings(UUID groupID, Connection connection) 
            throws SQLException {
        Utils.checkResetConnection(connection);
        
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT BC.UUID as UUID, Name, StartDate, EndDate " + 
                "FROM Boardgame as BG, Booking as BK, BookingContent as BC " +
                "WHERE BG.ID = BC.Boardgame AND BK.UUID = BC.Booking and BK.UUID = ?"
        )) {
            statement.setBytes(1, Utils.bytesFromUUID(groupID));
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Booking> resultList = new ArrayList<Booking>();

                while (resultSet.next()) {
                    UUID bookingID = Utils.uuidFromBytes(resultSet.getBytes("UUID"));
                    String gameName = resultSet.getString("Name");
                    Date startDate = resultSet.getDate("StartDate");
                    Date endDate = resultSet.getDate("EndDate");
                    Booking element = new Booking(
                        bookingID, 
                        gameName, 
                        null, 
                        new DateRange(startDate.toLocalDate(), endDate.toLocalDate())
                    );
                    resultList.add(element);
                }
                
                return resultList; 
            }
        }
    }
    
    public static List<BookingGroup> listBookingGroups(String user, Connection connection) 
            throws SQLException {
        Utils.checkResetConnection(connection);
        
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT UUID, Paid from Booking where UserID = ?"
        ))  {
            statement.setString(1, user); 
            try (ResultSet resultSet = statement.executeQuery()) {
                List<BookingGroup> resultList = new ArrayList<BookingGroup>();
                
                while (resultSet.next()) {
                    boolean paid = resultSet.getBoolean("Paid");
                    UUID uuid = Utils.uuidFromBytes(resultSet.getBytes("UUID"));
                    resultList.add(new BookingGroup(user, uuid, paid));
                }
                
                return resultList; 
            }
            
        }
    }
    
    public static List<Boardgame> listBoardgames(Connection connection) throws SQLException {
        Utils.checkResetConnection(connection);
        
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT Name, Price from Boardgame"
        )) {
            try (ResultSet resultSet = statement.executeQuery())  {
                List<Boardgame> resultList = new ArrayList<Boardgame>();
                
                while (resultSet.next()) {
                    resultList.add(new Boardgame(resultSet.getString("Name"), resultSet.getInt("Price")));
                }
                
                return resultList; 
            } 
        } 
    }
    
    public static void addBoardgame(Boardgame boardgame, Connection connection) 
            throws SQLException {
        Utils.checkResetConnection(connection);
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into " + tableName + " (Name, Price) " +
                                    "values (?, ?)")) {
            preparedStatement.setString(1, boardgame.getName());
            preparedStatement.setInt(2, boardgame.getEncodedPrice());
            preparedStatement.executeUpdate(); 
        }
    }
    
    public static PaymentData getPaymentData(String user, Connection connection) throws SQLException {
        
        Utils.checkResetConnection(connection);
        
        try (PreparedStatement statement = connection.prepareStatement(
                "select BK.UUID as UUID, SUM(Price * ({fn TIMESTAMPDIFF(SQL_TSI_DAY, STARTDATE, ENDDATE)} + 1)) as Price " +
                "from BookingContent as BC, Boardgame as BG, Booking as BK " +
                "where BC.Boardgame = BG.ID AND BK.UserID = ? AND BC.Booking = BK.UUID AND BK.Paid = FALSE " +
                "group by BK.UUID"
        )) {
            statement.setString(1, user);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                PaymentData paymentData = 
                        new PaymentData(result.getInt("Price"), Utils.uuidFromBytes(result.getBytes("UUID")));
                return paymentData;
            } else {
                return null;
            } 
        } 
    }

    public static boolean abortBooking(UUID id, Connection connection) throws SQLException {
        Utils.checkResetConnection(connection);
        try (PreparedStatement statement = connection.prepareStatement(
                "delete from BookingContent " +
                "where UUID in ( " +
                "SELECT BC.UUID " +
                "FROM Booking as B, BookingContent as BC " +
                "WHERE Paid = FALSE AND BC.Booking = B.UUID AND BC.UUID = ? )"
        )) {
            statement.setBytes(1, Utils.bytesFromUUID(id));
            int affected = statement.executeUpdate();
            LOG.info("Affected: " + affected);
            if (affected == 0) {
                return false;
            } else {
                return true;
            }
        }
    }
    
    public static Booking addBooking(BookingRequest booking, Connection connection) 
            throws SQLException {
        Utils.checkResetConnection(connection);
        boolean shouldCommit = false;
        try {
            connection.setAutoCommit(false);
            LOG.info("Set autoCommit to false");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            LOG.info("Set transaction isolation level");

            UUID referencedUuid = getUUID(booking.getUser(), connection);
            LOG.info("Got uuid");
            int gameID;
            try {
                gameID = getBoardgameID(booking.getName(), connection);
                LOG.info("Got boardgame id");
            } catch (InvalidDataException e) {
                throw new SQLException(e);
            }
            Date baseDate = Date.valueOf(booking.getRange().getBase());
            Date endDate = Date.valueOf(booking.getRange().getEnd());
            LOG.info("Got dates");
            
            if (baseDate.after(endDate)) {
                LOG.info("Invalid dates");
                throw new SQLException("Invalid data");
            }
            
            try (PreparedStatement statement = connection.prepareStatement(
                    "Insert into BookingContent (UUID, Boardgame, Booking, StartDate, EndDate) " +
                    "values (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                UUID singleBookingUuid = UUID.randomUUID();
                statement.setBytes(1, Utils.bytesFromUUID(singleBookingUuid));
                statement.setInt(2, gameID);
                statement.setBytes(3, Utils.bytesFromUUID(referencedUuid));
                statement.setDate(4, baseDate);
                statement.setDate(5, endDate);
                
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("addBooking failed, no row affected");
                } 
                try (ResultSet set = statement.getGeneratedKeys()) {
                    if (set.next()) {
                        shouldCommit = true;
                        return new Booking(singleBookingUuid, booking);
                    } else {
                        throw new SQLException("addBooking failed, no id");
                    }
                }
            }

        } finally {
            if (shouldCommit) {
                connection.commit(); 
            } else {
                connection.rollback();
            }
            Utils.resetConnection(connection);
        }
    }
    
    private static int getBoardgameID(String name, Connection connection) 
            throws InvalidDataException, SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "Select ID from Boardgame where Name = ?")) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    return result.getInt("ID");
                }
                throw new InvalidDataException("Missing " + name);
            }
        }
        
    }
    
    private static UUID getUUID(String user, Connection connection) 
            throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "Select UUID from Booking where UserID = ? and Paid = FALSE"
        )) {
            statement.setString(1, user);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    return Utils.uuidFromBytes(result.getBytes("UUID"));
                } 
            }
        }
        //if no existing valid uuid has been found
        UUID uuid = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(
                "Insert into Booking (UserID, Paid, UUID) " +
                "values (?, ?, ?)"
        )) {
            statement.setString(1, user);
            statement.setBoolean(2, false);
            statement.setBytes(3, Utils.bytesFromUUID(uuid)); 
            statement.executeUpdate();
        }
        return uuid;
    }
    
    public static void main(String[] args) throws SQLException {
        String driver = "org.apache.derby.jdbc.ClientDriver";
         // the database name  
        String dbName="laidb";
         // define the Derby connection URL to use 
        String connectionURL = "jdbc:derby://localhost:1527/" + dbName + ";user=gio;password=temp";

        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            List<Booking> result = listBookings(UUID.fromString("062b47cb-2f43-43ae-99d4-12ed25bacf90"), connection);
            System.out.println("Printing results...");
            for (Booking b : result) {
                System.out.println(b.getName());
            }
        }
    }
}
