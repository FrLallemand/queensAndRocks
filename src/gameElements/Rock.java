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
		if (player.getNumber()==0) {
			return "R0";
		}
		else {
			return "R1";
		}
	}
}
