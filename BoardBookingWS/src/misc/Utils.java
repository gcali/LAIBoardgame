package misc;

import javax.xml.ws.WebServiceContext;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.ws.handler.MessageContext;


public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName()); 

    public static HttpSession getSession(WebServiceContext wsContext) {
    	MessageContext messageContext = wsContext.getMessageContext();
    	return ((HttpServletRequest) messageContext.get(MessageContext.SERVLET_REQUEST)).getSession(true); 
    }
    
    public static Object getSessionLock(HttpSession session) {
        return session.getId().intern();
    }
    
    public static Connection getConnection() throws SQLException {
            try {
                Context ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testdb");
                return ds.getConnection(); 
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
    }
    
    public static UUID uuidFromBytes(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        return new UUID(bb.getLong(), bb.getLong());
    }
    
    public static byte[] bytesFromUUID(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
    
    public static void resetConnection(Connection connection) throws SQLException {
        LOG.info("Resetting connection");
        connection.setAutoCommit(true);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        LOG.info("Connection reset");
    }
    
    public static void checkResetConnection(Connection connection) throws SQLException {
        if (connection == null) {
            throw new IllegalArgumentException("Connection is null");
        } else {
            resetConnection(connection);
        }
    }
    
}
