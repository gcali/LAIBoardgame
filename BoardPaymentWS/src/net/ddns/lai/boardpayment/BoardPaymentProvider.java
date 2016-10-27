package net.ddns.lai.boardpayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.Db;
import utils.Xml;

@WebServiceProvider()
@ServiceMode(value=Service.Mode.MESSAGE)
public class BoardPaymentProvider implements Provider<SOAPMessage> {
    
    private final static String SCHEMA_PREFIX = "http://www.lai.ddns.net/schema/BoardPayment/";
    private final static String WSDL_PREFIX = "http://www.lai.ddns.net/BoardPayment/";
    
    private final static String SUCCESS_ANSWER = "success";
    private final static String FAILURE_ANSWER = "failure";
    
    private final static Logger LOG = Logger.getLogger(BoardPaymentProvider.class.getName());
    
    private static Node getPayment(SOAPBody body) {
        Node root = body.getFirstChild();
        if ("payment".equals(root.getLocalName()) && WSDL_PREFIX.equals(root.getNamespaceURI())) {
            return root;
        } else {
            return null;
        }
    }

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        
        try {
            SOAPBody soapBody = request.getSOAPBody();
            LOG.info("First child: " + soapBody.getFirstChild().getNodeName() + " <ns:" + soapBody.getFirstChild().getNamespaceURI() + ">");
            Node root = getPayment(soapBody);
            if (root == null) {
                LOG.info("Invalid SOAPMessage");
                    return getAnswer(FAILURE_ANSWER);
            } else {
                try {
                    UUID id = getId(root);
                    int price = getPrice(root);
                    return handleRequest(id, price); 
                } catch (IllegalArgumentException e) {
                    LOG.info("Invalid body");
                    return getAnswer(FAILURE_ANSWER);
                }
            }
        } catch (SOAPException e) {
            LOG.info("Invalid SOAPMessage"); 
            return getAnswer(FAILURE_ANSWER);
        } 
        
    }

    private SOAPMessage handleRequest(UUID id, Integer price) {
        LOG.info("Requested payment for " + id + " at " + price);
        try (Connection connection = Db.getConnection()) {
            try {
                setPaid(id, price, connection);
                return getAnswer(SUCCESS_ANSWER);
            } catch (TransactionFailedException e) {
                return getAnswer(FAILURE_ANSWER);
            }
        } catch (SQLException e) {
            return getAnswer(FAILURE_ANSWER);
        } 
    }
    
    private void setPaid(UUID id, Integer price, Connection connection) throws TransactionFailedException {
        boolean shouldCommit = false;
        try {
            LOG.info("Starting settings...");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            LOG.info("Serialized set");
            PreparedStatement statement = connection.prepareStatement(
                "select SUM(Price * ({fn TIMESTAMPDIFF(SQL_TSI_DAY, STARTDATE, ENDDATE)} + 1)) as Price " +
                "from BookingContent as BC, Boardgame as BG, Booking as BK " +
                "where BC.Boardgame = BG.ID AND BC.Booking = BK.UUID AND BK.UUID = ? AND BK.Paid = FALSE " +
                "group by BK.UUID"
            );
            byte[] idBytes = Db.bytesFromUUID(id);
            statement.setBytes(1, idBytes);
            LOG.info("Executing first query...");
            ResultSet result = statement.executeQuery();
            LOG.info("Query executed!");
            if (result.next()) {
                LOG.info("Got answer!");
                int queryPrice = result.getInt("Price");
                if (queryPrice != price) {
                    LOG.info("Price didn't check!");
                    throw new TransactionFailedException();
                } else {
                    LOG.info("Price ok; waiting to simulate paying time");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    PreparedStatement update = connection.prepareStatement(
                            "update Booking " +
                            "set Paid = TRUE " +
                            "where UUID = ?"
                    );
                    update.setBytes(1, idBytes);
                    int howMany = update.executeUpdate();
                    if (howMany == 1) {
                        shouldCommit = true;
                        LOG.info("All done!");
                    } else {
                        LOG.info("Too many updates, aborting");
                    }
                }
            } else {
                LOG.info("No results :(");
                throw new TransactionFailedException();
            } 
        } catch (SQLException e) {
            LOG.info("SQLException");
            throw new TransactionFailedException();
        } finally {
            try {
                if (shouldCommit) {
                    LOG.info("Committing");
                    connection.commit();
                } else {
                    LOG.info("Rollback!");
                    connection.rollback();
                }
                connection.setAutoCommit(true);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            } catch (SQLException e) {
                LOG.info("SQLException");
            }
        }
    }

    private static SOAPMessage getAnswer(String answer) {

        try {
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage message = factory.createMessage();
            SOAPBody body = message.getSOAPBody();
            SOAPBodyElement bodyElement = body.addBodyElement(new QName(WSDL_PREFIX, "paymentResult"));
            bodyElement.setTextContent(answer);
            return message;
        } catch (SOAPException e) {
            LOG.info("Couldn't create answer");
            return null;
        }
    }

    private static int getPrice(Node root) {
        for (Node node : Xml.asIterable(root.getChildNodes())) {
            if ("price".equals(node.getLocalName()) && SCHEMA_PREFIX.equals(node.getNamespaceURI())) {
                try {
                    return Integer.parseInt(node.getTextContent());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        throw new IllegalArgumentException("Price absent");
    }

    private static UUID getId(Node root) {
        for (Node node : Xml.asIterable(root.getChildNodes())) {
            if ("id".equals(node.getLocalName()) && SCHEMA_PREFIX.equals(node.getNamespaceURI())) {
                return UUID.fromString(node.getTextContent());
            }
        }
        throw new IllegalArgumentException("ID absent");
    }
    
}
