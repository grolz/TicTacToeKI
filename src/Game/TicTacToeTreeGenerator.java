package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeTreeGenerator {

	public static void main(String[] args) {
		GameNode game = new GameNode(new Board(),Cell.CIRCLE);
		System.out.println("generating");
		
		game.generateTreeForBoard();
		
		play(game);
	}

	private static void play(GameNode game) {
		game.getBoard().printBoardAsGraphic();
		Scanner scanner = new Scanner(System.in);
		String[] move = null;
		do {
			String moveAsString = scanner.next();
			move = moveAsString.split(",");
		} while (!(Cell.EMPTY.equals(game.getBoard().getCellAt(move).getSymbol())));
		Board newBoard = game.makeMove(move);
		GameNode nextNode = game.findGameNode(newBoard);
		play(nextNode.getBestMoveFor(game.nextSymbol()));
	}

	private static void printData(GameNode game) {
		for (GameNode gameNode : game.generateNextMoves()) {
			gameNode.getBoard().printBoardAsGraphic();
			gameNode.getBestMoveFor(Cell.CIRCLE);
			printData(gameNode);
		}
		
	}

	private static void generateTree(GameNode game) {
		game.generateTreeForBoard();		
	}
	
	
}
