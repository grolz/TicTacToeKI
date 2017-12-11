package Game;

import java.util.ArrayList;
import java.util.Collection;

import Test.TicTacToeGameStateNodeTest;

public class TicTacToeGameStateNode {

	private String currentSymbol;
	private Board board;
	private int circle_win = 0;
	private int cross_win = 0;
	private int move_i;
	private int move_j;
	private ArrayList<TicTacToeGameStateNode> next_moves = new ArrayList<>();

	public int getCircle_win() {
		return circle_win;
	}

	public void countUpCircle_win() {
		this.circle_win++;
	}

	public int getCross_win() {
		return cross_win;
	}

	public void countUpCross_win() {
		this.cross_win++;
	}

	public TicTacToeGameStateNode(Board board) {
		this(board, Cell.CIRCLE) ;
	}

	public TicTacToeGameStateNode(Board board,String symbol) {
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

	public Collection<? extends TicTacToeGameStateNode> getAllNextMoves() {
		String winner = getBoard().checkForWinner();
		if (winner == null)
		{
			ArrayList<Cell> empty_cells = this.board.getFreeCells();
			for (Cell cell : empty_cells) {
				Board next_board = makeMove(cell.get_i_position(), cell.get_j_position());

				next_moves.add(new TicTacToeGameStateNode(next_board, this.nextSymbol()));
			}
		} else {
			this.setWinnerCounter(winner);
		}
		return next_moves;
	}

	public int[] getMove() {
		int[] move = {this.move_i, this.move_j};
		return move;
	}
	
	public TicTacToeGameStateNode getBestMoveFor(String symbol)
	{
		int symbolAsInt = Cell.getSymbolAsInt(symbol);
		TicTacToeGameStateNode best_move = null;
		if (this.next_moves.size()>0) {
			best_move = this.next_moves.get(0);
			for (TicTacToeGameStateNode gameNode : next_moves) {
				if (best_move.getCross_win() < gameNode.getCross_win())
				{
					best_move = gameNode;
				}
			}
		}
		return best_move;
	}

	public void setCircle_win(int circle_win) {
		this.circle_win = circle_win;
	}

	public void setCross_win(int cross_win) {
		this.cross_win = cross_win;
	}

	public TicTacToeGameStateNode findGameNode(Board newBoard) {
		for (TicTacToeGameStateNode ticTacToeGameStateNode : next_moves) {
			if (newBoard.equals(ticTacToeGameStateNode.getBoard())) {
				return ticTacToeGameStateNode;
			}
		}
		return null;
	}

	private void setWinnerCounter(String winner) {
		switch (winner){
			case Cell.CIRCLE:
				this.circle_win++;
				break;
			case Cell.CROSS:
				this.cross_win++;
		}
	}	
}
