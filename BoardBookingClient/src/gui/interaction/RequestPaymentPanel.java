package gui.interaction;

import java.awt.Component;
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
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.schema.boardbooking.EmptyType;
import net.ddns.lai.schema.boardbooking.RequestPaymentDataAnswerType;
import net.ddns.lai.schema.boardbooking.RequestPaymentDataAnswerType.Success;

public class RequestPaymentPanel extends JPanel {

    private BoardBooking port;
    private JButton requestButton;
    private JTextField costOutput;
    private JTextField idOutput;

    public RequestPaymentPanel(BoardBooking port) {
        this.port = port;
        this.requestButton = new JButton("Request Payment");
        this.costOutput = new JTextField(10);
        this.idOutput = new JTextField(36);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        requestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        costOutput.setEditable(false);
        idOutput.setEditable(false);
        
        add(Box.createVerticalGlue());
        add(requestButton);
        add(Box.createVerticalGlue());
        add(new TitledBorderPanel("Cost", costOutput));
        add(Box.createVerticalGlue());
        add(new TitledBorderPanel("ID", idOutput));
        add(Box.createVerticalGlue());
        
        requestButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestPaymentDataAnswerType result = port.requestPaymentData(new EmptyType());
                if (result == null || result.getFailure() != null) {
                    JOptionPane.showMessageDialog(RequestPaymentPanel.this, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Success success = result.getSuccess();
                    costOutput.setText(Utils.centPriceToString(success.getPrice()));
                    idOutput.setText(success.getBookingID());
                }
            }
        });
    }


}
