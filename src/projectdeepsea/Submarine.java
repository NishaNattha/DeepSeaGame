package projectdeepsea;


public class Submarine {
    public int o2State;
    
    public Submarine(){
        o2State = 25;
    }
    
    public void o2Decrease(int deNum){
        o2State -= deNum;
    }
}
