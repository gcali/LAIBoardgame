package gui.interaction;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.support.DefaultJTextField;
import gui.support.Utils;
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.schema.boardbooking.SuccessOrFailure;

public class AbortPanel extends JPanel {

    private BoardBooking port;
    private DefaultJTextField sessionId;
    private JButton abortButton;

    public AbortPanel(BoardBooking port) {
        this.port = port;
        this.sessionId = new DefaultJTextField("ID", 20);
        this.abortButton = new JButton("Abort");
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        sessionId.setAlignmentX(Component.CENTER_ALIGNMENT);
        abortButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(Box.createVerticalGlue());
        add(sessionId);
        add(Box.createVerticalGlue());
        add(abortButton);
        
        abortButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String sessionIdText = sessionId.getText();
                if (sessionIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(AbortPanel.this, "Invalid session id", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SuccessOrFailure result = port.abortBoardgameBooking(sessionIdText);
                    JOptionPane.showMessageDialog(AbortPanel.this, "Result was: " + result.value());
                }
            }
        });
     
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AbortPanel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Utils.setLookAndFeel();
        frame.setContentPane(new AbortPanel(null));
        
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
