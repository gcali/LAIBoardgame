
package net.ddns.lai.boardpayment;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2016-08-27T21:17:22.501+02:00
 * Generated source version: 3.1.6
 * 
 */
public final class BoardPayment_BoardPayment_Client {

    private static final QName SERVICE_NAME = new QName("http://www.lai.ddns.net/BoardPayment/", "BoardPayment");

    private BoardPayment_BoardPayment_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = BoardPayment_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        BoardPayment_Service ss = new BoardPayment_Service(wsdlURL, SERVICE_NAME);
        BoardPayment port = ss.getBoardPayment();  
        
        {
        System.out.println("Invoking requestPayment...");
        net.ddns.lai.schema.boardpayment.RequestPaymentType _requestPayment_payment = new net.ddns.lai.schema.boardpayment.RequestPaymentType();
        _requestPayment_payment.setId("Id2005859538");
        _requestPayment_payment.setPrice(1818182843);
        net.ddns.lai.schema.boardpayment.SuccessOrFailure _requestPayment__return = port.requestPayment(_requestPayment_payment);
        System.out.println("requestPayment.result=" + _requestPayment__return);


        }

        System.exit(0);
    }

}
