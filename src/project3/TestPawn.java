package project3;
/**
 * @author Christina Kidwell and Jamie Chen
 * @version winter 2018
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestPawn.
 *
 * @author Christina Kidwell
 * @version winter 2018
 */
public class TestPawn{
    ChessPiece [][] board;
    Pawn p;
    Move move;
    /**
     * Default constructor for test class TestPawn
     */
    public TestPawn(){
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        board = new ChessPiece[8][8];
        p = new Pawn(Player.BLACK);
        board[1][0] = p;
        move = new Move(1,0,2,0);
    }
    
    
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
    }

    @Test
    public void silly(){
        assertTrue("This should return true",p.isValidMove(move,board));
    }
}