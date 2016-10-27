package data;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {

    private final String name;
    private final String user;
    private final UUID id;
    private final DateRange range;
    
    protected Booking(UUID id, String name, String user, DateRange range) { 
        this.id = id;
        this.name = name;
        this.user = user;
        this.range = range;
    }
    
    protected Booking(UUID id, BookingRequest request) {
        this(id, request.getName(), request.getUser(), request.getRange());
    }
    
    public UUID getId() {
        return id;
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
