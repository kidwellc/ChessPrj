package project3;
/**
 * @author Jamie Chen
 * @version winter 2018
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestBishop {
	ChessPiece [][] board;
    Bishop b;
    Rook r;
    Move move;
    Pawn p;
    
	@Test
	public void isNotValidMoveBySameColor() {
		board = new ChessPiece[8][8];
        b = new Bishop(Player.BLACK);
        board[0][2] = b;
        move = new Move(0,2,3,5);
        p = new Pawn(Player.BLACK);
        board[3][5] = p;
        assertFalse("This should return false",b.isValidMove(move, board));
	}
	@Test
	public void isNotValidMove() {
		board = new ChessPiece[8][8];
        b = new Bishop(Player.BLACK);
        board[0][4] = b;
        move = new Move(0,4,2,3);
        assertFalse("This should return false",b.isValidMove(move, board));
        
        move = new Move(0,4,0,0);
        assertFalse("this should return false",b.isValidMove(move, board));
        
        move = new Move(0,4,0,4);
        assertFalse("This should return false",b.isValidMove(move, board));
        
        move = new Move(0,4,0,0);
        assertFalse("This should return false",b.isValidMove(move, board));
        
        move = new Move(0,4,7,7);
        assertFalse("This should return false",b.isValidMove(move, board));
	}
	@Test
	public void isValidMoveTakingPiece() {
		board = new ChessPiece[8][8];
		b = new Bishop(Player.BLACK);
        board[5][4] = b;
        move = new Move(5,4,1,0);
        r = new Rook(Player.WHITE);
        board[1][0] = r;
        assertTrue("This should return true",b.isValidMove(move, board));
        
        move = new Move(5,4,2,7);
        p = new Pawn(Player.WHITE);
        board[2][7] = p;
        assertTrue("this should return true",b.isValidMove(move, board));
	}
	@Test
	public void isValidMove() {
		board = new ChessPiece[8][8];
        b = new Bishop(Player.BLACK);
        board[3][6] = b;
        move = new Move(3,6,5,4);
        assertTrue("This should return true",b.isValidMove(move, board));
        
        move = new Move(3,6,4,7);
        assertTrue("This should return true",b.isValidMove(move, board));
        
        move = new Move(3,6,0,3);
        assertTrue("This should return true",b.isValidMove(move, board));
        
        move = new Move(3,6,2,7);
        assertTrue("This should return true",b.isValidMove(move, board));
	}
}
