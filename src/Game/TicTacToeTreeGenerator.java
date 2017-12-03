package Game;

import java.util.ArrayList;

public class TicTacToeTreeGenerator {

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame(new Board(),Cell.CIRCLE);
		
		generateTree(game);
		
	}

	private static void generateTree(TicTacToeGame game) {
		ArrayList<Board> boards = new ArrayList<Board>();
		
		for (Board board : game.getAllNextMoves())
		{
			board.printBoard();
			String winner = board.checkForWinner();
			if (winner != null)
			{
				System.out.println(winner);
				break;
			}
			
			TicTacToeGame next_game = new TicTacToeGame(board,game.nextSymbol());
			generateTree(next_game);
		}		
	}
}
