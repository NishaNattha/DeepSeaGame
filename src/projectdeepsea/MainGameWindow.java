package projectdeepsea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;


public class MainGameWindow extends JFrame implements ActionListener {

    JFrame frame = new JFrame(mainMenu.title);
    JProgressBar bar;
    JButton rollButton = new JButton();
    JButton yesCollectButton = new JButton();
    JButton noCollectButton = new JButton();
    JButton forwardButton = new JButton();
    JButton backwardButton = new JButton();
    static JLabel mainLabel = new JLabel("", new ImageIcon("images\\mainbg.png"), JLabel.CENTER);
    JLabel scoreLabel = new JLabel(), label;
    JLabel playerLabel = new JLabel();
    JLabel roundLabel =  new JLabel();
    JLabel o2Bar = new JLabel();
    int round = 1,  play=-1;
    int spaceX = 150;
    int spaceY = 150;
    boolean first = true;
    Players[] player;
    Submarine submarine = new Submarine();
    Tile tile_blank = new TileBlank();
    Map tile_map = new Map();
    ImageIcon tileBlank = new ImageIcon("images\\tileBlank.png");
    JFrame endFrame = new JFrame(mainMenu.title);
    Font titleFont = new Font("Cunia",Font.BOLD,50);
    
    

    public MainGameWindow(int playerNumber) {
        player = new Players[playerNumber];     

        for (int i = 0; i < player.length; i++) {
            player[i] = new Players(); 
            player[i].setCharacter(new ImageIcon("images\\character" + (i + 1) + ".png"));
            player[i].getCharacter().setBounds((i*100)+10, 0, 100, 100);
            mainLabel.add(player[i].getCharacter());
        }
        tile_blank.setTileIcon(new ImageIcon("images\\tileBlank.png"));
        bar = new JProgressBar(JProgressBar.VERTICAL, 0, 25);
        bar.setValue(submarine.o2State);
        bar.setBounds(50, 150, 50, 700);
        bar.setStringPainted(true);
        bar.setString("o2 state 25/" + submarine.o2State);
        
        o2Bar.setIcon(new ImageIcon("images\\o2Bar.png"));
        o2Bar.setBounds(35, 50, 150, 900);
        
        mainMenu.createButton(forwardButton, 1400, 200, 200, 200, new ImageIcon("images\\keepDiveButton.png"));
        mainMenu.createButton(backwardButton, 1650, 200, 200, 200, new ImageIcon("images\\turnBackButton.png"));
        mainMenu.createButton(yesCollectButton, 1400, 700, 200, 200, new ImageIcon("images\\collectButton.png"));
        mainMenu.createButton(noCollectButton, 1650, 700, 200, 200, new ImageIcon("images\\notCollectButton.png"));
        mainMenu.createButton(rollButton, 1500, 450, 200, 200, new ImageIcon("images\\rollButton.png"));
        forwardButton.addActionListener(this);
        backwardButton.addActionListener(this);
        yesCollectButton.addActionListener(this);
        noCollectButton.addActionListener(this);
        rollButton.addActionListener(this);
        
        mainLabel.add(forwardButton);
        mainLabel.add(backwardButton);
        mainLabel.add(yesCollectButton);
        mainLabel.add(noCollectButton);
        mainLabel.add(rollButton);
        scoreLabel.setBounds(1500, 500,300,100);
        playerLabel.setBounds(1500, 130, 300, 50);
        roundLabel.setBounds(1500, 50, 300, 50);
        scoreLabel.setFont(titleFont);
        playerLabel.setFont(titleFont);
        roundLabel.setFont(titleFont);
        mainLabel.add(scoreLabel);
        mainLabel.add(playerLabel);
        mainLabel.add(roundLabel);
        smallLoop();
        mainLabel.setBounds(0, 0, 1920, 1080);
        
        frame.setTitle(mainMenu.title);
        frame.setIconImage(mainMenu.logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLayout(null);
        frame.setSize(1920, 1080);
        
        for(int i=1;i<=32;i++) {
            tile_map.tile.get(i-1).setPosition(100 + spaceX, 50 + spaceY);
            tile_map.tile.get(i-1).getTileIcon().setBounds(100 + spaceX, 50 + spaceY, 100, 100);
            spaceX += 150;
            if((i%8==0 && i!=0) || i==32){
                spaceY += 150;
                spaceX = 150;
            }
            mainLabel.add(tile_map.tile.get(i-1).getTileIcon());
        }
        
        
        
        mainLabel.add(o2Bar);
        mainLabel.add(bar);
        frame.setVisible(true);
        frame.add(mainLabel);
    }
    
    public void smallLoop(){
        play++;
        if (play > player.length - 1) {
            play = 0;
            first = false;
        }
        if(Players.countStatus == player.length)  gameLoop();
        
        if (player[play].getStatus() == true && Players.countStatus != player.length) {
            play++;
            if(play > player.length - 1){
                for(int j=0;j<player.length;j++){
                    if(player[j].getStatus() == false){
                        play = j;
                        break;
                    }
                }
            }
        }

        submarine.o2Decrease(player[play].tilePickupCount);
        bar.setValue(submarine.o2State);
        bar.setString("o2 state 25/" + submarine.o2State);
        if(submarine.o2State <= 0)  gameLoop();
        
        if(player[play].getDirection() == true && first == false){
            rollButton.setVisible(false);
            forwardButton.setVisible(true);
            backwardButton.setVisible(true);
        }else{
            rollButton.setVisible(true);
            forwardButton.setVisible(false);
            backwardButton.setVisible(false);
        }
        
        yesCollectButton.setVisible(false);
        noCollectButton.setVisible(false);
        playerLabel.setText("PLAYER : "+ (play+1));
        roundLabel.setText("ROUND : "+ round);
        
        
    }
    
    public void gameLoop(){
        
        round++;
        if(round > 3){
            for (play = 0; play < player.length; play++) {
                System.out.println("\nPLAYER " + (play + 1) + " : " + player[play].getFinalScore());
            }
            rollButton.setVisible(false);
            forwardButton.setVisible(false);
            backwardButton.setVisible(false);
            yesCollectButton.setVisible(false);
            noCollectButton.setVisible(false);
            endFrame();
            return;
        }
        submarine.o2State = 25;
        tile_map.reMap();
        for (play = 0; play < player.length; play++) {
            player[play].getCharacter().setBounds((play*100)+10, 0, 100, 100);
            player[play].playerReRound(tile_map);
            System.out.print(play);
        }
        first = true;
        play = -1;
        spaceX = 150;
        spaceY = 150;
        for(int i=1;i<=tile_map.tile.size();i++){ 
            tile_map.tile.get(i-1).setPosition(100 + spaceX, 50 + spaceY);
            tile_map.tile.get(i-1).getTileIcon().setBounds(100 + spaceX, 50 + spaceY, 100, 100);
            spaceX += 150;
            if((i%8==0 && i!=0) || i==32){
                spaceY += 150;
                spaceX = 150;
            }
        }
        for(int i=1;i<=tile_map.tile.size();i++){
            System.out.print(tile_map.tile.get(i-1).getscore()+"\t");
            if((i%8==0 && i!=0) || i==32)   System.out.println();
        }
        System.out.println();
        rollButton.setVisible(true);
        
        smallLoop();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == rollButton ){
            rollButton.setVisible(false);
            player[play].move = player[play].dice();
            scoreLabel.setText("Score: " + player[play].move);
            player[play].walk(tile_map, player);
            if(player[play].getStatus() == true){
                player[play].getCharacter().setBounds((play*100)+10, 0, 100, 100);
            }else{
                player[play].getCharacter().setBounds(tile_map.tile.get(player[play].getPosition()).getxPosition(), tile_map.tile.get(player[play].getPosition()).getyPosition(), 100, 100);
            }
            if(player[play].getStatus() == false){
                yesCollectButton.setVisible(true);
                noCollectButton.setVisible(true);
            }else{
                smallLoop();
            }
            
        }

        if(e.getSource() == forwardButton) {
            player[play].setDirection(true);
            rollButton.setVisible(true);
            forwardButton.setVisible(false);
            backwardButton.setVisible(false);
        }else if (e.getSource() == backwardButton) {
            player[play].setDirection(false);
            rollButton.setVisible(true);
            forwardButton.setVisible(false);
            backwardButton.setVisible(false);
        }
        if(e.getSource() == yesCollectButton) {
            
            yesCollectButton.setVisible(false);
            noCollectButton.setVisible(false);
            
            player[play].collect(tile_map.tile.get(player[play].getPosition()).getscore());
            tile_map.tile.get(player[play].getPosition()).setNewTileIcon(tileBlank);
            tile_map.tile.get(player[play].getPosition()).setscore(-1);
            tile_map.tile.get(player[play].getPosition()).getTileIcon().setBounds(tile_map.tile.get(player[play].getPosition()).getxPosition(),tile_map.tile.get(player[play].getPosition()).getyPosition(), 100, 100);
            tile_map.tileDel.add(tile_map.tile.get(player[play].getPosition()));
            mainLabel.add(tile_map.tile.get(player[play].getPosition()).getTileIcon());
            for(int i=0;i<tile_map.tile.size();i++){
                System.out.print(tile_map.tile.get(i).getscore()+"\t");
                if((i%8==0 && i!=0) || i==32)   System.out.println();
            }
            smallLoop();
        }else if (e.getSource() == noCollectButton) {
            yesCollectButton.setVisible(false);
            noCollectButton.setVisible(false);
            smallLoop();
        }
    }
    
    public void endFrame(){
        Players bestScore = player[0];
        for(int i=1; i<player.length; i++){
            if(player[i].finalScore > bestScore.finalScore)    bestScore = player[i];
        }
        playerLabel = bestScore.getCharacter();
        playerLabel.setBounds(250, 150, 200, 200);
        
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainLabel = new JLabel("", new ImageIcon("images\\Endgameframe.png"), JLabel.CENTER);
        mainLabel.setBounds(0, 0, 700, 600);
        mainLabel.add(playerLabel);
        endFrame.setTitle(mainMenu.title);
        endFrame.setIconImage(mainMenu.logo.getImage());
        endFrame.setResizable(false);
        endFrame.setLayout(null);
        endFrame.setSize(700, 600);
        endFrame.setLocationRelativeTo(null);
        endFrame.add(mainLabel);
        frame.dispose();
        endFrame.setVisible(true);
    }
}
