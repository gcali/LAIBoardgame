package gui.login;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gui.support.DefaultJTextField;
import gui.support.TitledBorderPanel;
import gui.support.Utils;


public class Login extends JPanel {
    
    private JTextField userField;
    private JButton chooseButton;
    private JButton closeButton;
    
    public Login() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        userField = new DefaultJTextField("User", 20, true);
        chooseButton = new JButton("Login");
        closeButton = new JButton("Close");
        setDefaultCloseAction();
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(chooseButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(closeButton);
        buttonPanel.add(Box.createHorizontalGlue());
        
        
        add(Box.createVerticalStrut(20));
        add(Box.createVerticalGlue());
//        JPanel textPanel = new JPanel();
//        textPanel.setLayout(new FlowLayout());
//        textPanel.add(userField);
        add(new TitledBorderPanel("User", userField));
        add(Box.createVerticalGlue());
        add(Box.createVerticalStrut(20)); 
        add(buttonPanel);
        add(Box.createVerticalStrut(10)); 
    }
    
    public void setCloseAction(ActionListener action) {
        Utils.removeListeners(closeButton);
        closeButton.addActionListener(action);
    }
    
    
    private void setDefaultCloseAction() {
        Utils.removeListeners(closeButton);
        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Container container = getTopLevelAncestor();
                if (container != null && container instanceof Window) {
                    ((Window) container).dispose();
                }
            }
            
        });
    }
    
    public void setChooseAction(ActionListener action) {
        Utils.removeListeners(chooseButton);
        chooseButton.addActionListener(action);
    }
    
    public String getUser() {
        return userField.getText();
    }
    
    @Override
    public Dimension getMinimumSize() {
        return super.getPreferredSize();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Login login = new Login();
        frame.setContentPane(login);
        
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
