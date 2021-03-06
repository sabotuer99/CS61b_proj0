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
	public void ctor_shouldBeEmptyTrue_EmptyBoard() {
		//Arrange		
		//Act
		Board sut = new Board(true);
		
		//Assert
		for(int i = 0; i < 8; i += 1)
			for(int j = 0; j < 8; j += 1)
				assertNull(sut.pieceAt(i, j));
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
	public void pieceAt_OutOfBounds_returnsNull(){
		//Arrange		
		Board sut = new Board(false);
		
		//Act
		Piece piece = sut.pieceAt(-1, -1);
		
		//Assert
		assertNull(piece);
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
	public void canSelect_RedSquare_false(){
		//Arrange
		Board sut = new Board(false);
		
		//Act
		boolean result = sut.canSelect(1,0);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void canSelect_PieceSelectedNotMovedOtherPieceChecked_true(){
		//Arrange
		Board sut = new Board(true);
		sut.place(new Piece(true, null, 0, 0, "bomb"), 0, 0);
		sut.place(new Piece(true, null, 1, 1, "bomb"), 1, 1);
		sut.select(0, 0);
		
		//Act
		boolean result = sut.canSelect(1,1);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void canSelect_ValidCaptureMoveForFire_true(){
		//Arrange
		Board sut = new Board(true);
		sut.place(new Piece(true, null, 0, 0, "bomb"), 0, 0);
		sut.place(new Piece(false, null, 1, 1, "bomb"), 1, 1);
		sut.select(0, 0);
		
		//Act
		boolean result = sut.canSelect(2,2);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void canSelect_ValidPieceMoveForFire_true(){
		//Arrange
		Board sut = new Board(true);
		sut.place(new Piece(true, null, 0, 0, "bomb"), 0, 0);
		sut.select(0, 0);
		
		//Act
		boolean result = sut.canSelect(1,1);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void canSelect_FireMovedAlready_False(){
		//Arrange
		Board sut = new Board(true);
		sut.place(new Piece(true, sut, 0, 0, "bomb"), 0, 0);
		sut.place(new Piece(true, sut, 2, 0, "bomb"), 2, 0);
		sut.select(0, 0);
		sut.select(1, 1);
		
		//Act
		boolean result1 = sut.canSelect(1,1);
		boolean result2 = sut.canSelect(2,0);
		boolean result3 = sut.canSelect(0,0);
		
		//Assert
		assertFalse(result1);
		assertFalse(result2);
		assertFalse(result3);
	}
	
	@Test
	public void canSelect_InvalidPieceMoveForFire_false(){
		//Arrange
		Board sut = new Board(true);
		sut.place(new Piece(true, null, 0, 0, "bomb"), 0, 0);
		sut.select(0, 0);
		
		//Act
		boolean result1 = sut.canSelect(2,2);
		boolean result2 = sut.canSelect(1,7);
		
		//Assert
		assertFalse(result1);
		assertFalse(result2);
	}
	
	@Test
	public void place_DestinationOutOfBounds_doesNothing(){
		//Arrange
		Board sut = new Board(true);
		Piece piece = new Piece(false, null, 0, 0, "bomb");
		
		//Act
		sut.place(piece, -1, -1);
		
		//Assert
		for(int i = 0; i < 8; i += 1)
			for(int j = 0; j < 8; j += 1)
				assertNull(sut.pieceAt(i, j));
	}
	
	@Test
	public void place_DestinationRedSquare_doesNothing(){
		//Arrange
		Board sut = new Board(true);
		Piece piece = new Piece(false, null, 0, 0, "bomb");
		
		//Act
		sut.place(piece, 0, 1);
		
		//Assert
		for(int i = 0; i < 8; i += 1)
			for(int j = 0; j < 8; j += 1)
				assertNull(sut.pieceAt(i, j));
	}
	
	@Test
	public void place_DestinationValid_PlacesPiece(){
		//Arrange
		Board sut = new Board(true);
		Piece piece1 = new Piece(false, null, 0, 0, "bomb");
		Piece piece2 = new Piece(false, null, 0, 2, "bomb");
		
		//Act
		sut.place(piece1, 0, 0);
		sut.place(piece2, 0, 2);
		
		//Assert
		assertEquals(piece1, sut.pieceAt(0,0));
		assertEquals(piece2, sut.pieceAt(0,2));
	}
	
	@Test
	public void place_DestinationOccupied_OverwritesPiece(){
		//Arrange
		Board sut = new Board(true);
		Piece piece1 = new Piece(false, null, 0, 0, "bomb");
		Piece piece2 = new Piece(false, null, 0, 2, "bomb");
		
		//Act
		sut.place(piece1, 0, 0);
		sut.place(piece2, 0, 0);
		
		//Assert
		assertEquals(piece2, sut.pieceAt(0,0));
		assertNotEquals(piece1, sut.pieceAt(0, 0));
	}
	
	@Test
	public void remove_DestinationOutOfBounds_returnsNull(){
		//Arrange
		Board sut = new Board(true);
		Piece piece = new Piece(false, null, 0, 0, "bomb");
		sut.place(piece, 0, 0);
		
		//Act
		Piece result = sut.remove(-1, -1);
		
		//Assert
		assertNull(result);
				
	}
	
	@Test
	public void remove_DestinationIsPiece_returnsPiece(){
		//Arrange
		Board sut = new Board(true);
		Piece piece = new Piece(false, null, 0, 0, "bomb");
		sut.place(piece, 0, 0);
		
		//Act
		Piece result = sut.remove(0, 0);
		
		//Assert
		assertEquals(piece, result);		
	}
	
	@Test
	public void remove_DestinationIsPiece_PieceNotOnBoard(){
		//Arrange
		Board sut = new Board(true);
		Piece piece = new Piece(false, null, 0, 0, "bomb");
		sut.place(piece, 0, 0);
		
		//Act
		sut.remove(0, 0);
		
		//Assert
		assertNull(sut.pieceAt(0, 0));	
	}
	
	@Test
	public void remove_DestinationIsEmpty_returnsNull(){
		//Arrange
		Board sut = new Board(true);
		
		//Act
		Piece result = sut.remove(0, 0);
		
		//Assert
		assertNull(result);		
	}
	
	@Test
	public void select_SelectPieceSelectSpace_MovePiece(){
		//Arrange
		Board sut = new Board(true);
		Piece piece = new Piece(true, sut, 0, 0, "bomb");
		sut.place(piece, 0, 0);
		
		//Act
		sut.select(0, 0);
		sut.select(1, 1);
		
		//Assert
		assertNull(sut.pieceAt(0, 0));
		assertEquals(sut.pieceAt(1, 1), piece);
	}
	
	@Test
	public void canSelect_PieceHasCapturedSelectInvalidCaptureDestination_False(){
		//Arrange
		Board sut = new Board(true);
		Piece fire = new Piece(true, sut, 0, 0, "pawn");
		Piece water = new Piece(false, sut, 0, 0, "pawn");
		sut.place(fire, 0, 0);
		sut.place(water, 1, 1);
		sut.select(0, 0);
		sut.select(2, 2);
		
		//Act
		boolean result = sut.canSelect(3, 3);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void canSelect_PieceHasCapturedSelectValidCaptureDestination_True(){
		//Arrange
		Board sut = new Board(true);
		Piece fire = new Piece(true, sut, 0, 0, "pawn");
		Piece water1 = new Piece(false, sut, 0, 0, "pawn");
		Piece water2 = new Piece(false, sut, 0, 0, "pawn");
		sut.place(fire, 0, 0);
		sut.place(water1, 1, 1);
		sut.place(water2, 3, 3);
		sut.select(0, 0);
		sut.select(2, 2);
		
		//Act
		boolean result = sut.canSelect(4, 4);
		
		//Assert
		assertTrue(result);
	}

	@Test
	public void canSelect_CaptureAttemptDestinationOccupied_False(){
		//Arrange
		Board sut = new Board(true);
		Piece fire = new Piece(true, sut, 0, 0, "pawn");
		Piece water1 = new Piece(false, sut, 0, 0, "pawn");
		Piece water2 = new Piece(false, sut, 0, 0, "pawn");
		sut.place(fire, 0, 0);
		sut.place(water1, 1, 1);
		sut.place(water2, 2, 2);
		sut.select(0, 0);
		
		//Act
		boolean result = sut.canSelect(2, 2);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void canSelect_PieceSelectedTestSamePiece_False(){
		//Arrange
		Board sut = new Board(true);
		Piece fire = new Piece(true, sut, 0, 0, "bomb");
		sut.place(fire, 0, 0);
		sut.select(0, 0);
		
		//Act
		boolean result = sut.canSelect(0, 0);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void select_ChangePieceSelected_PiecesUnchanged(){
		//Arrange
		Board sut = new Board(true);
		Piece fire1 = new Piece(true, sut, 0, 0, "bomb");
		Piece fire2 = new Piece(true, sut, 2, 0, "bomb");
		sut.place(fire1, 0, 0);
		sut.place(fire2, 2, 0);
		
		//Act
		sut.select(0, 0);
		sut.select(2, 0);
		
		//Assert
		assertEquals(sut.pieceAt(0, 0), fire1);
		assertEquals(sut.pieceAt(2, 0), fire2);
	}
}
