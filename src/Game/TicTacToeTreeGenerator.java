package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeTreeGenerator {

	public static void main(String[] args) {
		GameNode game = new GameNode(new Board(),Cell.CIRCLE);
		System.out.print("generating");
		
		game.generateTreeForBoard();
		
		play(game);
	}

	private static void play(GameNode game) {
		game.getBoard().printBoardAsGraphic();
		Scanner scanner = new Scanner(System.in);
		String moveAsString = scanner.next();
				
		String[] move = moveAsString.split(",");
		Board newBoard = game.makeMove(Integer.valueOf(move[0]).intValue(), Integer.valueOf(move[1]).intValue());
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
