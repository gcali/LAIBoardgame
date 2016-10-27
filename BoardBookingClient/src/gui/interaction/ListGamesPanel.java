package gui.interaction;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import misc.Utils;
import net.ddns.lai.boardbooking.BoardBooking;

import net.ddns.lai.schema.boardbooking.BoardgameInfoType;
import net.ddns.lai.schema.boardbooking.EmptyType;
import net.ddns.lai.schema.boardbooking.GameListType;

public class ListGamesPanel extends ListThingsPanel {
    
    private BoardBooking port;
    private List<BoardgameInfoType> boardgameList;
    private GameListTableModel tableModel;
    
    public ListGamesPanel(BoardBooking port) {
        
        this.port = port;
        tableModel = new GameListTableModel();
        
        init("Request game list", tableModel, 5);

        addRequestActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                GameListType result = port.listGames(new EmptyType());
                if (result == null || result.getGame() == null) {
                    JOptionPane.showMessageDialog(ListGamesPanel.this, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boardgameList = result.getGame();
                    tableModel.fireTableDataChanged();
                }
            }
        });
    }
    
    private class GameListTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            if (boardgameList != null) {
                return boardgameList.size();
            } else {
                return 0;
            }
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        
        @Override
        public String getColumnName(int column) {
            if (column == 0) {
                return "Name";
            } else if (column == 1) {
                return "Price";
            } else {
                return "Unknown";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (boardgameList == null) {
                return "";
            } else {
                BoardgameInfoType element = boardgameList.get(rowIndex);
                if (columnIndex == 0) {
                    return element.getName();
                } else if (columnIndex == 1) {
                    return Utils.centPriceToString(element.getPrice());
                } else {
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
