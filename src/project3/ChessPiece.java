package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public abstract class ChessPiece implements IChessPiece{
   
    private Player owner;

    /**
     * Constructor for objects of class ChessPiece
     */
    public ChessPiece(Player player)
    {
        // Initialize instance variables
        this.owner = player;
    }
    /**
     * abstract of method to return piece type
     */
    public abstract String type();
    /**
	 * Gets piece owner 
	 * @return owner of piece
	 */
    public Player player() {
        return owner;
    }
    /**
	 * Inicial test for valid moves 
	 * @return true if valid, false if not 
	 */
    public boolean isValidMove(Move move,IChessPiece[][] board) {
    	//tests to see if it is moving on to itself 
       if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
           return false;
        }
        //
        if ( this != board[move.fromRow][move.fromColumn] ) {
            return false;
        }
        // makes sure your not movingon to your own piece 
        if ( board[move.toRow][move.toColumn] != null &&
             board[move.toRow][move.toColumn].player() == owner ) {
            return false;
        }
        
        return true;
        
    }
}