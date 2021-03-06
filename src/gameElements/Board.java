	package gameElements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private int rocksPlayer0, rocksPlayer1;
    
    private static int queenValue = 5;
    private static int rockValue = 2;
    
    //ATTENTION
    //Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
    //Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
    
    public Board(Game g, int size){
        this.game = g;
        this.size = size;
        this.numberOfPieces = 0;
        this.board = new Square[size][size];
        this.rocksPlayer0 = size;
        this.rocksPlayer1 = size;
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
        
        while(k >= 0 && l >= 0){
            if(this.board[k][l] instanceof Queen){
                return false;
            }
            k--;
            l--;
        }
        
        k = i;
        l = j;
        
        while(k >= 0 && l < size){
            if(this.board[k][l] instanceof Queen){
                return false;
            }
            k--;
            l++;
        }
        
        k = i;
        l = j;
        
        while(k < size && l >= 0){
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
	            this.setPiece(i, j, game.getQueen0());
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
    
    public ArrayList<Board> getNewSuccessors(){
    	ArrayList<Board> successeurs = new ArrayList<Board>();
    	int nextCol = this.numberOfQueens()+1;
    	
    	for(int i = 0; i < this.size; i++){
			if(this.isAccessible(nextCol, i)){
				Board new_succ = this.clone();
				new_succ.placeQueen(nextCol, i);
				successeurs.add(new_succ);
			}
    	}    	
    	return successeurs;
    }

    public ArrayList<Board> depthFirstSearch(Board initialState) {
		//System.out.println(initialState);
		ArrayList<Board> solution = new ArrayList<Board>();
		if(initialState.isSolution()){
			solution.add(initialState);
			return solution;
		}
		try{
			for(Board board : initialState.getSuccessors()){
					solution.addAll(depthFirstSearch(board));
					if(!solution.isEmpty() && solution.get(0).isSolution()){
						solution.add(initialState);
						return solution;
				}
			}
		}
		catch (NoSuchElementException e) {
			System.err.println("BLAAARG !");
		}
		
		Collections.reverse(solution);
		return solution;
	}
    
	public String solutionSteps(Board b){
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Board> solution = this.depthFirstSearch(b);
		
		for(Board step : solution){
			sb.append(step.toString());
		}
		
		return sb.toString();
	}
	
	public ArrayList<Board> depthFirstSearch() {
			return depthFirstSearch(new Board(this.game, this.size));
	}
	
	public ArrayList<Board> depthFirstSearch2(Board initialState) {
		ArrayList<Board> solution = new ArrayList<Board>();
		if(initialState.isSolution()){
			solution.add(initialState);
			return solution;
		}
		try{
			for(Board board : initialState.getNewSuccessors()){
				solution.addAll(depthFirstSearch2(board));
				if(!solution.isEmpty() && solution.get(0).isSolution()){
					solution.add(initialState);
					return solution;
				}
			}
		}
		catch (NoSuchElementException e) {
			System.err.println("BLAAARG !");
		}
		
		Collections.reverse(solution);
		return solution;    	
	}
	
	public ArrayList<Board> depthFirstSearch2() {
		return depthFirstSearch2(new Board(this.game, this.size));
	}
	
	public int[] boardToArray(){
		int[] tab = new int[this.size];
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				if(!this.isEmpty(i, j)){
					tab[i] = j;
				}
				else {
					tab[i] = -1;
				}
			}
		}
		return tab;
	}

	public Board arrayToBoard(int[] array){
		Board b = new Board(new Game(), array.length);
		
		for(int i=0; i<array.length; i++){
			if(array[i] != -1){
				b.placeQueen(i, array[i]);
			}		
		}
		return b;
	}
	
	public ArrayList<int[]> getArraySuccessors(int[] array){
    	ArrayList<int[]> successeurs = new ArrayList<>();
    	int nextCol = this.numberOfQueens()+1;
    	
    	for(int i = 0; i < array.length; i++){
    		if(array[i] == -1){
				if(this.isAccessible(nextCol, i)){
					int[] new_succ = array;
					new_succ[nextCol] = i;
					successeurs.add(new_succ);
				}
    		}
    	}    	
    	return successeurs;
	}
	
	public boolean isSolutionArray(int[] array){
		boolean result = true;
    	for(int i = 0; i < array.length; i++){
    		if(array[i] == -1){
    			result = false;
    			break;
    		}
    	}
    	return result;
	}
	
	public ArrayList<int[]> depthFirstSearchArray(int[] initialState) {
		ArrayList<int[]> solution = new ArrayList<>();
		if(isSolutionArray(initialState)){
			solution.add(initialState);
			return solution;
		}
		try{
			for(int[] i : getArraySuccessors(initialState)){
				solution.addAll(depthFirstSearchArray(i));
				if(!solution.isEmpty() && isSolutionArray(solution.get(0))){
					solution.add(initialState);
					return solution;
				}
			}
		}
		catch (NoSuchElementException e) {
			System.err.println("BLAAARG !");
		}
		
		Collections.reverse(solution);
		return solution;    	
	}
	
	public ArrayList<int[]> depthFirstSearchArray() {
		int[] solution = new int[this.size];
		for(int i=0; i<this.size; i++){
			solution[i] = -1;
		}
		return depthFirstSearchArray(solution);
	}
	
	//------------TP3----------------------
	public int getRocksPlayer0() {
		return rocksPlayer0;
	}

	public void setRocksPlayer0(int rocksPlayer0) {
		this.rocksPlayer0 = rocksPlayer0;
	}

	public int getRocksPlayer1() {
		return rocksPlayer1;
	}

	public void setRocksPlayer1(int rocksPlayer1) {
		this.rocksPlayer1 = rocksPlayer1;
	}

	
	public boolean isAccessible2(int i, int j, Player currentPlayer) {		
		boolean result=false;
		Queen reineEnnemie;
		if(currentPlayer.getNumber() == 0) {
			reineEnnemie = this.game.getQueen1();
		} else {
			reineEnnemie = this.game.getQueen0();
		}
		
		if (this.isEmpty(i, j)) {
			result = true;
			for (int x=-1; x<=1; x++) {
				for (int y=-1; y<=1; y++) {
					int k=i;
					int l=j;
					boolean stop=false;
					while (!stop && result) {
						k += y;
						l += x;
						if ((k<0 || k>=this.size)
						|| (l<0 || l>=this.size)
						|| (y==0 && x==0)) {
							stop=true;
						} else {
							if(this.getPiece(k,l).toString().equals(reineEnnemie.toString())) {
								result=false;
								stop=true;
							} else if(!this.isEmpty(k, l)) {
								stop=true;
							}
						}
					}
				}
			}
			
		}
		return result;
	}
	
    public int numberOfAccessible2(Player player) {
        int acc = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(isAccessible2(i, j, player)){
                    acc++;
                }
            }
        }
        return acc;
    }

	public String toStringAccess2(Player player){
		String s = "";
		for(int i=0; i<this.size; i++){
			s += "| ";
			for(int j=0; j<this.size; j++){
					if(!this.isAccessible2(i, j, player) && this.isEmpty(i, j)){							
							s+="X ";
					}
					else if(this.isEmpty(i, j)){							
							s+="  ";
					}
					else {
							s+="  ";							
					}
					s += " | ";

			}
			s += "\n";
		}
		return s;
	}

	public boolean placeQueen2(int i, int j, Player player) {
		if((i >= 0 && i<this.size) &&
		   (j >= 0 && j<this.size)){
		        if(isAccessible2(i, j, player)){        	
		        	if(player.getNumber() == 0){		        		
		        		this.setPiece(i, j, game.getQueen0());
		        	}
		        	else {		        		
		        		this.setPiece(i, j, game.getQueen1());
		        	}		        	
		            return true;
		        }
			}
		return false;
	}

	public boolean placeRock2(int i, int j, Player player) {
		if((i >= 0 && i<this.size) &&
		   (j >= 0 && j<this.size)){
	        if(this.isEmpty(i, j)){        	
	        	if(player.getNumber() == 0){		        		
	        		if(this.getNumberOfRocksLeft(player) > 0){
		        		this.setPiece(i, j, game.getRock0());
		        		this.useRock(player);
	        		}
	        	}
	        	else {
        			if(this.getNumberOfRocksLeft(player) > 0){
			    		this.setPiece(i, j, game.getRock1());
		        		this.useRock(player);
        			}	
    			}
	            return true;
	        }
		}
		return false;
	}
	
	public int getNumberOfRocksLeft(Player player){
		if(player == this.game.getPlayer0()){
			return this.rocksPlayer0;
		}
		else{
			return this.rocksPlayer1;
		}
	}
	
	public void useRock(Player player){
		if(player == this.game.getPlayer0()){
			this.rocksPlayer0--;
		}
		else{
			this.rocksPlayer1--;
		}
	}
	
	public int getScore(Player player){
		int scoreQueens = this.numberOfQueens2(player) * queenValue;
		int scoreRocks = this.numberOfRocks2(player) * rockValue;		
		return scoreQueens + scoreRocks;
	}
	
	public int numberOfQueens2(Player player){
        int acc = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(this.getPiece(i, j) instanceof Queen && this.board[i][j].getPlayer() == player){
                    acc++;
                }
            }
        }
        return acc;
	}

	public int numberOfRocks2(Player player){
        int acc = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(this.getPiece(i, j) instanceof Rock && this.board[i][j].getPlayer() == player){
                    acc++;
                }
            }
        }
        return acc;
	}


	//----------------------TP4&5--------------------------
	/*public boolean isFinal() {
		if(this.rocksPlayer0 == 0 && this.numberOfAccessible2(this.game.getPlayer0()) == 0){
			return true;
		}
		
		if(this.rocksPlayer1 == 0 && this.numberOfAccessible2(this.game.getPlayer1()) == 0){
			return true;
		}
		
		return false;
	}*/
	
	public boolean isFinal() {
		int nombreRocher0 = this.getNumberOfRocksLeft(this.getGame().getPlayer0());
		int nombreRocher1 = this.getNumberOfRocksLeft(this.getGame().getPlayer1());
		int nombreAccesible0 = this.numberOfAccessible2(this.getGame().getPlayer0());
		int nombreAccesible1 =this.numberOfAccessible2(this.getGame().getPlayer1());
		boolean placeLibre = this.getNumberOfPieces() != (this.size*this.size);
				
		return (nombreRocher0==0 && nombreAccesible0==0) || (nombreRocher1==0 && nombreAccesible1==0) || !placeLibre;
	}

	public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
		ArrayList<Board> successeurs = b.getSuccessors2(currentPlayer);
		float score_max = Float.NEGATIVE_INFINITY;
		float score;
		Board e_sortie = new Board(new Game(), b.size);
		Player adversaire;
		if (currentPlayer.getNumber()==0) {
			adversaire = b.getGame().getPlayer1();
		} else {
			adversaire = b.getGame().getPlayer0();
		}

		
		if(b.isFinal()){
			return b;
		}
		for(Board s : successeurs){
			score = evaluation(s, currentPlayer, minimaxDepth, evaluation, adversaire);
			if(score >= score_max){
				e_sortie = s;
				score_max = score;
			}
		}
		return e_sortie;
	}
	
	public float evaluation(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation, Player playing) {
		ArrayList<Board> successeurs = new ArrayList<>();
		Player adversaire;
		if (playing.getNumber()==0) {
			adversaire = b.getGame().getPlayer1();
		} else {
			adversaire = b.getGame().getPlayer0();
		}
		
		if(b.isFinal()){
			if(evaluation.getEval(currentPlayer, b) > 0){
				return Float.POSITIVE_INFINITY;
			}
			else if(evaluation.getEval(currentPlayer, b) == 0){
				return 0;
			}
			else{
				return Float.NEGATIVE_INFINITY;
			}
		}
		
		if(minimaxDepth == 0){
			return evaluation.getEval(currentPlayer, b);
		}
		
		float score_max = Float.NEGATIVE_INFINITY;
		float score_min = Float.POSITIVE_INFINITY;
		successeurs = b.getSuccessors2(playing);
		if(currentPlayer.getNumber()==playing.getNumber()){
			//System.out.println(successeurs);
			for(Board s : successeurs){
				score_max = Math.max(score_max,evaluation(s,currentPlayer,minimaxDepth-1,evaluation,adversaire));
			}			
			return score_max;
		} else {			
			for(Board s : successeurs){
				score_min = Math.min(score_min,evaluation(s,currentPlayer,minimaxDepth-1,evaluation,adversaire));
			}			
			return score_min;
		}
	}

	public ArrayList<Board> getSuccessors2(Player p){
    	ArrayList<Board> successeurs = new ArrayList<Board>();
    	
    	for(int i = 0; i < this.size; i++){
    		for(int j = 0; j < this.size; j++){
    			if(this.isAccessible2(i, j, p)){
    				Board new_succ = this.clone();
    				new_succ.placeQueen2(i, j, p);
    				successeurs.add(new_succ);
    			}
    			if(this.isEmpty(i, j) && getNumberOfRocksLeft(p) > 0){
    				Board new_succ = this.clone();
    				new_succ.placeRock2(i, j, p);
    				successeurs.add(new_succ);
    			}
    		}
    	}    	
    	return successeurs;
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
		String s = "\n";
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
    	Board b = new Board(this.getGame(), this.size);
    	b.setNumberOfPieces(this.numberOfPieces);
    	
    	for(int i = 0; i < this.size; i++){
    		for(int j = 0; j < this.size; j++){
    			b.board[i][j] = this.getBoard()[i][j];    			 
    		}
    	}
    	b.setRocksPlayer0(this.getRocksPlayer0());
    	b.setRocksPlayer1(this.getRocksPlayer1());
    	
    	return b;
    }
    
    public boolean isSolution(){
    	boolean result = false;
    	if(this.numberOfQueens() == this.size){
    		result = true;
    	}
    	return result;
    }

}
