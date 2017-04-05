import java.util.Date;
import java.util.Scanner;

import gameElements.Board;
import gameElements.Eval;
import gameElements.Eval0;
import gameElements.Game;
import gameElements.Player;
import graphics.GameUI;

public class Main {
	private static Board board;
	
    public static void main(String[] args) {
    	board = new Board(new Game(), 4);
    	//board.placeQueen(0,  0);
    	//System.out.println(board.getSuccessors());
    	testGraphic();
    	//Eval eval = new Eval0();
    	//test4ordiVSordi(4, 2, 2, true, eval, eval);
    	//System.out.println(board.toString());
    	//System.out.println(board.toStringAccess());
    }
    
    public static void test(){
    	while(true){
    		System.out.println(board.toString());
        	Scanner reader = new Scanner(System.in);  
        	System.out.println("Coordonnee i: ");
        	int i = reader.nextInt(); 
        	System.out.println("Coordonnee j: ");
        	int j = reader.nextInt(); 
        	if(!board.placeQueen(i, j)){
        		System.out.println("Impossible de placer à cet endroit.");
        	}        	        	
    	}	

    	
    }

	public static int test4ordiVSordi(int s, int profondeur0, int profondeur1, boolean rocher, Eval e0, Eval e1) {
		Board b = new Board(new Game(), s);
		boolean fin=false;
		Player ordinateur0;
		Player ordinateur1;
		int numJoueur=0;

		Date date = new Date();

		
		//b.setRegleRocher(rocher);
		
		while(!fin) {
			ordinateur0 = b.getGame().getPlayer0();
			ordinateur1 = b.getGame().getPlayer1();

			if (numJoueur==0) {
				System.out.println("Ordinateur0:");
				System.out.println(b.toStringAccess2(ordinateur0));;
				b = b.minimax(b, ordinateur0, profondeur0, e0);
				numJoueur=1;
			} else {
				System.out.println("Ordinateur1:");
				System.out.println(b.toStringAccess2(ordinateur1));
				b = b.minimax(b, ordinateur1, profondeur1, e1);
				numJoueur=0;
			}
			
			if (b.isFinal()) {
				fin=true;
			}
		}
		
		Date date2 = new Date();
		
		System.out.println(b.toString());
		float diff = date2.getTime()-date.getTime();
		System.out.println("Temps: "+diff+" ms");
		
		int score0=b.getScore(b.getGame().getPlayer0());
		int score1=b.getScore(b.getGame().getPlayer1());
		System.out.println("Score ordinateur0: "+score0+" et score ordinateur1: "+score1);
		if(score0>score1) {
			System.out.println("Victoire ordinateur0 !");
			return 0;
		} else if (score0<score1){
			System.out.println("Victoire ordinateur1 !");
			return 1;
		} else {
			System.out.println("Ex aequo !");
			return 2;
		}
}

    public static void testGraphic(){
    	new GameUI(board).launch();
    }
}
