package gameElements;

public class Eval0 implements Eval {

	@Override
	public float getEval(Player player, Board b) {
		Player adversaire;
		if (player == b.getGame().getPlayer0()){
			adversaire = b.getGame().getPlayer0();
		}
		else{
			adversaire = b.getGame().getPlayer1();			
		}
		return b.getScore(player) - b.getScore(adversaire);
	}
}
