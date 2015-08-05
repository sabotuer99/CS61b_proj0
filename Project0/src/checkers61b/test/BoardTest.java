package checkers61b.test;

import static org.junit.Assert.*;

import org.junit.Test;

import checkers61b.Board;
import checkers61b.Piece;

public class BoardTest {

	@Test
	public void ctor_shouldBeEmptyFalse_DefaultBoard() {
		//Arrange		
		//Act
		new Board(false);
		
		//Assert
	}

	@Test
	public void pieceAt_DefaultBoard_PiecesCorrectType() {
		//Arrange		
		Board sut = new Board(false);
		
		//Act
		//get two pawns
		Piece p00 = sut.pieceAt(0, 0);
		Piece p77 = sut.pieceAt(7, 7);
		
		//get two shields
		Piece p11 = sut.pieceAt(1, 1);
		Piece p66 = sut.pieceAt(6, 6);
		
		//get two bombs
		Piece p22 = sut.pieceAt(2, 2);
		Piece p55 = sut.pieceAt(5, 5);
		
		//get two empties
		Piece p33 = sut.pieceAt(3, 3);
		Piece p44 = sut.pieceAt(4, 4);
		
		//Assert
		assertTrue(!p00.isBomb() && !p00.isShield() && p00.isFire());
		assertTrue(!p77.isBomb() && !p77.isShield() && !p77.isFire());
		assertTrue(!p11.isBomb() && p11.isShield() && p11.isFire());
		assertTrue(!p66.isBomb() && p66.isShield() && !p66.isFire());
		assertTrue(p22.isBomb() && !p22.isShield() && p22.isFire());
		assertTrue(p55.isBomb() && !p55.isShield() && !p55.isFire());
		assertNull(p33);
		assertNull(p44);
	}
	
	@Test
	public void canSelect_FirstMoveFirePlayerFirePiece_True() {
		//Arrange
		Board sut = new Board(false);
		
		//Act
		boolean result = sut.canSelect(0,0);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void canSelect_FirstMoveFirePlayerWaterPiece_False() {
		//Arrange
		Board sut = new Board(false);
		
		//Act
		boolean result = sut.canSelect(7,7);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void canSelect_SelectedPiece_false(){
		//Arrange
		Board sut = new Board(false);
		sut.select(0, 0);
		
		//Act
		boolean result = sut.canSelect(0,0);
		
		//Assert
		assertFalse(result);
	}
}
