package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeTreeGenerator {

	public static void main(String[] args) {
		TicTacToeGameStateNode game = new TicTacToeGameStateNode(new Board(),Cell.CIRCLE);
		System.out.print("generating");
		generateTree(game);
		
		play(game);
	}

	private static void play(TicTacToeGameStateNode game) {
		game.getBoard().printBoardAsGraphic();
		Scanner scanner = new Scanner(System.in);
		String moveAsString = scanner.next();
				
		String[] move = moveAsString.split(",");
		Board newBoard = game.makeMove(Integer.valueOf(move[0]).intValue(), Integer.valueOf(move[1]).intValue());
		TicTacToeGameStateNode nextNode = game.findGameNode(newBoard);
		play(nextNode.getBestMoveFor(game.nextSymbol()));
	}

	private static void printData(TicTacToeGameStateNode game) {
		for (TicTacToeGameStateNode gameNode : game.getAllNextMoves()) {
			gameNode.getBoard().printBoardAsGraphic();
			gameNode.getBestMoveFor(Cell.CIRCLE);
			printData(gameNode);
		}
		
	}

	private static void generateTree(TicTacToeGameStateNode game) {
		ArrayList<Board> boards = new ArrayList<Board>();
		
		for (TicTacToeGameStateNode nextGameNode : game.getAllNextMoves())
		{			
			generateTree(nextGameNode);
			game.setCircle_win(game.getCircle_win() + nextGameNode.getCircle_win());
			game.setCross_win(game.getCross_win() + nextGameNode.getCross_win());
		}		
	}
	
	
}
