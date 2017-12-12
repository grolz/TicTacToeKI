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
	private String winner;

	public GameNode(Board board) {
		this(board, Cell.CIRCLE) ;
	}

	public GameNode(Board board,String symbol) {
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
	public String currentSymbol()
	{
		return this.currentSymbol;
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

	public Collection<? extends GameNode> generateNextMoves() {
			ArrayList<Cell> empty_cells = this.board.getFreeCells();
			for (Cell cell : empty_cells) {
				Board next_board = makeMove(cell.get_i_position(), cell.get_j_position());

				next_moves.add(new GameNode(next_board, this.nextSymbol()));
			}
		 
		return next_moves;
	}

	public int[] getMove() {
		int[] move = {this.move_i, this.move_j};
		return move;
	}
	
	public GameNode getBestMoveFor(String symbol)
	{
		GameNode best_move = this;
		if (this.next_moves.size() > 0) {
			for (GameNode gameNode : next_moves) {
				if (symbol.equals(gameNode.getWinner()) ||
					null == gameNode.getWinner()) {
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

	
	public String generateTreeForBoard() {
		for (GameNode nextGameNode : this.generateNextMoves())
		{
			this.winner = nextGameNode.getWinner();
			if (this.winner == null) {
				this.winner = nextGameNode.generateTreeForBoard();
			} 
			if (this.winner != null)
			{
				break;
			}
		}
		
		return this.winner;
	}

	public String getWinner() {
		return board.checkForWinner();
	}

	public ArrayList<GameNode> getNextMoves() {
		return this.next_moves;
	}	
}
