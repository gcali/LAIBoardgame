package misc;

import javax.xml.ws.BindingProvider;

public class Utils {
    
    public static void setMaintainSession(BindingProvider provider, boolean value) {
        provider.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, value);
    }

    
    public static String centPriceToString(int centPrice) {
        return String.format("%d.%02d", centPrice/100, centPrice%100);
    }

    public static int parsePrice(String text) throws WrongInputException {
        String[] result = text.split("\\.");
        try {
            switch(result.length) {
            case 0:
                throw new WrongInputException();
            case 1:
                return Integer.parseInt(text, 10) * 100;
            case 2:
                int integer = 0;
                if (!"".equals(result[0])) {
                    integer = Integer.parseInt(result[0], 10);
                } 
                int decimal = Integer.parseInt(result[1], 10);
                if (decimal < 0 || decimal >= 100 || integer < 0) {
                    throw new WrongInputException();
                } else {
                    return integer * 100 + decimal;
                }
            default:
                throw new WrongInputException();
            }
        } catch (NumberFormatException e) {
            throw new WrongInputException(e);
        }
    }
    
}
