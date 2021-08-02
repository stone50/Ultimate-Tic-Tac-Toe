
import java.util.*;



class ultimateTicTacToe {	
	Scanner in = new Scanner(System.in);
	static int player = -1;
	static int bigBoard = 0;
	static int smallBoard = 0;
	static Board board = new Board();
	static boolean win = false;
	static boolean allWin = false;
	static boolean replay = true;
	public static void main(String[] args) {
		firstMove(board);
		playGame();
 	}
 	
 	
 	public static void playGame() {
 		while(!allWin && replay) {
			while(!win) {
				printKey();
				bigBoard = smallBoard;
				System.out.println("Where would you like to go?");
				smallBoard = input()-1;
				if(!board.setItem(bigBoard, smallBoard, player)) {
					player = player*-1;	
					if (board.getItem(bigBoard, smallBoard) == -1){
						System.out.println("An X is already there, please chose again.");
					}
					else {
						System.out.println("An O is already there, please choose again.");
					}
					if(board.checkWon(board.getSmallBoard(bigBoard)) == 0) {
						smallBoard = bigBoard;
					} else {
						System.out.println("New board was chosen: " + (smallBoard+1));
					}
				}
				player = player*-1;
				board.displayBoard();
				win = board.checkWin(player*-1, board.getSmallBoard(bigBoard));
				if(win) {
					for(int i = 0; i < 9; i++) {
						board.replaceItem(bigBoard, i, player*-1);
					}
					board.displayBoard();
				}
			}
			win = false;
			int[] games = new int[9];
			for(int i = 0; i < 9; i ++) {
				games[i] = board.checkWon((int[])board.getSmallBoard(i));
			}
			if(board.checkWin(1, games)) {
				System.out.println("Player 2 has wone!");
				allWin = true;
				System.out.println();
				System.out.println("Would you like to play again? (y/n)");
				Scanner in = new Scanner(System.in);
				String answer = in.nextLine();
				if(answer.equals("y")) {
					board = new Board();
					allWin = false;
					for(int i = 0; i < 30; i++) {
					System.out.println();
					}
					firstMove(board);
				} else {
					replay = false;
					for(int i = 0; i < 30; i++) {
					System.out.println();
					}
					System.out.println("Thank you for playing!");
				}
			} else if(board.checkWin(-1, games)) {
				System.out.println("Player 1 has wone!");
				allWin = true;
				System.out.println();
				System.out.println("Would you like to play again? (y/n)");
				Scanner in = new Scanner(System.in);
				String answer = in.nextLine();
				if(answer.equals("y")) {
					board = new Board();
					allWin = false;
					for(int i = 0; i < 30; i++) {
					System.out.println();
					}
					firstMove(board);
				} else {
					replay = false;
					for(int i = 0; i < 30; i++) {
					System.out.println();
					}
					System.out.println("Thank you for playing!");
				}
			}
		}
 	}
 	
 	
 	public static int input()
	{
    	Scanner in = new Scanner(System.in);
    	return in.nextInt();
	}
	
	public static void printKey() {
		System.out.println("Key:   7 8 9");
		System.out.println("       4 5 6");
		System.out.println("       1 2 3");
	}
	
	public static Board play(Board board) {
		board.displayBoard();
		System.out.println("Where do you want to start?");
		bigBoard = input();
		System.out.println("Where would you like to go?");
		smallBoard = input();
		board.setItem(bigBoard-1, smallBoard-1, player);
		player = player*-1;
		return board;
	}
	   
	   
	    public static void firstMove(Board board) {
	    board.displayBoard();
   		printKey();
		System.out.println("Where do you want to start?");
		bigBoard = input()-1;
		System.out.println("Where would you like to go?");
		smallBoard = input()-1;
		board.setItem(bigBoard, smallBoard, player);
		player = player*-1;
		board.displayBoard();
    }
}



class Board {
	
	static int board[][] = new int[9][9];
	
	Board() {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				board[row][col] = 0;
			}
		}
	}
	
	
	public static boolean checkWin(int player, int[] board) {
		if(board[0] == player && board[1] == player && board[2] == player) {
			return true;
		} else if(board[3] == player && board[4] == player && board[5] == player) {
			return true;
		} else if(board[6] == player && board[7] == player && board[8] == player) {
			return true;
		} else if(board[0] == player && board[3] == player && board[6] == player) {
			return true;
		} else if(board[1] == player && board[4] == player && board[7] == player) {
			return true;
		} else if(board[2] == player && board[5] == player && board[8] == player) {
			return true;
		} else if(board[0] == player && board[4] == player && board[8] == player) {
			return true;
		} else if(board[2] == player && board[4] == player && board[6] == player) {
			return true;
		} else {
    		return false;
		}
    }
    
    public static int checkWon(int[] board) {
    	int count = 0;
    	for(int i = 0; i < 9; i++) {
    		count += board[i];
    	}
    	if(count == 9) {
    		return 1;
    	} else if(count == -9) {
    		return -1;
    	} else {
    		return 0;
    	}
    }
    
    public static void displayBoard() {
    	for(int i = 0; i < 5; i++) {System.out.println();} 
    	System.out.println(getMove(6, 6) + ""+ getMove(6, 7) + "" + getMove(6, 8) + " " + getMove(7, 6) + "" + getMove(7, 7) + "" + getMove(7, 8) + " " + getMove(8, 6) + "" + getMove(8, 7) + "" + getMove(8, 8)); 
    	System.out.println(getMove(6, 3) + ""+ getMove(6, 4) + "" + getMove(6, 5) + " " + getMove(7, 3) + "" + getMove(7, 4) + "" + getMove(7, 5) + " " + getMove(8, 3) + "" + getMove(8, 4) + "" + getMove(8, 5));
    	System.out.println(getMove(6, 0) + ""+ getMove(6, 1) + "" + getMove(6, 2) + " " + getMove(7, 0) + "" + getMove(7, 1) + "" + getMove(7, 2) + " " + getMove(8, 0) + "" + getMove(8, 1) + "" + getMove(8, 2));
		System.out.println();
    	System.out.println(getMove(3, 6) + ""+ getMove(3, 7) + "" + getMove(3, 8) + " " + getMove(4, 6) + "" + getMove(4, 7) + "" + getMove(4, 8) + " " + getMove(5, 6) + "" + getMove(5, 7) + "" + getMove(5, 8));
    	System.out.println(getMove(3, 3) + ""+ getMove(3, 4) + "" + getMove(3, 5) + " " + getMove(4, 3) + "" + getMove(4, 4) + "" + getMove(4, 5) + " " + getMove(5, 3) + "" + getMove(5, 4) + "" + getMove(5, 5));
    	System.out.println(getMove(3, 0) + ""+ getMove(3, 1) + "" + getMove(3, 2) + " " + getMove(4, 0) + "" + getMove(4, 1) + "" + getMove(4, 2) + " " + getMove(5, 0) + "" + getMove(5, 1) + "" + getMove(5, 2));
		System.out.println();
    	System.out.println(getMove(0, 6) + ""+ getMove(0, 7) + "" + getMove(0, 8) + " " + getMove(1, 6) + "" + getMove(1, 7) + "" + getMove(1, 8) + " " + getMove(2, 6) + "" + getMove(2, 7) + "" + getMove(2, 8));
 		System.out.println(getMove(0, 3) + ""+ getMove(0, 4) + "" + getMove(0, 5) + " " + getMove(1, 3) + "" + getMove(1, 4) + "" + getMove(1, 5) + " " + getMove(2, 3) + "" + getMove(2, 4) + "" + getMove(2, 5));
    	System.out.println(getMove(0, 0) + ""+ getMove(0, 1) + "" + getMove(0, 2) + " " + getMove(1, 0) + "" + getMove(1, 1) + "" + getMove(1, 2) + " " + getMove(2, 0) + "" + getMove(2, 1) + "" + getMove(2, 2));
		System.out.println(); 
    	System.out.println(); 
    	System.out.println(); 
    }
    
    public static String getMove(int row, int col) {
    	if(board[row][col] == -1) {
    		return "X";
    	} else if(board[row][col] == 1) {
    		return "O";
    	} else {
    		return "-";
    	}
    }
    
    static int[][] getBoard() {
    	return board;
    }
    
    static void setBoard(int[][] b) {
    	board = b;
    }
    
    static int getItem(int row, int col) {
    	return board[row][col];
    }
    
    static boolean setItem(int row, int col, int item) {
    	if(board[row][col] == 0) {
    		board[row][col] = item;
    		return true;
    	} else {
    		return false;
    	}
    }
    
    static void replaceItem(int row, int col, int item) {
    	board[row][col] = item;
    }
    
    static int[] getSmallBoard(int a) {
    	return board[a];
    }

    
    
}