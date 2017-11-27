package Game;

public class Cell {

	private String symbol;
	
	public Cell(int index_i, int index_j) {
		this.symbol = Cell.EMPTY;
	}
	public static final String CIRCLE = "Circle";
	public static final String CROSS = "Cross";
	public static final String EMPTY = "Empty";

	public void setSymbol(String newContent) {
		this.symbol = newContent;
	}

	public String getSymbol() {
		return this.symbol;
	}

}
