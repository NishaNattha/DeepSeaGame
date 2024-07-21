package projectdeepsea;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class TileScore implements Tile{
    private int score;
    public int level;
    JLabel tileIcon;
    private int x,y;
    
    public int getxPosition(){
        return x;
    }
    
    public int getyPosition(){
        return y;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public TileScore(int level, int score){
        this.level = level;
        this.score = score;
    }
    
    public int getscore(){
        return score;
    }
    
    public void setscore(int score){
        this.score = score;
    }
    
     public int getlevel(){
        return level;
    }
     
    public void setTileIcon(ImageIcon icon){
        tileIcon = new JLabel(icon);
    }
    
    public JLabel getTileIcon(){
        return tileIcon;
    }
    
    public void setNewTileIcon(ImageIcon icon){
        tileIcon.setIcon(icon);
    }
}
