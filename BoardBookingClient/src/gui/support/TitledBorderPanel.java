package gui.support;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TitledBorderPanel extends JPanel {
    
    public TitledBorderPanel(String title, Component component) {
        super();
        add(component);
        setBorder(BorderFactory.createTitledBorder(title));
    }
    
    @Override
    public Dimension getMaximumSize() {
        return super.getPreferredSize();
    }
}
