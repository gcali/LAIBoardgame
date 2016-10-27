package gui.support;

import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.UIManager;

public class Utils {

    public static void removeListeners(JButton button) {
        for (ActionListener listener : button.getActionListeners()) {
            button.removeActionListener(listener);
        } 
    }

    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
//                    UIManager.getCrossPlatformLookAndFeelClassName()
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            
        } 
    }

    public static void setDefaultCloseAction(JButton button) {
        removeListeners(button);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Container container = button.getTopLevelAncestor();
                if (container != null && container instanceof Window) {
                    ((Window) container).dispose();
                }
            }
            
        });
    }

}
