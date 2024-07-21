 package projectdeepsea;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;

public class Map {
    public ArrayList<Tile> tile = new ArrayList<>();
    public ArrayList<Tile> tileDel = new ArrayList<>();
    

    public Map(){
        int level = 1, score = 0;
        for(int i=1;i<=32;i++){
            tile.add(new TileScore(level,score));
            tile.get(i-1).setTileIcon(new ImageIcon("images\\tile"+level+".png"));
            if((i%8==0 && i!=0) || i==32){
                Collections.shuffle(tile.subList(i-7, i));
                level++;
            }
            if(i%2!=1 && i!=0) score++;
            
        }
    }
    
    
    public void reMap(){
        
        for(int i=1;i<=tile.size();i++){
            System.out.print(tile.get(i-1).getscore()+"\t");
            if((i%8==0 && i!=0) || i==32)   System.out.println();
        }
        System.out.println(tile.size());
        for(int i=0;i<tile.size();i++){ 
            if(tile.get(i).getscore() == -1){
                tile.get(i).getTileIcon().setVisible(false);
                tile.remove(i);
            }
        }
        System.out.println(tile.size());
        for(int i=0;i<tileDel.size();i++){
            tileDel.get(i).getTileIcon().setVisible(false);
        }
    }
}
