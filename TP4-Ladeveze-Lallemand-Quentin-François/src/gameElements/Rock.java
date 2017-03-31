package gameElements;

public class Rock implements Square {
	private Player player;
	
	public Rock(Player p) {
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
		return "R";
	}

}
