package Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	private Cell[][] content = new Cell[3][3];

	private String winner;

	public Board() {
		for (int index_i = 0; index_i < content.length; index_i++) {
			for (int index_j = 0; index_j < content[index_i].length; index_j++) {
				content[index_i][index_j] = new Cell(index_i, index_j);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Board))
			return false;
		Board objBoard = (Board) obj;

		return this.getContentAsList().equals(objBoard.getContentAsList());
	}

	public void setSymbolAt(int i, int j, String newContent) {
		this.content[i][j].setSymbol(newContent);

	}

	public String getSymbolAt(int i, int j) {
		return this.content[i][j].getSymbol();
	}

	public ArrayList<Cell> getFreeCells() {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		for (Cell cell : this.getContentAsList()) {
			if (cell.getSymbol().equals(Cell.EMPTY)) {
				emptyCells.add(cell);
			}
		}
		return emptyCells;
	}

	public ArrayList<Cell> getContentAsList() {
		ArrayList<Cell> contentAsList = new ArrayList<Cell>();
		for (Cell[] cellRow : this.content) {
			contentAsList.addAll(Arrays.asList(cellRow));
		}
		return contentAsList;
	}

	public Cell getCellAt(int i, int j) {
		return content[i][j];
	}

	public void printBoard() {
		for (Cell cell : this.getContentAsList())
			System.out.print("(" + cell.get_i_position() + "," + cell.get_j_position() + "," + cell.getSymbol() + ")");

		System.out.println();
	}
	
	public void printBoardAsGraphic()
	{
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(this.getCellAt(i, j).getSymbol()+" ");
			}
			System.out.println("");
		}
		System.out.println("---------");
	}

	public Board copyBoard() {
		Board board_copy = new Board();
		for (Cell cell : this.getContentAsList()) {
			board_copy.setSymbolAt(cell.get_i_position(), cell.get_j_position(), cell.getSymbol());
		}
		return board_copy;
	}

	public String[] getRow(int i) {
		String[] row = { this.content[i][0].getSymbol(), 
				this.content[i][1].getSymbol(),
				this.content[i][2].getSymbol() };
		return row;

	}

	public String[] getColumn(int i) {
		String[] column = { this.content[0][i].getSymbol(), 
				this.content[1][i].getSymbol(),
				this.content[2][i].getSymbol() };
		return column;
	}

	public String[] getDiagonalDown() {
		String[] diagonal = { this.content[0][0].getSymbol(), 
				this.content[1][1].getSymbol(),
				this.content[2][2].getSymbol() };
		return diagonal;
	}

	public String[] getDiagonalUp() {
		String[] diagonal = { this.content[2][0].getSymbol(), 
				this.content[1][1].getSymbol(),
				this.content[0][2].getSymbol() };
		return diagonal;
	}

	public void checkThreeInLine(String[] row) throws FoundException {

		if (row[0].equals(row[1]) && 
			row[0].equals(row[2]) && 
		   !row[0].equals(Cell.EMPTY)) {
			throw new FoundException(row[0]);
		}

	}

	public String checkForWinner() {

		try {
			for (int i = 0; i < 3; i++) {
				checkThreeInLine(getRow(i));
				checkThreeInLine(getColumn(i));
			}

			checkThreeInLine(getDiagonalDown());
			checkThreeInLine(getDiagonalUp());
		} catch (FoundException e) {
			this.winner = e.getSymbol();
			return getWinner();
		}

		return null;
	}

	public String getWinner() {
		return this.winner;
	}
}
