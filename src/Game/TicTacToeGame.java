package Game;

import java.util.ArrayList;
import java.util.Collection;

public class TicTacToeGame {

	private String currentSymbol;
	private Board board;
	private int circle_win = 0;
	private int cross_win = 0;

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

	public TicTacToeGame(Board board) {
		this(board, Cell.CIRCLE) ;
	}

	public TicTacToeGame(Board board,String symbol) {
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
		next_Board.setSymbolAt(i, j, this.currentSymbol);		
		return next_Board;
	}

	public Board getBoard() {
		return this.board;
	}

	public Collection<? extends Board> getAllNextMoves() {
		ArrayList<Board> next_moves = new ArrayList<Board>();
		ArrayList<Cell> empty_cells = this.board.getFreeCells();
		for (Cell cell: empty_cells)
		{
			Board next_board = makeMove(cell.get_i_position(), cell.get_j_position());
			
			next_moves.add(next_board);	
		}
		return next_moves;
	}

	
}
