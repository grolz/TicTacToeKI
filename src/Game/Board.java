package Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	boolean alreadySet = false;

	private Cell[][] content = new Cell[3][3];

	public Board() {
		for (int index_i = 0; index_i < content.length; index_i++) {
			for (int index_j = 0; index_j < content[index_i].length; index_j++) {
				content[index_i][index_j] = new Cell(index_i, index_j);
			}
		}
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
			System.out.print(cell.getSymbol() + "|");
		
		System.out.println();
	}

}
