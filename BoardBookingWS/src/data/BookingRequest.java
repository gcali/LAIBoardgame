package data;

import java.time.LocalDate;

public class BookingRequest {

    private final String name;
    private final String user;
    private final DateRange range;
    
    public BookingRequest(String name, String user, DateRange range) { 
        this.name = name;
        this.user = user;
        this.range = range;
    }
    
    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }
    
    public DateRange getRange() {
        return range;
    }


} 
