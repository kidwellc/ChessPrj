package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class Bishop extends ChessPiece {
	/**
	 * Constructor for objects of class ChessPiece
	 * @param player that owns this Bishop 
	 */
	public Bishop(Player player) {
        super(player);
    }
	/**
	 * Method to determine the type of the piece
	 * @return the type of piece
	 */
	public String type() {
        return "Bishop";
    }
	/**
	 * Determines the way that the piece can be move
	 * @param move describes the move to be made
	 * @param board where piece resides.
	 * @return true, if valid, false not 
	 */
	public boolean isValidMove(Move move,IChessPiece[][] board) {
        if (!super.isValidMove(move,board)) {
            return false;
        }
        boolean valid = true;
        // checks to see if this piece has a clear path
    	if(move.toRow -move.fromRow == move.toColumn - move.fromColumn) {
    		if(move.toColumn > move.fromColumn) {
    			for(int i=1;i<move.toColumn-move.fromColumn;i++) {
    				if(board[move.fromRow+i][move.fromColumn+i] != null){
    					return false;
    				}
    			}
    		}
    		else if(move.toColumn < move.fromColumn){
    			for(int i=1;i<move.fromColumn-move.toColumn;i++) {
    				if(board[move.toRow+i][move.toColumn+i] != null){
    					return false;
    				}
    			}
    		}
    	}
    	else if(move.toRow -move.fromRow == move.fromColumn - move.toColumn) {
    		if(move.toColumn > move.fromColumn) {
    			for(int i=1;i<move.toColumn-move.fromColumn;i++) {
    				if(board[move.fromRow-i][move.fromColumn+i] != null){
    					return false;
    				}
    			}
    		}
    		else if(move.toColumn < move.fromColumn){
    			for(int i=1;i<move.fromColumn-move.toColumn;i++) {
    				if(board[move.toRow-i][move.toColumn+i] != null){
    					return false;
    				}
    			}
    		}
    	}
    	else {
    		return false;
    	}

        return valid;
    }
}
