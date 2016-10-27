package net.ddns.lai.boardbooking;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2016-08-29T01:17:46.716+02:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://www.lai.ddns.net/BoardBooking/", name = "BoardBooking")
@XmlSeeAlso({net.ddns.lai.schema.boardbooking.ObjectFactory.class, ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BoardBooking {

    @WebMethod(operationName = "AbortBoardgameBooking", action = "AbortBoardgameBooking")
    @WebResult(name = "bookingAbortResult", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "bookingAbortResult")
    public net.ddns.lai.schema.boardbooking.SuccessOrFailure abortBoardgameBooking(
        @WebParam(partName = "bookingAbort", name = "bookingAbort", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        java.lang.String bookingAbort
    );

    @WebMethod(operationName = "BookingDetails", action = "BookingDetails")
    @WebResult(name = "requestDetailsResult", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "requestDetailsResult")
    public net.ddns.lai.schema.boardbooking.BookingDetailsListType bookingDetails(
        @WebParam(partName = "requestDetails", name = "requestDetails", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        java.lang.String requestDetails
    );

    @WebMethod(operationName = "RequestPaymentData", action = "RequestPaymentData")
    @WebResult(name = "paymentResult", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "paymentResult")
    public net.ddns.lai.schema.boardbooking.RequestPaymentDataAnswerType requestPaymentData(
        @WebParam(partName = "payment", name = "payment", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        net.ddns.lai.schema.boardbooking.EmptyType payment
    );

    @WebMethod(operationName = "OpenSession", action = "OpenSession")
    @WebResult(name = "openSessionResult", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "openSessionResult")
    public net.ddns.lai.schema.boardbooking.SuccessOrFailure openSession(
        @WebParam(partName = "openSession", name = "openSession", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        net.ddns.lai.schema.boardbooking.OpenSessionType openSession
    );

    @WebMethod(operationName = "ListGames", action = "ListGames")
    @WebResult(name = "listGamesResult", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "listGamesResult")
    public net.ddns.lai.schema.boardbooking.GameListType listGames(
        @WebParam(partName = "listGames", name = "listGames", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        net.ddns.lai.schema.boardbooking.EmptyType listGames
    );

    @WebMethod(operationName = "BookingGroups", action = "BookingGroups")
    @WebResult(name = "requestBookingGroupsResult", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "requestBookingGroupsResult")
    public net.ddns.lai.schema.boardbooking.BookingGroupListType bookingGroups(
        @WebParam(partName = "requestBookingGroups", name = "requestBookingGroups", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        net.ddns.lai.schema.boardbooking.EmptyType requestBookingGroups
    );

    @WebMethod(operationName = "BookBoardgame", action = "BookBoardgame")
    @WebResult(name = "bookingStatus", targetNamespace = "http://www.lai.ddns.net/BoardBooking/", partName = "status")
    public net.ddns.lai.schema.boardbooking.BoardgameBookingAnswerType bookBoardgame(
        @WebParam(partName = "bookingData", name = "bookingData", targetNamespace = "http://www.lai.ddns.net/BoardBooking/")
        net.ddns.lai.schema.boardbooking.BookingDataType bookingData
    );
}