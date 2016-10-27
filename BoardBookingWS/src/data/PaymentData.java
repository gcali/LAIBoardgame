package data;

import java.util.UUID;

public class PaymentData {

    private int price;
    private UUID uuid;

    public PaymentData(int price, UUID uuid) {
        
        this.price = price;
        this.uuid = uuid; 
        
    }
    
    public int getPrice() {
        return price;
    }
    
    public UUID getUuid() {
        return uuid;
    }

}
