
package net.ddns.lai.boardpayment;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import net.ddns.lai.schema.boardpayment.RequestPaymentType;
import net.ddns.lai.schema.boardpayment.SuccessOrFailure;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.ddns.lai.boardpayment package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Payment_QNAME = new QName("http://www.lai.ddns.net/BoardPayment/", "payment");
    private final static QName _PaymentResult_QNAME = new QName("http://www.lai.ddns.net/BoardPayment/", "paymentResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.ddns.lai.boardpayment
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestPaymentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardPayment/", name = "payment")
    public JAXBElement<RequestPaymentType> createPayment(RequestPaymentType value) {
        return new JAXBElement<RequestPaymentType>(_Payment_QNAME, RequestPaymentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuccessOrFailure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardPayment/", name = "paymentResult")
    public JAXBElement<SuccessOrFailure> createPaymentResult(SuccessOrFailure value) {
        return new JAXBElement<SuccessOrFailure>(_PaymentResult_QNAME, SuccessOrFailure.class, null, value);
    }

}
