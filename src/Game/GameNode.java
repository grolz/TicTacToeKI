package Game;

import java.util.ArrayList;
import java.util.Collection;

import Test.TicTacToeGameStateNodeTest;

public class GameNode {

	private String currentSymbol;
	private Board board;
	private int move_i;
	private int move_j;
	private ArrayList<GameNode> next_moves = new ArrayList<>();
	private double circleWinnerSum;
	private double crossWinnerSum;
	private double numberOfNextMoves = 1.0;

	public GameNode(Board board) {
		this(board, Cell.CIRCLE);
	}

	public GameNode(Board board, String symbol) {
		this.currentSymbol = symbol;
		this.board = board;
	}

	public String nextSymbol() {
		switch (this.currentSymbol) {
		case Cell.CROSS:
			return Cell.CIRCLE;
		case Cell.CIRCLE:
			return Cell.CROSS;
		default:
			return Cell.EMPTY;
		}
	}

	public Board makeMove(int i, int j) {
		Board next_Board = board.copyBoard();
		this.move_i = i;
		this.move_j = j;
		next_Board.setSymbolAt(i, j, this.currentSymbol);
		return next_Board;
	}

	public Board getBoard() {
		return this.board;
	}

	public ArrayList<GameNode> generateNextMoves() {
		ArrayList<Cell> empty_cells = this.board.getFreeCells();
		ArrayList<GameNode> moves = new ArrayList<>();
		for (Cell cell : empty_cells) {
			Board next_board = makeMove(cell.get_i_position(), cell.get_j_position());
			moves.add(new GameNode(next_board, this.nextSymbol()));
		}

		return moves;
	}

	public int[] getMove() {
		int[] move = { this.move_i, this.move_j };
		return move;
	}

	public GameNode getBestMoveFor(String symbol) {
		GameNode best_move = this;
		if (this.next_moves.size() > 0) {
			best_move = next_moves.get(0);
			for (GameNode gameNode : next_moves) {
				if (symbol.equals(Cell.CIRCLE) && (best_move.getCircleWinnerSum()
						- best_move.getCrossWinnerSum()) < (gameNode.getCircleWinnerSum()
								- gameNode.getCrossWinnerSum())) {
					best_move = gameNode;
				}
				if (symbol.equals(Cell.CROSS) && (best_move.getCrossWinnerSum()
						- best_move.getCircleWinnerSum()) < (gameNode.getCrossWinnerSum()
								- gameNode.getCircleWinnerSum())) {
					best_move = gameNode;
				}
			}
		}
		return best_move;
	}

	public GameNode findGameNode(Board newBoard) {
		for (GameNode ticTacToeGameStateNode : next_moves) {
			if (newBoard.equals(ticTacToeGameStateNode.getBoard())) {
				return ticTacToeGameStateNode;
			}
		}
		return null;
	}

	public double getCircleWinnerSum() {
		return circleWinnerSum;
	}

	public double getCrossWinnerSum() {
		return crossWinnerSum;
	}

	public void generateTreeForBoard() {
		this.next_moves = this.generateNextMoves();
		for (GameNode nextGameNode : this.next_moves) {

			if (nextGameNode.getWinner() == null) {
				nextGameNode.generateTreeForBoard();
			}
			this.circleWinnerSum += nextGameNode.getCircleWinnerSum() / nextGameNode.getNumberOfNextNodes();
			this.crossWinnerSum += nextGameNode.getCrossWinnerSum() / nextGameNode.getNumberOfNextNodes();
			this.numberOfNextMoves += nextGameNode.getNumberOfNextNodes();
		}
	}

	private double getNumberOfNextNodes() {
		return this.numberOfNextMoves;
	}

	public String getWinner() {
		String winner = board.checkForWinner();
		if (Cell.CROSS.equals(winner)) {
			this.crossWinnerSum++;
		}
		if (Cell.CIRCLE.equals(winner)) {
			this.circleWinnerSum++;
		}
		return winner;
	}

	public ArrayList<GameNode> getNextMoves() {
		return this.next_moves;
	}

	public Board makeMove(String[] move) {
		return this.makeMove(Integer.valueOf(move[0]).intValue(), Integer.valueOf(move[1]).intValue());
	}
}
