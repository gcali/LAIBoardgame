package utils;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Xml {

    public static Iterable<Node> asIterable(NodeList list) {
        return new Iterable<Node>() {
            public Iterator<Node> iterator() {
                return new Iterator<Node>() { 
                    private int nextElement = 0;
                    @Override
                    public boolean hasNext() {
                        return nextElement < list.getLength();
                    }

                    @Override
                    public Node next() {
                        return list.item(nextElement++);
                    }
                };
                
            }
        };
    }

}
