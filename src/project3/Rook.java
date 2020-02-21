package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class Rook extends ChessPiece {
	/**
	 * Constructor for objects of class ChessPiece
	 * @param player that owns this Rook
	 */
	public Rook(Player player) {
        super(player);
    }
	/**
	 * Method to determine the type of the piece
	 * @return the type of piece
	 */
	public String type() {
        return "Rook";
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
    	if(move.toRow == move.fromRow) {
    		if(move.toColumn > move.fromColumn) {
    			for(int i=move.fromColumn+1;i<move.toColumn;i++) {
    				if(board[move.toRow][i] != null){
    					return false;
    				}
    			}
    		}
    		else if(move.toColumn < move.fromColumn){
    			for(int i=move.toColumn+1;i<move.fromColumn;i++) {
    				if(board[move.toRow][i] != null){
    					return false;
    				}
    			}
    		}
    	}
    	else if(move.toColumn == move.fromColumn){
    		if(move.toRow > move.fromRow) {
    			for(int i=move.fromRow+1;i<move.toRow;i++) {
    				if(board[i][move.toColumn] != null){
    					return false;
    				}
    			}
    		}
    		else if(move.toRow < move.fromRow) {
    			for(int i=move.toRow+1;i<move.fromRow;i++) {
    				if(board[i][move.toColumn] != null){
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
