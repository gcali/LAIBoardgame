package data;


public class Boardgame {

		
    private final String name; 
    private final int encodedPrice;
    
    public Boardgame(String name, int encodedPrice) {
        this.name = name;
        this.encodedPrice = encodedPrice;
    }
    
    public Boardgame(String name, int integerPrice, int fractionalPrice) {
        this(name, integerPrice * 100 + fractionalPrice);
    }

    public String getName() {
        return name;
    }

    public int getEncodedPrice() {
        return encodedPrice;
    } 
    
    public int getIntegerPrice() {
        return encodedPrice / 100;
    }
    
    public int getFractionalPrice() {
        return encodedPrice % 100;
    }

}
