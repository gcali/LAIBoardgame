package gui.interaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.support.TitledBorderPanel;
import misc.Utils;
import misc.WrongInputException;
import net.ddns.lai.boardpayment.BoardPayment;
import net.ddns.lai.schema.boardpayment.RequestPaymentType;
import net.ddns.lai.schema.boardpayment.SuccessOrFailure;

public class PayPanel extends JPanel {
    
    private BoardPayment port;
    private JTextField priceInput;
    private JTextField idInput;
    private JButton payButton;

    public PayPanel(BoardPayment port) {
        this.port = port;
        
        this.priceInput = new JTextField(10);
        this.idInput = new JTextField(36);
        
        this.payButton = new JButton("Pay");
        
        payButton.setAlignmentX(CENTER_ALIGNMENT);
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        add(Box.createVerticalGlue());
        add(new TitledBorderPanel("Price", priceInput));
        add(Box.createVerticalGlue());
        add(new TitledBorderPanel("ID", idInput));
        add(Box.createVerticalGlue());
        add(payButton);
        add(Box.createVerticalGlue());
        
        payButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int price = Utils.parsePrice(priceInput.getText());
                    RequestPaymentType paymentRequest = new RequestPaymentType();
                    paymentRequest.setId(idInput.getText());
                    paymentRequest.setPrice(price);
                    SuccessOrFailure result = port.requestPayment(paymentRequest);
                    if (result == null || result == SuccessOrFailure.FAILURE) {
                        JOptionPane.showMessageDialog(PayPanel.this, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(PayPanel.this, "Success!");
                    }
                } catch (WrongInputException e1) {
                    JOptionPane.showMessageDialog(PayPanel.this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
