package gui.interaction;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.ws.BindingProvider;

import gui.support.TitledBorderPanel;
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.boardpayment.BoardPayment;

public class ChangeAddressPanel extends JPanel {

	private static final String remotePaymentAddress = "http://lai.ddns.net:8080/BoardPayment/services/BoardPayment"; 
	private static final String remoteBookingAddress= "http://lai.ddns.net:8080/BoardBooking/services/BoardBooking";
	
	private static final String localPaymentAddress = "http://localhost:8080/BoardPayment/services/BoardPayment";
	private static final String localBookingAddress= "http://localhost:8080/BoardBooking/services/BoardBooking";

	private static final String defaultProxyAddress = "http://localhost:8080/BoardProxy/Proxy";

    private final String startingBookingAddress;
    private final String startingPaymentAddress;
    private final BindingProvider bookingProvider;
    private final BindingProvider paymentProvider;
    private ButtonsPanel bookingPanel;
    private ButtonsPanel paymentPanel;
	
	public ChangeAddressPanel(BoardBooking bookingPort, BoardPayment paymentPort) {
        bookingProvider = (BindingProvider) bookingPort;
        paymentProvider = (BindingProvider) paymentPort;
	    if (bookingPort != null && paymentPort != null) {
            startingBookingAddress = (String) ((BindingProvider)bookingPort).getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
            startingPaymentAddress  = (String) ((BindingProvider)paymentPort).getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY); 
	    } else {
	        startingBookingAddress = null;
	        startingPaymentAddress = null;
	    }
	    
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	    
	    JPanel buttonsPanel = new JPanel();
	    buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
	    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
	    
	    bookingPanel = new ButtonsPanel();
	    paymentPanel = new ButtonsPanel();
	    
	    ActionListener basic = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (!"custom".equals(command)){ 
                    System.out.println(command);
                } else {
                    System.out.println(command + ": " + bookingPanel.getCustomText());
                }
            }
        };
	    
	    buttonsPanel.add(new TitledBorderPanel("Booking", bookingPanel));
	    buttonsPanel.add(new TitledBorderPanel("Payment", paymentPanel));
	    
	    JButton updateButton = new JButton("Update");
	    updateButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getBookingAddress());
                paymentProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getPaymentAddress());
            }
        });
	    updateButton.setAlignmentX(CENTER_ALIGNMENT);
	    add(buttonsPanel);
	    add(Box.createVerticalStrut(10));
	    add(updateButton);
	}
	
	private String getBookingAddress() {
	    switch (getBookingOption()) {
	    case DEFAULT:
	        return startingBookingAddress;
	    case REMOTE:
	        return remoteBookingAddress;
	    case PROXY:
	        return defaultProxyAddress;
	    case LOCAL:
	        return localBookingAddress;
	    case CUSTOM:
	        return getBookingCustom();
	    default:
	        return "";
	    }
	}
	
	private String getPaymentAddress() {
	    switch (getPaymentOption()) {
	    case DEFAULT:
	        return startingPaymentAddress;
	    case REMOTE:
	        return remotePaymentAddress;
	    case PROXY:
	        return defaultProxyAddress;
	    case LOCAL:
	        return localPaymentAddress;
	    case CUSTOM:
	        return getPaymentCustom();
	    default:
	        return "";
	    }
	}
	
	public MyRadioButtons getBookingOption() {
	    return bookingPanel.getSelected();
	}
	
	public MyRadioButtons getPaymentOption() {
	    return paymentPanel.getSelected();
	}
	
	public String getBookingCustom() {
	    return bookingPanel.getCustomText();
	}
	
	public String getPaymentCustom() {
	    return paymentPanel.getCustomText();
	}
	
	public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Change test");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(new ChangeAddressPanel(null, null));
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                mainFrame.pack();
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });
    }

	public static enum MyRadioButtons {
	    DEFAULT, PROXY, REMOTE, LOCAL, CUSTOM 
	}
	

	
	private static class ButtonsPanel extends JPanel {
	    
	    private final List<ActionListener> actionListeners = new ArrayList<>();
        private final JTextField input;
        private JRadioButton defaultButton;
        private JRadioButton proxyButton;
        private JRadioButton remoteButton;
        private JRadioButton customButton;
        private JRadioButton localButton;

	    public ButtonsPanel() {
                
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            
            JPanel lastPanel = new JPanel();
            lastPanel.setLayout(new BoxLayout(lastPanel, BoxLayout.LINE_AXIS));
            lastPanel.setAlignmentX(LEFT_ALIGNMENT);
            
            defaultButton = new JRadioButton("Default");
            proxyButton = new JRadioButton("Proxy");
            remoteButton = new JRadioButton("Remote");
            localButton = new JRadioButton("Local");
            customButton = new JRadioButton("");
            
            input = new JTextField(30) {
                @Override
                public Dimension getMaximumSize() {
                    return super.getPreferredSize();
                }
            };
            
            input.setEditable(false);
            
            customButton.addItemListener(new ItemListener() {
                
                @Override
                public void itemStateChanged(ItemEvent e) {
                    input.setEditable(customButton.isSelected());
                }
            });
            
            ButtonGroup group = new ButtonGroup();
            group.add(defaultButton);
            group.add(proxyButton);
            group.add(remoteButton);
            group.add(localButton);
            group.add(customButton);
            
            defaultButton.setSelected(true);
            
            defaultButton.setAlignmentX(LEFT_ALIGNMENT);
            proxyButton.setAlignmentX(LEFT_ALIGNMENT);
            remoteButton.setAlignmentX(LEFT_ALIGNMENT);
            localButton.setAlignmentX(LEFT_ALIGNMENT);
            customButton.setAlignmentX(LEFT_ALIGNMENT);
            input.setAlignmentX(LEFT_ALIGNMENT);
            
            lastPanel.add(customButton);
            lastPanel.add(input);
            
            add(defaultButton);
            add(proxyButton);
            add(remoteButton);
            add(localButton);
            add(lastPanel);
        }
	    
	    public String getCustomText() {
	        return input.getText();
	    }
	    
	    public MyRadioButtons getSelected() {
	        if (defaultButton.isSelected()) {
	            return MyRadioButtons.DEFAULT;
	        } else if (proxyButton.isSelected()) {
	            return MyRadioButtons.PROXY;
	        } else if (remoteButton.isSelected()) {
	            return MyRadioButtons.REMOTE;
	        } else if (localButton.isSelected()) {
	            return MyRadioButtons.LOCAL;
	        } else {
	            return MyRadioButtons.CUSTOM;
	        }
	    }

	    
    }
	
}
