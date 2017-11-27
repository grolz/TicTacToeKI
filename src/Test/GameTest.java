package Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import Game.Cell;
import Game.TicTacToeGame;

public class GameTest {

	@Test
	public void givienInitialGameFirstMoveIsCROSS() {
		TicTacToeGame game = new TicTacToeGame();

		Assert.assertEquals(Cell.CIRCLE, game.nextSymbol());
	}

	@Test
	public void givenInitialGameTheSecondMoveIsCIRCLE() throws Exception {
		TicTacToeGame game = new TicTacToeGame();
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
		TicTacToeGame game = new TicTacToeGame();
		String symbol = Cell.CIRCLE;
		int i = 1, j = 1;

		game.makeMove(i, j, symbol);

		Assert.assertEquals(symbol, game.getBoard().getXYPosition(i, j));
	}
}
