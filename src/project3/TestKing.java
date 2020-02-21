package project3;
/**
 * @author Jamie Chen
 * @version winter 2018
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestKing {
	ChessPiece [][] board;
    King k;
    Queen q;
    Rook r;
    Move move;
    Pawn p;

	@Test
	public void isNotValidMove() {
		board = new ChessPiece[8][8];
        k = new King(Player.BLACK);
        board[0][4] = k;
        move = new Move(0,4,1,4);
        p = new Pawn(Player.BLACK);
        board[1][4] = p;
		assertFalse("This should return false",k.isValidMove(move, board));
	}
	@Test
	public void testKingIsValidMoveTakePiece() {
		board = new ChessPiece[8][8];
		k = new King(Player.BLACK);
		board[0][4] = k;
		move = new Move(0,4,0,3);
		q = new Queen(Player.WHITE);
		board[0][3] = q;
		assertTrue("This should return true",k.isValidMove(move, board));
	}
	@Test
	public void testKingIsNotValidMove() {
		board = new ChessPiece[8][8];
		k = new King(Player.WHITE);
		board[5][3] = k;
		move = new Move(5,3,3,3);
		assertFalse("This should return false",k.isValidMove(move, board));
		
		move = new Move(5,3,5,3);
		assertFalse("This should return false",k.isValidMove(move, board));
	}
	@Test
	public void testKingIsValidMove() {
		board = new ChessPiece[8][8];
		k = new King(Player.BLACK);
		board[3][3] = k;
		move = new Move(3,3,4,2);
		assertTrue("This should return true",k.isValidMove(move, board));
		
		move = new Move(3,3,2,2);
		assertTrue("This should return true",k.isValidMove(move, board));
		
		move = new Move(3,3,3,4);
		assertTrue("This should return true",k.isValidMove(move, board));
		
		move = new Move(3,3,2,3);
		assertTrue("This should return true",k.isValidMove(move, board));
		
		move = new Move(3,3,4,4);
		assertTrue("This should return true",k.isValidMove(move, board));
	}
}
