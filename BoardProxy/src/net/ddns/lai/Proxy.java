package net.ddns.lai;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Servlet implementation class Proxy
 */
@WebServlet("/Proxy")
public class Proxy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String HEADER_ACTION = "SOAPAction";
	
	private static final Logger LOG = Logger.getLogger(Proxy.class.getName());
	
//	private static final String addressPayment = "http://lai.ddns.net:8080/BoardPayment/services/BoardPayment";
	private static final String addressPayment = "http://localhost:8080/BoardPayment/services/BoardPayment";
	
//	private static final String addressBooking = "http://lai.ddns.net:8080/BoardBooking/services/BoardBooking";
	private static final String addressBooking = "http://localhost:8080/BoardBooking/services/BoardBooking";
	
	private static final Set<String> paymentActions;
	private static final Set<String> bookingActions;
	static {
        String[] paymentActionsArray = {
            "\"RequestPayment\""
        };
	    paymentActions = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(paymentActionsArray)));

        String[] bookingActionsArray = {
            "\"OpenSession\"",
            "\"BookBoardgame\"",
            "\"AbortBoardgameBooking\"",
            "\"RequestPaymentData\"",
            "\"ListGames\"",
            "\"BookingDetails\"",
            "\"BookingGroups\"" 
        };
        
        bookingActions = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(bookingActionsArray)));
	}
	
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    
	    for (String bookingAction : bookingActions) {
	        LOG.info("Booking: " + bookingAction);
	    }
	    
	    String action = request.getHeader(HEADER_ACTION);
	    LOG.info("Action: " + action);
	    Enumeration<String> requestHeaders = request.getHeaderNames(); 
	    MimeHeaders soapHeaders = new MimeHeaders();
	    while (requestHeaders.hasMoreElements()) {
	        String name = requestHeaders.nextElement();
	        soapHeaders.addHeader(name, request.getHeader(name));
	    }
        try {
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage soapMessage = factory.createMessage(soapHeaders, request.getInputStream());
            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            String url;
            if (paymentActions.contains(action)) {
                url = addressPayment;
            } else if (bookingActions.contains(action)) {
                url = addressBooking;
            } else {
                LOG.info("Invalid action");
                url = addressBooking;
            }
            SOAPMessage soapResult = connection.call(soapMessage, url);
            response.reset();
            MimeHeaders resultHeaders = soapResult.getMimeHeaders();
            
            Iterator<MimeHeader> headerIterator = resultHeaders.getAllHeaders();
            while (headerIterator.hasNext()) {
                MimeHeader header = headerIterator.next();
                response.addHeader(header.getName(), header.getValue());
            }
            
            OutputStream responseStream = response.getOutputStream();
            soapResult.writeTo(responseStream);
            responseStream.flush();
            } catch (SOAPException e) {
                e.printStackTrace();
            } 

	} 

}
