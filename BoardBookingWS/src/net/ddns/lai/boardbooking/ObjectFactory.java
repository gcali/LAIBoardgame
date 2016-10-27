
package net.ddns.lai.boardbooking;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import net.ddns.lai.schema.boardbooking.BoardgameBookingAnswerType;
import net.ddns.lai.schema.boardbooking.BookingDataType;
import net.ddns.lai.schema.boardbooking.BookingDetailsListType;
import net.ddns.lai.schema.boardbooking.BookingGroupListType;
import net.ddns.lai.schema.boardbooking.EmptyType;
import net.ddns.lai.schema.boardbooking.GameListType;
import net.ddns.lai.schema.boardbooking.OpenSessionType;
import net.ddns.lai.schema.boardbooking.RequestPaymentDataAnswerType;
import net.ddns.lai.schema.boardbooking.SuccessOrFailure;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.ddns.lai.boardbooking package. 
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

    private final static QName _BookingData_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "bookingData");
    private final static QName _BookingStatus_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "bookingStatus");
    private final static QName _BookingAbort_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "bookingAbort");
    private final static QName _BookingAbortResult_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "bookingAbortResult");
    private final static QName _OpenSession_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "openSession");
    private final static QName _OpenSessionResult_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "openSessionResult");
    private final static QName _Payment_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "payment");
    private final static QName _PaymentResult_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "paymentResult");
    private final static QName _ListGames_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "listGames");
    private final static QName _ListGamesResult_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "listGamesResult");
    private final static QName _RequestDetails_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "requestDetails");
    private final static QName _RequestDetailsResult_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "requestDetailsResult");
    private final static QName _RequestBookingGroups_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "requestBookingGroups");
    private final static QName _RequestBookingGroupsResult_QNAME = new QName("http://www.lai.ddns.net/BoardBooking/", "requestBookingGroupsResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.ddns.lai.boardbooking
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "bookingData")
    public JAXBElement<BookingDataType> createBookingData(BookingDataType value) {
        return new JAXBElement<BookingDataType>(_BookingData_QNAME, BookingDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoardgameBookingAnswerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "bookingStatus")
    public JAXBElement<BoardgameBookingAnswerType> createBookingStatus(BoardgameBookingAnswerType value) {
        return new JAXBElement<BoardgameBookingAnswerType>(_BookingStatus_QNAME, BoardgameBookingAnswerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "bookingAbort")
    public JAXBElement<String> createBookingAbort(String value) {
        return new JAXBElement<String>(_BookingAbort_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuccessOrFailure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "bookingAbortResult")
    public JAXBElement<SuccessOrFailure> createBookingAbortResult(SuccessOrFailure value) {
        return new JAXBElement<SuccessOrFailure>(_BookingAbortResult_QNAME, SuccessOrFailure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OpenSessionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "openSession")
    public JAXBElement<OpenSessionType> createOpenSession(OpenSessionType value) {
        return new JAXBElement<OpenSessionType>(_OpenSession_QNAME, OpenSessionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuccessOrFailure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "openSessionResult")
    public JAXBElement<SuccessOrFailure> createOpenSessionResult(SuccessOrFailure value) {
        return new JAXBElement<SuccessOrFailure>(_OpenSessionResult_QNAME, SuccessOrFailure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "payment")
    public JAXBElement<EmptyType> createPayment(EmptyType value) {
        return new JAXBElement<EmptyType>(_Payment_QNAME, EmptyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestPaymentDataAnswerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "paymentResult")
    public JAXBElement<RequestPaymentDataAnswerType> createPaymentResult(RequestPaymentDataAnswerType value) {
        return new JAXBElement<RequestPaymentDataAnswerType>(_PaymentResult_QNAME, RequestPaymentDataAnswerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "listGames")
    public JAXBElement<EmptyType> createListGames(EmptyType value) {
        return new JAXBElement<EmptyType>(_ListGames_QNAME, EmptyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "listGamesResult")
    public JAXBElement<GameListType> createListGamesResult(GameListType value) {
        return new JAXBElement<GameListType>(_ListGamesResult_QNAME, GameListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "requestDetails")
    public JAXBElement<String> createRequestDetails(String value) {
        return new JAXBElement<String>(_RequestDetails_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingDetailsListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "requestDetailsResult")
    public JAXBElement<BookingDetailsListType> createRequestDetailsResult(BookingDetailsListType value) {
        return new JAXBElement<BookingDetailsListType>(_RequestDetailsResult_QNAME, BookingDetailsListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "requestBookingGroups")
    public JAXBElement<EmptyType> createRequestBookingGroups(EmptyType value) {
        return new JAXBElement<EmptyType>(_RequestBookingGroups_QNAME, EmptyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingGroupListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lai.ddns.net/BoardBooking/", name = "requestBookingGroupsResult")
    public JAXBElement<BookingGroupListType> createRequestBookingGroupsResult(BookingGroupListType value) {
        return new JAXBElement<BookingGroupListType>(_RequestBookingGroupsResult_QNAME, BookingGroupListType.class, null, value);
    }

}
