package projectdeepsea;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Players {
    private int score;
    public int tilePickupCount;
    private boolean direction;  //forward=true, backward=false
    private boolean status; //onboat=true, dive=false
    private int position;
    public int move;
    public int finalScore;
    public static int countStatus=0;
    private JLabel character;
    
    Random rand = new Random();
    
    public Players(){
        score = 0;
        tilePickupCount = 0;
        direction = true;
        status = false;
        position = -1;
        finalScore = 0;
    }
    
    public void walk(Map tile_map,Players[] player){
        move -= this.tilePickupCount;
        while(move>0){
            if(direction == true){
                if(position == (tile_map.tile.size()-1)){
                    direction = false;
                    move++;
                }
                else{
                    int j=0;
                    for(int i=0;i<player.length;i++){
                        if(player[i]==this) i++;
                        else if(player[i].position == this.position+1)   j=1;
                    }
                    if(tile_map.tile.get(position+1).getscore() == -1 || j==1) move++;
                    position++;
                } 
            }else if(direction == false){
                if(position-move <0){
                    position -= move;
                    move =0;
                    status = true;
                    countStatus++;
                }else{
                    if(tile_map.tile.get(position-1).getscore() == -1) move++;
                    position--;
                }
            }
            move--;
        }
        System.out.println("\nPLAYER at "+(position+1));
    }
    
    
    public int getScore(){
        return score;
    }
    
    public int getPosition(){
        return position;
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public void setStatus(boolean changeStatus){
        status = changeStatus;
    }
    
    public void setDirection(boolean changeDir){
        direction = changeDir;
    }
    
    public boolean getDirection(){
        return direction;
    }
    
    public int getFinalScore(){
        return finalScore;
    }
    
    void collect(int mark){
        score += mark;
        tilePickupCount++;
    }
    
    public int dice(){
        move = 0;
        System.out.print("\ndice : ");
        for(int i=0;i<2;i++){
            int ran = rand.nextInt(2) +1;
            System.out.print(ran +"  ");
            move += ran;
        }
        return move;
    }
    
    public void playerReRound(Map tile_map){
        if(status == false &&  score!=0){
            tile_map.tile.add(new TileScore(5,score));
            tile_map.tile.get(tile_map.tile.size()-1).setTileIcon(new ImageIcon("images\\tile5.png"));
            MainGameWindow.mainLabel.add(tile_map.tile.get(tile_map.tile.size()-1).getTileIcon());
        }  //ตกเรือ
        else finalScore += score;
        
        score = 0;      
        tilePickupCount = 0;
        direction = true;
        status = false;
        position = -1;
        countStatus=0;
    }
    
    public void setCharacter(ImageIcon icon){
        character = new JLabel(icon);
        
    }
    
    public JLabel getCharacter(){
        return character;
    }
}
