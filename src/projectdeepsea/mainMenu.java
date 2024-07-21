package projectdeepsea;


import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class mainMenu implements ActionListener {
    
    public static String title = "Deep Sea Adventure";
    public static ImageIcon logo = new ImageIcon("images\\logo.png");

    JFrame frame;
    JLabel background;
    JButton playButton = new JButton();
    JButton howtoButton = new JButton();

    public mainMenu() {
        
        frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1920, 1080);
        frame.setIconImage(logo.getImage());
        
        background = new JLabel("", new ImageIcon("images\\Background.jpg"), JLabel.CENTER);
        background.setBounds(0, 0, 1920, 1080);
        
        createButton(playButton,750,500,370,200,new ImageIcon("images\\playButton.png"));
        createButton(howtoButton, 670,730,520,200,new ImageIcon("images\\howtoButton.png"));
        playButton.addActionListener(this);
        howtoButton.addActionListener(this);
        
        frame.setLayout(null);
        frame.setVisible(true);
        
        frame.add(playButton);
        frame.add(howtoButton);
        frame.add(background);

    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == howtoButton) {
            frame.dispose();
            new HowToPlayWindow();
        } else if (e.getSource() == playButton) {
            frame.dispose();
            new PlayerNumberWindow();
        }
    }
    
    public static void createButton(JButton button, int x, int y, int wide, int height, ImageIcon icon) {
        button.setBounds(x, y, wide, height);
        button.setIcon(icon);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setVisible(true);
    }
    
}
