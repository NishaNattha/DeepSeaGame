package projectdeepsea;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerNumberWindow implements ActionListener {

    JFrame frame = new JFrame();
    JLabel background;
    JButton startButton = new JButton();
    JComboBox players;
    

    PlayerNumberWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setTitle(mainMenu.title);
        frame.setIconImage(mainMenu.logo.getImage());
        frame.setLocationRelativeTo(null);
        
        background = new JLabel("", new ImageIcon("images\\bgPlayerInput.png"), JLabel.CENTER);
        background.setBounds(0, 0, 900, 600);
        
        JPanel p = new JPanel();
        String[] playerNumbers = {"2", "3", "4", "5", "6"};
        players = new JComboBox(playerNumbers);
        players.setPrototypeDisplayValue("text here");
        players.setPreferredSize(new Dimension(100,50));
        p.add(players);
        p.setBounds(400, 230, 100, 50);
        
        mainMenu.createButton(startButton, 350, 350, 200, 100, new ImageIcon("images\\startButton.png"));
        startButton.addActionListener(this);
        
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        
        background.add(startButton);
        background.add(p);
        frame.add(background);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {
            frame.dispose();
            new MainGameWindow(Integer.parseInt(players.getSelectedItem().toString()));
        }
    }
}
