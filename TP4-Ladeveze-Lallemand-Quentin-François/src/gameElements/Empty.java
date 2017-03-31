package gameElements;

public class Empty implements Square {

	private Player player;
	
	public Empty(Player p){
		this.player = p;
	}
	
	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public String toString() {
		return " ";
	}

}
