package Game;

public class TicTacToeGame {

	private String currentSymbol;
	private Board board;

	public TicTacToeGame() {
		this.currentSymbol = Cell.CROSS;
		this.board = new Board();
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

	public void makeMove(int i, int j, String symbol) {
		this.board.setXYPosition(i, j, symbol);
		this.currentSymbol = this.nextSymbol();
	}

	public Board getBoard() {
		return this.board;
	}

	
}
