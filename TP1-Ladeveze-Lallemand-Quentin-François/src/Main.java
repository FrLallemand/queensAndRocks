import java.util.Scanner;

import gameElements.Board;
import gameElements.Game;
import graphics.GameUI;

public class Main {
	private static Board board;
	
    public static void main(String[] args) {
    	board = new Board(new Game(), 10);
    	testGraphic();
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

    public static void testGraphic(){
    	new GameUI(board).launch();
    }
}
