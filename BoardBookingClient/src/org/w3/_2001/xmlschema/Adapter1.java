
package org.w3._2001.xmlschema;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, LocalDate>
{


    public LocalDate unmarshal(String value) {
        return (parser.DateAdapter.parseDate(value));
    }

    public String marshal(LocalDate value) {
        return (parser.DateAdapter.printDate(value));
    }

}
