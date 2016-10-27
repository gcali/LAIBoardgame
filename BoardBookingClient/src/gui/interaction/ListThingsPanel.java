package gui.interaction;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class ListThingsPanel extends JPanel {
    
    private JButton requestButton;
    private JTable outputTable;
    
    public ListThingsPanel(String requestLabel, TableModel tableModel, int showedRows) {
        init(requestLabel, tableModel, showedRows); 
    }
    
    protected ListThingsPanel() {
        
    }

    protected void init(String requestLabel, TableModel tableModel, int showedRows) {
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        requestButton = new JButton(requestLabel); 
        outputTable = new JTable(tableModel);
        JTextField tf = new JTextField();
        tf.setEditable(false);
        DefaultCellEditor editor = new DefaultCellEditor( tf );
        outputTable.setDefaultEditor(Object.class, editor);

        
        add(Box.createVerticalGlue());
        add(requestButton);
        add(Box.createVerticalGlue());

        JScrollPane pane = new JScrollPane(
                outputTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED 
        ) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                return new Dimension(d.width, outputTable.getRowHeight() * showedRows);
            }
        };
        add(pane);
        add(Box.createVerticalGlue()); 
        
    }
    
    public void addRequestActionListener(ActionListener l) {
        requestButton.addActionListener(l);
    }
    

}

