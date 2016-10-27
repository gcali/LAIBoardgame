
package net.ddns.lai.schema.boardbooking;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for successOrFailure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="successOrFailure"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="success"/&gt;
 *     &lt;enumeration value="failure"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "successOrFailure")
@XmlEnum
public enum SuccessOrFailure {

    @XmlEnumValue("success")
    SUCCESS("success"),
    @XmlEnumValue("failure")
    FAILURE("failure");
    private final String value;

    SuccessOrFailure(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SuccessOrFailure fromValue(String v) {
        for (SuccessOrFailure c: SuccessOrFailure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
