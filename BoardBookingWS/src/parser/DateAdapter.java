package parser;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

public class DateAdapter {
    public static LocalDate parseDate(String s) {
        return DatatypeConverter.parseDate(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      }
      public static String printDate(LocalDate dt) {
          return dt.toString(); 
      }


}
