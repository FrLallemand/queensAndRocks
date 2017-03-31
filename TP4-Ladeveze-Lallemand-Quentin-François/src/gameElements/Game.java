package gameElements;

public class Game {
    private Player player0;
    private Player player1;
    private Queen queen0;
    private Queen queen1;
    private Rock rock0;
    private Rock rock1;
    private Empty empty;
    
    public Game(){
        this.player0 = new Player(0);
        this.player1 = new Player(1);
        this.queen0  = new Queen(player0);
        this.queen1  = new Queen(player1);
        this.rock0   = new Rock(player0);
        this.rock1   = new Rock(player1);
        this.empty   = new Empty(player0);
    }
    
    public Player getPlayer0(){
        return this.player0;
    }
    
    public Player getPlayer1(){
        return this.player1;
    }
    
    public Queen getQueen0(){
        return this.queen0;
    }
    
    public Queen getQueen1(){
        return this.queen1;
    }
    
    public Rock getRock0(){
        return this.rock0;
    }
    
    public Rock getRock1(){
        return this.rock1;
    }
    
    public Empty getEmpty(){
        return this.empty;
    }
    
    public void setColorMode(){
        if(this.player0.getColorMode().equals("bw")){
            this.player0.setColorMode("og");
            this.player1.setColorMode("og");
        }
        else{
            this.player0.setColorMode("bw");
            this.player1.setColorMode("bw");
        }
    }
    
    public Player otherPlayer(Player player){
        if(player.getNumber() == 0){
            return this.player1;
        }
        else{
            return this.player0;
        }
    }
    
}