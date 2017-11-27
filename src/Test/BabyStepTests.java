package Test;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import Game.Board;
import Game.Cell;

public class BabyStepTests {

	@Test
	public void givenPuttingCircleInPosition00ThenGetPositionRerurnsCircle() {

		Board board = new Board();

		board.setXYPosition(0, 0, Cell.CIRCLE);

		Assert.assertEquals(Cell.CIRCLE, board.getXYPosition(0, 0));

	}

	@Test
	public void givenPuttingCirossInPosition00ThenGetPositionRerurnsCross() {

		Board board = new Board();

		board.setXYPosition(0, 0, Cell.CROSS);

		Assert.assertEquals(Cell.CROSS, board.getXYPosition(0, 0));

	}

	@Test
	public void givenACircleAt00AndaCrossAt01getXPosition00ShouldReturnACircle() {
		Board board = new Board();

		board.setXYPosition(0, 0, Cell.CIRCLE);
		board.setXYPosition(0, 1, Cell.CROSS);
		Assert.assertEquals(Cell.CIRCLE, board.getXYPosition(0, 0));
	}

	@Test
	public void givenIntialBoardThenGetXPositionAsEmpty() {

		Board board = new Board();

		Assert.assertEquals(Cell.EMPTY, board.getXYPosition(0, 0));
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

}
