package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gui.interaction.Interaction;
import gui.login.Login;
import gui.support.Utils;

public class GuiMain extends JFrame {
    
    private boolean shouldResize = true;
    
    public GuiMain() {
        super("Client");
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                if (shouldResize) {
                    GuiMain.this.setMinimumSize(GuiMain.this.getMinimumSize());
                }
                shouldResize = false;
            }
        });

    }
    
    @Override
    public Dimension getMinimumSize() {
        shouldResize = true;
        Dimension old = getContentPane().getMinimumSize();
        return new Dimension(old.width + 10, old.height + 30);
    }
    
    private static void setInteractionAction(Interaction interaction, JFrame frame) {
        interaction.setBackAction(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                setLoginAction(login, frame);
                frame.setContentPane(login);
                frame.pack();
            }
        });
    }
    
    private static void setLoginAction(Login login, JFrame frame) {
        login.setChooseAction(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = login.getUser();
                if (user == null || user.length() < 1) {
                    JOptionPane.showMessageDialog(frame, "Invalid user", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Interaction interaction = new Interaction(user);
                    setInteractionAction(interaction, frame);
                    frame.setContentPane(interaction);
                    frame.pack();
                }
            }
        });
    }

    public static void main(String[] args) {
        Utils.setLookAndFeel();
        JFrame frame = new GuiMain();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Login login = new Login();
        frame.setContentPane(login);
        setLoginAction(login, frame);
        
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
