package gameElements;

public class Queen implements Square {
	private Player player;
	
	public Queen(Player p) {
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
			return "♕";
		} else {
			return "♛";
		}
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj != null && obj.getClass() == this.getClass()){			
			Queen q = (Queen)obj;
			if(this.player.getNumber() == q.getPlayer().getNumber()){
				result = true;
			}
			
		}		
		return result;
	}


}
