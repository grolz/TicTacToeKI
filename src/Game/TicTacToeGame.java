package Game;

public class TicTacToeGame {

	private String currentSymbol;
	private Board board;

	public TicTacToeGame(Board board) {
		this.currentSymbol = Cell.CROSS;
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

	public Board makeMove(int i, int j, String symbol) {
		this.board.setSymbolAt(i, j, symbol);
		this.currentSymbol = this.nextSymbol();
		return board;
	}

	public Board getBoard() {
		return this.board;
	}

	
}
