package gui.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTextField extends DefaultJTextField {
    
    public DateTextField(int columns) {
        super("yyyy-MM-dd", columns);
    }
    
    public LocalDate getDate() {
        String text = getText();
        try {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-M-d"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
   

}
