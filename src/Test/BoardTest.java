package Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Board;
import Game.Cell;
import Game.FoundException;

public class BoardTest {
	
	public static Board getPrparedBoard() {
		Board board = new Board();
		board.setSymbolAt(0, 0, Cell.CIRCLE);
		board.setSymbolAt(0, 1, Cell.CROSS);
		board.setSymbolAt(1, 0, Cell.CIRCLE);
		board.setSymbolAt(0, 2, Cell.CROSS);
		board.setSymbolAt(1, 1, Cell.CIRCLE);
		board.setSymbolAt(2, 2, Cell.CIRCLE);
		return board;
	}

	@Test
	public void givenPuttingCircleInPosition00ThenGetPositionRerurnsCircle() {

		Board board = new Board();

		board.setSymbolAt(0, 0, Cell.CIRCLE);

		Assert.assertEquals(Cell.CIRCLE, board.getSymbolAt(0, 0));

	}

	@Test
	public void givenPuttingCirossInPosition00ThenGetPositionRerurnsCross() {

		Board board = new Board();

		board.setSymbolAt(0, 0, Cell.CROSS);

		Assert.assertEquals(Cell.CROSS, board.getSymbolAt(0, 0));

	}

	@Test
	public void givenACircleAt00AndaCrossAt01getXPosition00ShouldReturnACircle() {
		Board board = new Board();

		board.setSymbolAt(0, 0, Cell.CIRCLE);
		board.setSymbolAt(0, 1, Cell.CROSS);
		Assert.assertEquals(Cell.CIRCLE, board.getSymbolAt(0, 0));
	}

	@Test
	public void givenIntialBoardThenGetXPositionAsEmpty() {

		Board board = new Board();

		Assert.assertEquals(Cell.EMPTY, board.getSymbolAt(0, 0));
	}
	
	@Test
	public void givenInitialBoardAListOfEmptyCellsShouldHaveLength9() throws Exception {
		Board board = new Board();
		
		Assert.assertEquals(board.getFreeCells().size(), 9);
	}
	
	@Test
	public void givenABoardTheContentAsListShouldHave9Elements() throws Exception {
		Board board = new Board();
		Assert.assertEquals( 9,board.getContentAsList().size());
	}
	
	@Test
	public void givenInitialBoardACellAt10ShouldBeInTheListOfEMPTYCells() throws Exception {
		Board board = new Board();
		
		Cell an_empty_cell = board.getCellAt(1, 0);
		
		Assert.assertTrue(board.getFreeCells().contains(an_empty_cell));		
	}
	
	@Test
	public void givenInitialBoardACellSetToCIRCLEAt10ShouldNotBeInTheListOfEMPTYCells() throws Exception {
		Board board = new Board();
		
		board.setSymbolAt(1, 0, Cell.CIRCLE);
		Cell an_empty_cell = board.getCellAt(1, 0);
		
		Assert.assertFalse(board.getFreeCells().contains(an_empty_cell));		
	}

	@Test
	public void givenABoardACopyShouldEqual() throws Exception {
		Board board = new Board();
		Board bord_copy = board.copyBoard();
		
		assertEquals(board, bord_copy);
		
		
	}
	
	@Test
	public void givaenABoardARowCanBeGRetrived() throws Exception {
		Board board = getPrparedBoard();
		String [] row = board.getRow(0);
		
		String [] expexted = {Cell.CIRCLE, Cell.CROSS, Cell.CROSS};
		Assert.assertEquals(Arrays.asList(expexted),Arrays.asList(row));
	}
	
	@Test
	public void givaenABoardAColumnCanBeGRetrived() throws Exception {
		Board board = getPrparedBoard();
		String [] row = board.getColumn(0);
		
		String [] expexted = {Cell.CIRCLE, Cell.CIRCLE, Cell.EMPTY};
		Assert.assertEquals(Arrays.asList(expexted),Arrays.asList(row));
	}
	
	@Test
	public void givaenABoardADiagonalCanBeGRetrived() throws Exception {
		Board board = getPrparedBoard();
		String [] diagonal = board.getDiagonalUp();
		
		String [] expexted = {Cell.EMPTY, Cell.CIRCLE, Cell.CROSS};
		Assert.assertEquals(Arrays.asList(expexted),Arrays.asList(diagonal));
	}
	
	@Test
	public void givenARowItCanBroovedWeatherItIsThreeInARow() throws Exception {
		Board board = getPrparedBoard();
		
		try {
			String[] row = board.getDiagonalDown();
			board.checkThreeInLine(row);
		} catch (FoundException e) {
			Assert.assertEquals(Cell.CIRCLE, e.getSymbol());
		}		
	}
	
	@Test
	public void givenABoardItShouldCheckForWinCondition() throws Exception {
		Board board = getPrparedBoard();
		
		Assert.assertEquals(Cell.CIRCLE, board.checkForWinner());
	}
	
}
