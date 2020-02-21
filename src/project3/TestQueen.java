package project3;
/**
 * @author Jamie Chen
 * @version winter 2018
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class TestQueen {
	ChessPiece [][] board;
    Queen q;
    King k;
    Move move;
    Pawn p;
	
    @Test
	public void isNotValidMoveBySameColor() {
    	board = new ChessPiece[8][8];
        q = new Queen(Player.BLACK);
        board[0][2] = q;
        move = new Move(0,2,3,5);
        p = new Pawn(Player.BLACK);
        board[3][5] = p;
        assertFalse("This should return false",q.isValidMove(move, board));
        
        move = new Move(0,2,0,5);
        k = new King(Player.BLACK);
        board[0][5] = k;
        assertFalse("This should return false",q.isValidMove(move, board));
    }
    @Test
    public void isNotValidMove() {
    	board = new ChessPiece[8][8];
        q = new Queen(Player.BLACK);
        board[4][3] = q;
        move = new Move(4,3,3,1);
        assertFalse("This should return false",q.isValidMove(move, board));
        
        move = new Move(4,3,7,7);
        assertFalse("This should return false",q.isValidMove(move, board));
        
        move = new Move(4,3,4,3);
        assertFalse("This should return false",q.isValidMove(move, board));
    }
    @Test
    public void isValidMoveTakingPiece() {
    	board = new ChessPiece[8][8];
        q = new Queen(Player.BLACK);
        board[4][3] = q;
        move = new Move(4,3,0,7);
        k = new King(Player.WHITE);
        board[0][7] = k;
        assertTrue("This should return true",q.isValidMove(move, board));
        
        move = new Move(4,3,4,1);
        p = new Pawn(Player.WHITE);
        board[4][1] = p;
        assertTrue("This should return true",q.isValidMove(move, board));
    }
    @Test
    public void isValidMove() {
    	board = new ChessPiece[8][8];
        q = new Queen(Player.BLACK);
        board[4][3] = q;
        move = new Move(4,3,4,0);
        assertTrue("This should return true",q.isValidMove(move, board));
        
        move = new Move(4,3,6,3);
        assertTrue("This should return true",q.isValidMove(move, board));
        
        move = new Move(4,3,2,5);
        assertTrue("This should return true",q.isValidMove(move, board));
        
        move = new Move(4,3,7,6);
        assertTrue("This should return true",q.isValidMove(move, board));
        
        move = new Move(4,3,7,0);
        assertTrue("This should return true",q.isValidMove(move, board));
    }
}
