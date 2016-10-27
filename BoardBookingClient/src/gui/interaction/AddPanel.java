package gui.interaction;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gui.support.DateTextField;
import gui.support.DefaultJTextField;
import gui.support.TitledBorderPanel;
import gui.support.Utils;
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.schema.boardbooking.BoardgameBookingAnswerType;
import net.ddns.lai.schema.boardbooking.BookingDataType;

public class AddPanel extends JPanel { 

    private final BoardBooking port;
    private DateTextField baseDate;
    private DateTextField endDate;
    private DefaultJTextField game;
    private JButton button;
    private JTextField output;

    public AddPanel(BoardBooking port) {
        this.port = port;
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        baseDate = new DateTextField(10);
        endDate = new DateTextField(10);
        game = new DefaultJTextField("name", 20);
        output = new JTextField(36);
        output.setEditable(false);
        
        JPanel centeredPanel = new JPanel() {
            @Override
            public Dimension getMaximumSize() {
                return super.getPreferredSize();
            }
        };
        centeredPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centeredPanel.add(new TitledBorderPanel("Name", game));
        centeredPanel.add(new TitledBorderPanel("Base", baseDate));
        centeredPanel.add(new TitledBorderPanel("End", endDate)); 
        
        
        add(Box.createVerticalGlue());
        add(centeredPanel); 
        add(Box.createVerticalGlue());
        button = new JButton("Book");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate base = baseDate.getDate();
                LocalDate end = endDate.getDate();
                String name = game.getText();
                if (base == null || end == null || name == null || name.isEmpty()) {
                    JOptionPane.showMessageDialog(AddPanel.this, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    BookingDataType message = new BookingDataType();
                    message.setBaseDate(base);
                    message.setEndDate(end);
                    message.setGame(name);
                    BoardgameBookingAnswerType answer = port.bookBoardgame(message);
                    if (answer.getFailure() != null) {
                        JOptionPane.showMessageDialog(AddPanel.this, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        output.requestFocusInWindow();
                        output.setText(answer.getSuccess());
                        JOptionPane.showMessageDialog(AddPanel.this, "Result: " + answer.getSuccess());
                    }
                }
            }
        });
        add(button);
        add(Box.createVerticalGlue());
        add(new TitledBorderPanel("Output", output));
        add(Box.createVerticalGlue());

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddPanel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Utils.setLookAndFeel();
        frame.setContentPane(new AddPanel(null));
        
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
