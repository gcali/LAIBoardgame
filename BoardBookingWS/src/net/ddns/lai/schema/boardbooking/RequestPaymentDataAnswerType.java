
package net.ddns.lai.schema.boardbooking;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestPaymentDataAnswerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestPaymentDataAnswerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="failure"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="success"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="price" type="{http://www.lai.ddns.net/schema/BoardBooking/}priceType"/&gt;
 *                   &lt;element name="bookingID" type="{http://www.lai.ddns.net/schema/BoardBooking/}bookingID"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestPaymentDataAnswerType", propOrder = {
    "failure",
    "success"
})
public class RequestPaymentDataAnswerType {

    protected RequestPaymentDataAnswerType.Failure failure;
    protected RequestPaymentDataAnswerType.Success success;

    /**
     * Gets the value of the failure property.
     * 
     * @return
     *     possible object is
     *     {@link RequestPaymentDataAnswerType.Failure }
     *     
     */
    public RequestPaymentDataAnswerType.Failure getFailure() {
        return failure;
    }

    /**
     * Sets the value of the failure property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestPaymentDataAnswerType.Failure }
     *     
     */
    public void setFailure(RequestPaymentDataAnswerType.Failure value) {
        this.failure = value;
    }

    /**
     * Gets the value of the success property.
     * 
     * @return
     *     possible object is
     *     {@link RequestPaymentDataAnswerType.Success }
     *     
     */
    public RequestPaymentDataAnswerType.Success getSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestPaymentDataAnswerType.Success }
     *     
     */
    public void setSuccess(RequestPaymentDataAnswerType.Success value) {
        this.success = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Failure {


    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;all&gt;
     *         &lt;element name="price" type="{http://www.lai.ddns.net/schema/BoardBooking/}priceType"/&gt;
     *         &lt;element name="bookingID" type="{http://www.lai.ddns.net/schema/BoardBooking/}bookingID"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Success {

        protected int price;
        @XmlElement(required = true)
        protected String bookingID;

        /**
         * Gets the value of the price property.
         * 
         */
        public int getPrice() {
            return price;
        }

        /**
         * Sets the value of the price property.
         * 
         */
        public void setPrice(int value) {
            this.price = value;
        }

        /**
         * Gets the value of the bookingID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBookingID() {
            return bookingID;
        }

        /**
         * Sets the value of the bookingID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBookingID(String value) {
            this.bookingID = value;
        }

    }

}
