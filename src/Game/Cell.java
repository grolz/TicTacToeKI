package Game;

public class Cell {

	private String symbol;
	private int i_position;
	private int j_position;
	
	public Cell(int index_i, int index_j) {
		this(index_i, index_j, Cell.EMPTY);
	}
	public Cell(int index_i, int index_j, String circle2) {
		this.symbol = circle2;
		this.i_position = index_i;
		this.j_position = index_j;
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

	public int get_i_position() {
		return this.i_position;
	}

	public int get_j_position() {
		return this.j_position;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cell))
			return false;
		Cell cellObject =  (Cell) obj;
		return this.i_position == cellObject.get_i_position() &&
				this.j_position == cellObject.get_j_position() &&
				this.symbol == cellObject.getSymbol();
	}
}
