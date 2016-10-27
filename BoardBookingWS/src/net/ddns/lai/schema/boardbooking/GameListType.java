
package net.ddns.lai.schema.boardbooking;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gameListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gameListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="game" type="{http://www.lai.ddns.net/schema/BoardBooking/}boardgameInfoType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gameListType", propOrder = {
    "game"
})
public class GameListType {

    @XmlElement(required = true)
    protected List<BoardgameInfoType> game;

    /**
     * Gets the value of the game property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the game property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BoardgameInfoType }
     * 
     * 
     */
    public List<BoardgameInfoType> getGame() {
        if (game == null) {
            game = new ArrayList<BoardgameInfoType>();
        }
        return this.game;
    }

}
