package gui.interaction;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.xml.ws.BindingProvider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import gui.support.Utils;
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.boardbooking.BoardBooking_Service;
import net.ddns.lai.boardpayment.BoardPayment;


public class Interaction extends JPanel {
    
    private final String user;
    private final JButton back;
    private final BoardBooking bookingPort;
    private final BoardPayment paymentPort;

    public Interaction(String user) {

        ClassPathXmlApplicationContext bookingContext = new ClassPathXmlApplicationContext("bookingClient.xml");
        bookingPort = (BoardBooking) bookingContext.getBean("client");
        
        ClassPathXmlApplicationContext paymentContext = new ClassPathXmlApplicationContext("paymentClient.xml");
        paymentPort = (BoardPayment) paymentContext.getBean("client");
        
        misc.Utils.setMaintainSession((BindingProvider) bookingPort, true);

        this.user = user;
        
        JTabbedPane pane = new JTabbedPane() {
            @Override
            public Dimension getPreferredSize() {
                Dimension oldMin = super.getPreferredSize();
                return new Dimension(Math.max(oldMin.width, 300), oldMin.height);
            }
        };
        
        JPanel openSession = new OpenSessionPanel(user, bookingPort);
        JPanel abortBooking = new AbortPanel(bookingPort);
        JPanel addBooking = new AddPanel(bookingPort);
        JPanel requestPayment = new RequestPaymentPanel(bookingPort);
        JPanel listGames = new ListGamesPanel(bookingPort);
        JPanel listGroups = new ListGroupsPanel(bookingPort);
        JPanel listBookings = new ListBookingsPanel(bookingPort);
        JPanel pay = new PayPanel(paymentPort);
        JPanel changeAddress = new ChangeAddressPanel(bookingPort, paymentPort);
        
        pane.addTab("Open", openSession);
        pane.addTab("Abort", abortBooking);
        pane.addTab("Add", addBooking);
        pane.addTab("Request", requestPayment);
        pane.addTab("ListGames", listGames);
        pane.addTab("ListGroups", listGroups);
        pane.addTab("ListBookings", listBookings);
        pane.addTab("Pay", pay);
        pane.addTab("ChangeAddress", changeAddress);
        
        back = new JButton("Back");
        Utils.setDefaultCloseAction(back);
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(back);
        backPanel.add(Box.createHorizontalStrut(4));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        add(pane);
        add(Box.createVerticalStrut(10));
        add(backPanel);
        add(Box.createVerticalStrut(6));
    }
    
    public void setBackAction(ActionListener action) {
        Utils.removeListeners(back);
        back.addActionListener(action);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return super.getPreferredSize();
    }
    
    public static void main(String[] args) {
        
        Utils.setLookAndFeel();
        JFrame frame = new JFrame("Interaction");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Interaction interaction = new Interaction("Gio");
        interaction.setBackAction(e -> frame.dispose());
        frame.setContentPane(interaction);
        
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
