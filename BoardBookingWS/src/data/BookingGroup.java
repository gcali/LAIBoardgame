package data;

import java.util.UUID;

public class BookingGroup {

    private final String user;
    private final UUID uuid;
    private final boolean paid;

    public BookingGroup(String user, UUID uuid, boolean paid) {
        this.user = user;
        this.uuid = uuid;
        this.paid = paid;
    }

    public String getUser() {
        return user;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isPaid() {
        return paid;
    }

}
