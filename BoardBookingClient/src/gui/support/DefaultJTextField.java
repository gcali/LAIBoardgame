package gui.support;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DefaultJTextField extends JTextField {
    
    private final static Color INACTIVE_COLOR = Color.GRAY;
    private final static Color ACTIVE_COLOR = Color.BLACK;
    private final String defaultText;
    
    private boolean isDefault = true;
    private boolean first;

    public DefaultJTextField(String defaultValue, int columns) {
        this(defaultValue, columns, false);
    }
    
    public DefaultJTextField(String defaultValue, int columns, boolean first) {
        super(defaultValue, columns);
        
        this.first = first;
        this.defaultText = defaultValue;
        
        setForeground(INACTIVE_COLOR);
        
        addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().length() == 0) {
                    isDefault = true;
                    setText(defaultText);
                    setForeground(INACTIVE_COLOR);
                }
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                setForeground(ACTIVE_COLOR);
                if (isDefault) {
                    setText("");
                    isDefault = false;
                }
            }
        }); 
    }
    
    @Override
    public Dimension getMaximumSize() {
        Dimension old = super.getMaximumSize();
        return new Dimension(old.width, super.getPreferredSize().height);
    }
    
    @Override
    public boolean isFocusable() {
        if (first) {
            first = false;
            return false;
        } else {
            return super.isFocusable(); 
        }
    }
    
    @Override
    public String getText() {
        if (isDefault) {
            return "";
        } else {
            return super.getText();
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("DefaultJTextField");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Utils.setLookAndFeel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        panel.add(new DefaultJTextField("Ciao", 10));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JButton("I'm a button"));
        
        frame.setContentPane(panel);
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        
    }
    
    
    

}
