package application;

import javafx.util.Pair;

public class TicTacToe {
	
	private String[][] board;
	private int turn;
	private boolean isOver;
	private String winner;
	
	public TicTacToe() {
		init();
	}
	
	public void init() {
		board = new String[3][3];
		initBoard(board);
		turn = 1;
		isOver = false;
		winner = "";
	}
	
	public void initBoard(String[][] board) {
		for(int i= 0; i<board.length; i++) {
			for(int j= 0; j<board[i].length; j++) {
				board[i][j] = "-";
			}
		}
	}
	
	public boolean isOpen(int col, int row) {
		if(board[col][row].equals("-")) { 
			return true;
		}
		return false;
	}
	
	
	public Pair<Integer, Integer> setTileAI(String s) {
		Pair<Integer, Integer> temp = aiMove();
		checkWinner(temp.getKey(),  temp.getValue(), s);
		return temp;
	}
	
	// Bad AI
	public Pair<Integer, Integer> aiMove() {
		Pair<Integer, Integer> temp = null;
		boolean breakLoop = false;
		if(!isOver) {
		    for(int i=0; i<board.length; i++) {
	    		 if(breakLoop) {
	    			 break;
	    		 }
		    	 for(int j=0; j<board[i].length; j++) {  
		    		 if(board[i][j].equals("-")) {
		    			 setTile(i,j, "O"); 
		    			 breakLoop = true;	
		    			 temp = new Pair<Integer, Integer>(i,j);
		    			 break;
		    		 }
		    		 
		    	 }
		    }
		}
		return temp;
}
	
	// Make Move
	public void setTile(int col, int row, String s) {
		board[col][row] = s;
		turn++;
		checkWinner(col, row, s);
	}
	
	// Winning conditions
	public boolean checkWinner(int x, int y, String move) {
		if(turn >= 6 ) {
			int x_1 = x;
			int y_1 = y;
			
			if(x == 0) {
				x++;
				
			}else if(x == board.length-1) {
				x--;
			}
			
			if(board[x-1][y] == move &&  board[x][y] == move && board[x+1][y] == move) {
				isOver = true;
			}
			
			if(y == 0) {
				y++;
			}else if(y == board[0].length-1) {
				y--;
				
			}
			
			if(board[x_1][y-1] == move &&  board[x_1][y] == move && board[x_1][y+1] == move) {
				isOver = true;
			}
			
			
			// check diagonals
			if(board[x-1][y-1] == move &&  board[x][y] == move && board[x+1][y+1] == move) {
				isOver = true;
			}
			if(board[x+1][y-1] == move &&  board[x][y] == move && board[x-1][y+1] == move) {
				isOver = true;
			}
			if(isOver) {
				winner = "Congratulations, " + move + " wins the game";
			}
			
		}
		if(turn >= 10 && !isOver) {
			winner = "Draw!";
			isOver = true;
		}
		return isOver;
	}
	
	public boolean getIsOver() {
		return isOver;
	}
	
	public String getWinner() {
		return winner;
	}
	
	//odd # = X, even # = O
	public int getTurn() {
		return turn;
	}
	
	public String toString() {
		String b = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				String s = board[i][j];
				b += s+" ";
			}
			b += "\n";
		}
		return b;
	}

	public String move() {
		if(turn %2 == 0) {
			return "O";
		}
		return "X";
	}
}
