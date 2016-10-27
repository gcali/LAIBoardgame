package gui.interaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import gui.support.DefaultJTextField;
import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.schema.boardbooking.BookingDetailsListType;
import net.ddns.lai.schema.boardbooking.BookingDetailsType;

public class ListBookingsPanel extends ListThingsPanel {
    
    private BoardBooking port;
    private List<BookingDetailsType> detailsList;
    DetailListTableModel tableModel;
    private DefaultJTextField sessionId;

    public ListBookingsPanel(BoardBooking port) {
        this.port = port;
        
        tableModel = new DetailListTableModel(); 
        this.sessionId = new DefaultJTextField("ID", 20); 
        add(Box.createVerticalGlue());
        add(sessionId);
        add(Box.createVerticalGlue());
        init("Request booking details", tableModel, 5);
        
        addRequestActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = sessionId.getText();
                if (id != null && !id.equals("")) {
                    BookingDetailsListType result = port.bookingDetails(id); 
                    if (result == null || result.getItem() == null) {
                        JOptionPane.showMessageDialog(ListBookingsPanel.this, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        detailsList = result.getItem();
                        tableModel.fireTableDataChanged();
                    }
                } else {
                    JOptionPane.showMessageDialog(ListBookingsPanel.this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
    private class DetailListTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            if (detailsList == null) {
                return 0;
            } else {
                return detailsList.size();
            }
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        
        @Override
        public String getColumnName(int column) {
            switch (column) {
            case 0:
                return "UUID";
            case 1:
                return "Game";
            case 2:
                return "StartDate";
            case 3:
                return "EndDate";
            default:
                return "";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (detailsList == null) {
                return "";
            } else {
                BookingDetailsType element = detailsList.get(rowIndex);
                switch (columnIndex) {
                case 0:
                    return element.getBookingID();
                case 1:
                    return element.getGame();
                case 2:
                    return element.getBaseDate();
                case 3:
                    return element.getEndDate();
                default:
                    return "";
                }
            }
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
        
    }

}
