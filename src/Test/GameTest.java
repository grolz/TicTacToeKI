package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.zip.CRC32;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import Game.Board;
import Game.Cell;
import Game.TicTacToeGame;

public class GameTest {

	@Test
	public void givienInitialGameFirstMoveIsCROSS() {
		TicTacToeGame game = new TicTacToeGame(new Board());

		Assert.assertEquals(Cell.CIRCLE, game.nextSymbol());
	}

	@Test
	public void givenInitialGameTheSecondMoveIsCIRCLE() throws Exception {
		TicTacToeGame game = new TicTacToeGame(new Board());
		game.makeMove(0, 0, "Somethig");
		Assert.assertEquals(Cell.CROSS, game.nextSymbol());
	}

	@Test
	@Ignore
	public void givenInitialGameAfterFirstMoveTheFilledCellIsNotPartOfTheEmptyCells()
			throws Exception {
		
	}

	@Test
	public void MakingAMoveRequiresCoordinatesAndSymbolAndThenTheCellIsFilledWithThisSymbol()
			throws Exception {
		TicTacToeGame game = new TicTacToeGame(new Board());
		String symbol = Cell.CIRCLE;
		int i = 1, j = 1;

		game.makeMove(i, j, symbol);

		Assert.assertEquals(symbol, game.getBoard().getSymbolAt(i, j));
	}
	
	@Test
	public void givenAGameAllPossibleNextMovesShouldBeGeneratedIntoaNewList() throws Exception {
		Board board = getPrparedBoard();
		
		TicTacToeGame game = new TicTacToeGame(board);
		
		ArrayList<Cell> free_cells = board.getFreeCells();
		ArrayList<Board> next_Moves = new ArrayList<Board>();
		String nextSymbol = game.nextSymbol();
		for (Cell cell : free_cells) {			
			next_Moves.add(game.makeMove(cell.get_i_position(), cell.get_j_position(), nextSymbol));
		}
		
		Assert.assertEquals(3, next_Moves.size());
		Board board_variant_1 = this.getPrparedBoard();
		board_variant_1.setSymbolAt(0, 2, Cell.CROSS);
		Assert.assertTrue(next_Moves.get(0).getContentAsList().contains(board_variant_1.getContentAsList()));
	}
	
	@Test
	public void givenTwoCellsTheyAreEqualWhenPositionAndSymbolAreEquals() throws Exception {
		Cell cell_1 = new Cell(1,2,Cell.CIRCLE);
		Cell cell_2 = new Cell(1,2,Cell.CIRCLE);
		
		Assert.assertEquals(cell_1, cell_2);
	}

	private Board getPrparedBoard() {
		Board board = new Board();
		board.setSymbolAt(0, 0, Cell.CIRCLE);
		board.setSymbolAt(0, 1, Cell.CROSS);
		board.setSymbolAt(1, 0, Cell.CIRCLE);
		board.setSymbolAt(0, 2, Cell.CROSS);
		board.setSymbolAt(1, 1, Cell.CIRCLE);
		board.setSymbolAt(2, 2, Cell.CROSS);
		return board;
	}
	
	@Test
	@Ignore
	public void printBoard() throws Exception {
		Board board = new Board();
		board.printBoard();
		
	}
	
}
