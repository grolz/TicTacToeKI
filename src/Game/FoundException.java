package Game;

public class FoundException extends Exception {

	private String symbol;

	public FoundException(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}	
}
