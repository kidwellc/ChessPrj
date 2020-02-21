package project3;
/**
 * @author Jamie Chen
 * @version winter 2018
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class TestRook {
	ChessPiece [][] board;
    Rook r;
    Knight kt;
    Move move;
    Pawn p;
    
	@Test
	public void isNotValidMoveBySameSide() {
		board = new ChessPiece[8][8];
		r = new Rook(Player.BLACK);
		board[0][0] = r;
		move = new Move(0,0,0,1);
		p = new Pawn(Player.BLACK);
		board[0][1] = p;
		assertFalse("This should return false",r.isValidMove(move, board));
	}
	@Test
	public void isNotValidMove() {
		board = new ChessPiece[8][8];
		r = new Rook(Player.WHITE);
		board[0][3] = r;
		move = new Move(0,3,4,6);
		assertFalse("This should return false",r.isValidMove(move, board));
		
		move = new Move(0,3,5,0);
		assertFalse("This should return false",r.isValidMove(move, board));
		
		move = new Move(0,3,7,7);
		assertFalse("This should return false",r.isValidMove(move, board));
		
		move = new Move(0,3,0,3);
		assertFalse("This should return false",r.isValidMove(move, board));
	}
	@Test
	public void isValidMoveTakingPiece() {
		board = new ChessPiece[8][8];
		r = new Rook(Player.BLACK);
		board[2][5] = r;
		move = new Move(2,5,5,5);
		kt = new Knight(Player.WHITE);
		board[5][5] = kt;
		assertTrue("This should return true",r.isValidMove(move, board));
	}
	@Test
	public void isValidMove() {
		board = new ChessPiece[8][8];
		r = new Rook(Player.WHITE);
		board[3][3] = r;
		move = new Move(3,3,6,3);
		assertTrue("This should return true",r.isValidMove(move, board));
		
		move = new Move(3,3,0,3);
		assertTrue("This should return true",r.isValidMove(move, board));
		
		move = new Move(3,3,3,5);
		assertTrue("This should return true",r.isValidMove(move, board));
		
		move = new Move(3,3,3,0);
		assertTrue("This should retunr true",r.isValidMove(move, board));
	}
}
