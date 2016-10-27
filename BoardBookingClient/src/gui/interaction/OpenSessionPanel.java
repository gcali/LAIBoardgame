package gui.interaction;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.support.Utils;
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.schema.boardbooking.OpenSessionType;
import net.ddns.lai.schema.boardbooking.SuccessOrFailure;

public class OpenSessionPanel extends JPanel {
    
    private String user;
    private BoardBooking port;
    
    public OpenSessionPanel(String user, BoardBooking port) {
        this.user = user;
        this.port = port;
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        JButton open = new JButton("Open");
        
        JLabel userLabel = new JLabel(user);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        open.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(userLabel);
        add(Box.createVerticalGlue());
        add(open);
        
        
        open.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenSessionType message = new OpenSessionType();
                message.setUser(user);
                SuccessOrFailure result = port.openSession(message);
                JOptionPane.showMessageDialog(OpenSessionPanel.this, "Open session result: " + result.value());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OpenSession");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Utils.setLookAndFeel();
        frame.setContentPane(new OpenSessionPanel("Gio", null));
        
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
