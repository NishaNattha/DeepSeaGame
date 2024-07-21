package projectdeepsea;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public interface Tile {
    int getscore();
    int getlevel();
    void setTileIcon(ImageIcon icon);
    JLabel getTileIcon();
    int getxPosition();
    int getyPosition();
    void setPosition(int x,int y);
    void setNewTileIcon(ImageIcon icon);
    void setscore(int score);
}
