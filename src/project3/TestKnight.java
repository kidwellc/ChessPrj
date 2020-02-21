package project3;
/**
 * @author Jamie Chen
 * @version winter 2018
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestKnight {
	ChessPiece [][] board;
    Knight kt;
    Queen q;
    Move move;
    Pawn p;

	@Test
	public void isNotValidMove() {
		board = new ChessPiece[8][8];
        kt = new Knight(Player.BLACK);
        board[0][4] = kt;
        move = new Move(0,4,2,3);
        p = new Pawn(Player.BLACK);
        board[2][3] = p;
		assertFalse("This should return false",kt.isValidMove(move, board));
	}
	@Test
	public void isValidMoveTakingpiece() {
		board = new ChessPiece[8][8];
        kt = new Knight(Player.BLACK);
        board[2][3] = kt;
        move = new Move(2,3,0,2);
        q = new Queen(Player.WHITE);
        board[0][2] = q;
        assertTrue("This should return true",kt.isValidMove(move, board));
	}
	@Test
	public void testKnightIsNotValidMove() {
		board = new ChessPiece[8][8];
		kt = new Knight(Player.WHITE);
		board[5][6] = kt;
		move = new Move(5,6,4,6);
		assertFalse("This should return false",kt.isValidMove(move, board));
		
		move = new Move(5,6,5,6);
		assertFalse("This should return false",kt.isValidMove(move, board));
		
		move = new Move(5,6,0,0);
		assertFalse("This should return false",kt.isValidMove(move, board));
	}
	@Test
	public void isValidMove() {
		board = new ChessPiece[8][8];
		kt = new Knight(Player.BLACK);
		board[2][2] = kt;
		move = new Move(2,2,4,3);
		assertTrue("This should return true",kt.isValidMove(move, board));
		
		move = new Move(2,2,4,1);
		assertTrue("This should return true",kt.isValidMove(move, board));
		
		move = new Move(2,2,0,1);
		assertTrue("This should return true",kt.isValidMove(move, board));
		
		move = new Move(2,2,3,0);
		assertTrue("This should return true",kt.isValidMove(move, board));
	}
}
