package projectdeepsea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class HowToPlayWindow implements ActionListener {

    JFrame frame = new JFrame();
    JButton home = new JButton();
    JLabel background;

    HowToPlayWindow() {
        frame.setTitle(mainMenu.title);
        frame.setIconImage(mainMenu.logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLayout(null);
        
        mainMenu.createButton(home, 20, 50, 200, 200, new ImageIcon("images\\homeIcon.png"));
        home.addActionListener(this);
        frame.add(home);
        
        background = new JLabel("", new ImageIcon("images\\Instruction.png"), JLabel.CENTER);
        background.setBounds(0, 0, 1920, 1080);
        frame.add(background);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== home) {
            frame.dispose();
            new mainMenu();
        }
    }
}
