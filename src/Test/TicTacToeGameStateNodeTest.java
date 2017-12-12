package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import Game.Board;
import Game.Cell;
import Game.GameNode;

public class TicTacToeGameStateNodeTest {

	@Test
	public void givienInitialGameFirstMoveIsCROSS() {
		GameNode game = new GameNode(new Board(),Cell.CROSS);

		Assert.assertEquals(Cell.CIRCLE, game.nextSymbol());
	}

	@Test
	public void givenInitialGameTheSecondMoveIsCIRCLE() throws Exception {
		GameNode game = new GameNode(new Board(),Cell.CIRCLE);
		Assert.assertEquals(Cell.CROSS, game.nextSymbol());
	}
	
	@Test
	public void givenABoardWithCIRCLEWinnungTheBoardshouldGiveBackCIRCLE() throws Exception {
		Board board = BoardTest.getPrparedBoard();
		GameNode gameNode = new GameNode(board);
		board.checkForWinner();
		Assert.assertEquals(Cell.CIRCLE, gameNode.getWinner());
	}
	

	@Test
	public void givenABoardWithCROSSWinnungTheBoardshouldGiveBackWinnerCROSS() throws Exception {
		Board board = BoardTest.getPrparedBoard();
		board.setSymbolAt(2, 2, Cell.CROSS);
		board.setSymbolAt(1, 2, Cell.CROSS);
		GameNode gameNode = new GameNode(board);
		board.checkForWinner();
		Assert.assertEquals(Cell.CROSS, gameNode.getBoard().getWinner());
	}

	@Test
	public void givenInitialGameAfterFirstMoveTheFilledCellIsNotPartOfTheEmptyCells()
			throws Exception {
		Board board = new Board();
		
		Cell cell = new Cell(1,1,Cell.CIRCLE);
		
		board.setSymbolAt(cell.get_i_position(), cell.get_j_position(), cell.getSymbol());
		
		ArrayList<Cell> empty_cells = board.getFreeCells();
		Assert.assertFalse(empty_cells.contains(cell));
	}

	@Test
	public void MakingAMoveRequiresCoordinatesAndSymbolAndThenTheCellIsFilledWithThisSymbol()
			throws Exception {
		String symbol = Cell.CIRCLE;
		GameNode game = new GameNode(new Board(),symbol);
		
		int i = 1, j = 1;
		Board next_Board = game.makeMove(i, j);

		Assert.assertEquals(symbol, next_Board.getSymbolAt(i, j));
	}
	
	@Test
	public void givenAGameAllEmptYcellsShouldBeReturned() throws Exception {
		Board board = BoardTest.getPrparedBoard();
		
		GameNode game = new GameNode(board);
		
		ArrayList<Cell> free_cells = board.getFreeCells();
		
		ArrayList<Cell> expected_cells = new ArrayList<Cell>();
		expected_cells.add(new Cell(2,0));
		expected_cells.add(new Cell(1,2));
		expected_cells.add(new Cell(2,1));
		Assert.assertTrue(expected_cells.containsAll(free_cells));
		
	}
	
	@Test
	public void givenTwoBoardTestIfTheyAreEqual() throws Exception {
		Board board_1 = new Board();
		Board board_2 = new Board();
		
		Assert.assertEquals(board_1, board_2);
		
		board_1.setSymbolAt(1, 0, Cell.CIRCLE);
		board_2.setSymbolAt(1, 0, Cell.CIRCLE);
		
		Assert.assertTrue(board_1.getContentAsList().equals(board_2.getContentAsList()));
		
		board_2.setSymbolAt(1, 1, Cell.CROSS);
		
		Assert.assertFalse(board_1.getContentAsList().equals(board_2.getContentAsList()));
	}
	
	@Test
	public void givenTwoCellsTheyAreEqualWhenPositionAndSymbolAreEquals() throws Exception {
		Cell cell_1 = new Cell(1,2,Cell.CIRCLE);
		Cell cell_2 = new Cell(1,2,Cell.CIRCLE);
		
		Assert.assertEquals(cell_1, cell_2);
	}
	
	@Test
	public void givenAGameAllNextMovesShouldBeCreated() throws Exception {
		GameNode game = new GameNode(BoardTest.getPrparedBoard(), Cell.CIRCLE);
		
		ArrayList<Cell> empty_cells = game.getBoard().getFreeCells();
		ArrayList<Board> possible_Boards = new ArrayList<Board>();
		for (Cell cell : empty_cells)
		{
			Board board = game.makeMove(cell.get_i_position(), cell.get_j_position());
			board.printBoardAsGraphic();
			possible_Boards.add(board);
			
		}
	}
	
	@Test
	public void givenABoardTheMoveMadeShouldBeReturned() throws Exception {
		GameNode game = new GameNode(BoardTest.getPrparedBoard(), Cell.CIRCLE);
		game.makeMove(2, 0);
		Assert.assertEquals(2,game.getMove()[0]);
		Assert.assertEquals(0,game.getMove()[1]);
	}
	
	@Test
	public void givenAGameNodeGetBackBestMoveForCross() throws Exception {
		GameNode game = new GameNode(BoardTest.getPrparedBoard(), Cell.CIRCLE);
		game.getBoard().setSymbolAt(2, 2, Cell.EMPTY);
		game.getBoard().setSymbolAt(1, 1, Cell.EMPTY);
		game.generateTreeForBoard();
		for (GameNode gameNode : game.getNextMoves()) {
			System.out.print(gameNode.getWinner() + " |");			
		}
	}
	
	@Test
	public void givenAnInitlaBoardPrintWinner() throws Exception {
		GameNode gameNode = new GameNode(new Board(),Cell.CIRCLE);
		GameNode nextNode = new GameNode(gameNode.makeMove(0, 0),gameNode.nextSymbol());
		nextNode.generateTreeForBoard();
		for (GameNode nextMove : nextNode.getNextMoves()) {
			System.out.print(nextMove.getWinner() + " |");			
		}
	}
}
