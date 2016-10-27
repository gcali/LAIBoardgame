package gui.interaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import net.ddns.lai.boardbooking.BoardBooking;
import net.ddns.lai.schema.boardbooking.BookingGroupListType;
import net.ddns.lai.schema.boardbooking.BookingGroupType;
import net.ddns.lai.schema.boardbooking.EmptyType;

public class ListGroupsPanel extends ListThingsPanel {
    
    private BoardBooking port;
    private List<BookingGroupType> groupList;
    private GroupListTableModel tableModel;

    public ListGroupsPanel(BoardBooking port) {
        this.port = port; 
        tableModel = new GroupListTableModel();
        
        init("Request Booking groups", tableModel, 5);
        
        addRequestActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingGroupListType result = port.bookingGroups(new EmptyType());
                if (result == null || result.getItem() == null) {
                    JOptionPane.showMessageDialog(ListGroupsPanel.this, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    groupList = result.getItem();
                    tableModel.fireTableDataChanged();
                }
            }
        });
    } 
    
    private class GroupListTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            if (groupList == null) {
                return 0;
            } else {
                return groupList.size();
            }
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        
        @Override
        public String getColumnName(int column) {
            switch (column) {
            case 0:
                return "UUID";
            case 1:
                return "Paid";
            default:
                return "";
            }
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (groupList == null) {
                return "";
            } else {
                BookingGroupType element = groupList.get(rowIndex);
                switch (columnIndex) {
                case 0:
                    return element.getBookingID();
                case 1:
                    return element.isPaid();
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
