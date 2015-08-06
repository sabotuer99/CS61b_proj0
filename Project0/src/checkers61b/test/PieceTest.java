package checkers61b.test;

import static org.junit.Assert.*;

import org.junit.Test;

import checkers61b.Board;
import checkers61b.Piece;

public class PieceTest {

	@Test
	public void ctor_NullParams_Success() {
		new Piece(false, null, 0, 0, null);
	}
	
	@Test
	public void isFire_PieceIsFirePiece_ReturnsTrue(){
		//Arrange
		Piece sut = new Piece(true, null, 0, 0, null);
		
		//Act
		boolean result = sut.isFire();
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void isFire_PieceIsNotFirePiece_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, null);
		
		//Act
		boolean result = sut.isFire();
		
		//Assert
		assertFalse(result);
	}

	@Test
	public void side_PieceIsFirePiece_Returns0(){
		//Arrange
		Piece sut = new Piece(true, null, 0, 0, null);
		
		//Act
		int result = sut.side();
		
		//Assert
		assertEquals(0, result);
	}
	
	@Test
	public void side_PieceIsNotFirePiece_Returns1(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, null);
		
		//Act
		int result = sut.side();
		
		//Assert
		assertEquals(1, result);
	}
	
	@Test
	public void isKing_NewPiece_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, null);
		
		//Act
		boolean result = sut.isKing();
		
		//Assert
		assertFalse(result);
	}
	
	@Test 
	public void isShield_TypeIsShield_ReturnsTrue(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, "shield");
		
		//Act
		boolean result = sut.isShield();
		
		//Assert
		assertTrue(result);
	}
	
	@Test 
	public void isShield_TypeIsPawn_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, "pawn");
		
		//Act
		boolean result = sut.isShield();
		
		//Assert
		assertFalse(result);
	}
	
	@Test 
	public void isShield_TypeIsBomb_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, "bomb");
		
		//Act
		boolean result = sut.isShield();
		
		//Assert
		assertFalse(result);
	}
	
	@Test 
	public void isShield_TypeIsNull_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, null);
		
		//Act
		boolean result = sut.isShield();
		
		//Assert
		assertFalse(result);
	}
	
	@Test 
	public void isBomb_TypeIsShield_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, "shield");
		
		//Act
		boolean result = sut.isBomb();
		
		//Assert
		assertFalse(result);
	}
	
	@Test 
	public void isBomb_TypeIsPawn_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, "pawn");
		
		//Act
		boolean result = sut.isBomb();
		
		//Assert
		assertFalse(result);
	}
	
	@Test 
	public void isBomb_TypeIsBomb_ReturnsTrue(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, "bomb");
		
		//Act
		boolean result = sut.isBomb();
		
		//Assert
		assertTrue(result);
	}
	
	@Test 
	public void isBomb_TypeIsNull_ReturnsFalse(){
		//Arrange
		Piece sut = new Piece(false, null, 0, 0, null);
		
		//Act
		boolean result = sut.isBomb();
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void move_MoveToEmptySpace_success(){
		//Arrange
		Board board = new Board(true);
		Piece sut = new Piece(false, board, 0, 0, "pawn");
		board.place(sut, 0, 0);
		
		//Act
		sut.move(1,1);
		
		//Assert
		assertEquals(board.pieceAt(1, 1), sut);
		assertNull(board.pieceAt(0, 0));
	}
	
	@Test
	public void move_CaptureMove_success(){
		//Arrange
		Board board = new Board(true);
		Piece sut = new Piece(false, board, 0, 0, "pawn");
		Piece opp = new Piece(true, board, 1, 1, "pawn");
		board.place(sut, 0, 0);
		board.place(opp, 1, 1);
		
		//Act
		sut.move(2,2);
		
		//Assert
		assertEquals(board.pieceAt(2, 2), sut);
		assertNull(board.pieceAt(0, 0));
		assertNull(board.pieceAt(1, 1));
		assertTrue(sut.hasCaptured());
	}
}
