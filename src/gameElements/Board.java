	package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Board {
	
	//ATTENTION
	//Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
	//Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
	
	private Game game;
    private Square[][] board;
    private int size;
    private int numberOfPieces;
    //ATTENTION
    //Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
    //Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
    
    public Board(Game g, int size){
        this.game = g;
        this.size = size;
        this.numberOfPieces = 0;
        this.board = new Square[size][size];
        
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j++){
                this.board[i][j] = g.getEmpty();
            }
        }
    }
    
    public Board(Game g){
        this(g, 8);
    }
	
	public Game getGame(){
		return this.game;
	}
	
	public void setGame(Game g){
		this.game = g;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setSize(int s){
		this.size = s;
	}
	
	public int getNumberOfPieces(){
		return this.numberOfPieces;
	}
	
	public void setNumberOfPieces(int n){
		this.numberOfPieces = n;
	}
	
	
	public Square[][] getBoard(){
		return this.board;
	}
	
	public void setBoard(Square[][] b){
		this.board = b;
	}
	
	
	//---------------TP1------------------------
	public Square getPiece(int i, int j) {
		return this.board[i][j];
	}
	
	public void setPiece(int i, int j, Square piece){
		this.board[i][j] = piece;
        this.numberOfPieces+=1;
	}
	
	public void removePiece(int i, int j){
        this.board[i][j] = game.getEmpty();
        this.numberOfPieces-=1;
    }
    
    public boolean isEmpty(int i, int j){
        return (this.board[i][j] instanceof Empty); 
    }

    public boolean isAccessible(int i, int j) {
        for(int k = 0; k < size; k++){
            if(this.board[k][j] instanceof Queen){
                return false;
            }
            if(this.board[i][k] instanceof Queen){
                return false;
            }
        }
        
        int k = i;
        int l = j; 
        
        while(k > 0 && l > 0){
            if(this.board[k][l] instanceof Queen){
                return false;
            }
            k--;
            l--;
        }
        
        k = i;
        l = j;
        
        while(k > 0 && l < size){
            if(this.board[k][l] instanceof Queen){
                return false;
            }
            k--;
            l++;
        }
        
        k = i;
        l = j;
        
        while(k < size && l > 0){
            if(this.board[k][l] instanceof Queen){
                return false;
            }
            k++;
            l--;
        }
        
        k = i;
        l = j;
        
        while(k < size && l < size){
            if(this.board[k][l] instanceof Queen){
                return false;
            }
            k++;
            l++;
        }
        return true;
    }

    public int numberOfAccessible() {
        int acc = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(isAccessible(i, j)){
                    acc++;
                }
            }
        }
        return acc;
    }

    public int numberOfQueens() {
        int acc = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(this.board[i][j] instanceof Queen){
                    acc++;
                }
            }
        }
        return acc;
    }	

    public boolean placeQueen(int i, int j) {
		if((i >= 0 && i<this.size) &&
		   (j >= 0 && j<this.size)){
	        if(isAccessible(i, j)){        	
	            this.board[i][j] = game.getQueen0();
	            return true;
	        }
		}
        return false;
    }
    
    
	//----------TP2-----------------------
    public ArrayList<Board> getSuccessors(){
    	ArrayList<Board> successeurs = new ArrayList<Board>();
    	
    	for(int i = 0; i < this.size; i++){
    		for(int j = 0; j < this.size; j++){
    			if(this.isAccessible(i, j)){
    				Board new_succ = this.clone();
    				new_succ.placeQueen(i, j);
    				successeurs.add(new_succ);
    			}
    		}
    	}
    	
    	return successeurs;
    }
    
	public ArrayList<Board> depthFirstSearch(Board initialState) {
		ArrayList<Board> solution = new ArrayList<>();
		if(initialState.isSolution()){
			solution.add(initialState);
			return solution;
		}
		for(Board board : initialState.getSuccessors()){
			try{
				solution.addAll(depthFirstSearch(board));
				solution.add(initialState);
			}
			catch (NoSuchElementException e) {
				System.out.println("liste vide");
				return null;
			}
		}
		throw new NoSuchElementException();
	}
	
	public ArrayList<Board> depthFirstSearch() {
		try{
			ArrayList<Board> solution = new ArrayList<>();
			ArrayList<Board> suc = this.getSuccessors();
			for(Board board : suc){
				solution.addAll(depthFirstSearch(board));
				if(!solution.isEmpty() && board.isSolution()){
					return solution;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			return solution;
		}
		catch (NoSuchElementException e) {
			System.out.println("liste vide");
			return null;
		}
	}
	
	//------------TP3----------------------
	public boolean isAccessible2(int i, int j, Player currentPlayer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public boolean placeQueen2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean placeRock2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getNumberOfRocksLeft(Player player){
		// TODO Auto-generated method stub
		return 0;  
	}
	
	public int getScore(Player player){
		// TODO Auto-generated method stub
		return 0;
	}



	//----------------------TP4&5--------------------------
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String toStringAccess(){
		String s = "";
		for(int i=0; i<this.board.length; i++){
			for(int j=0; j<this.board[0].length; j++){
					if(this.isAccessible(i, j)){
						s+=" ";					
					}
					else {
						s+="X";
					}
			}
		}
		return s;

	}
	
	public String toString(){
		String s = "";
		for(int i=0; i<this.board.length+2; i++){
			s+="-";			
		}
		s+="\n";
		for(int i=0; i<this.board.length; i++){
			s+="|";
			for(int j=0; j<this.board[0].length; j++){
				s+=this.board[i][j].toString();
			}
			s+="|\n";
		}
		for(int i=0; i<this.board.length+2; i++){
			s+="-";			
		}
		return s;
	}
	
    public Board clone(){
    	Board b = new Board(this.game, this.size);
    	b.setNumberOfPieces(this.numberOfPieces);
    	
    	for(int i = 0; i < this.size; i++){
    		for(int j = 0; j < this.size; j++){
    			if(this.board[i][j] instanceof Queen){
    				b.placeQueen(i, j);
    			}
    		}
    	}
    	return b;
    }

    public boolean isSolution(){
    	boolean result = false;
    	if(this.board.length == this.numberOfPieces){
    		result = true;
    	}
    	return result;
    }

}
