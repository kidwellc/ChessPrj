package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class Knight extends ChessPiece{
	/**
	 * Constructor for objects of class ChessPiece
	 * @param player that owns this Knight 
	 */
	public Knight(Player player) {
		super(player);
	}
	/**
	 * Method to determine the type of the piece
	 * @return the type of piece
	 */
	public String type() {
		return "Knight";
	}
	/**
	 * Determines the way that the piece can be move
	 * @param move describes the move to be made
	 * @param board where piece resides.
	 * @return true, if valid, false not 
	 */
	public boolean isValidMove(Move move,IChessPiece[][] board) {
		if(!super.isValidMove(move, board)) {
			return false;
		}
		boolean valid = true;

		if((move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn - 2)
				|| (move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn + 2)
				|| (move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn - 1)
				|| (move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn + 1)
				|| (move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn - 2)
				|| (move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn + 2)
				|| (move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn - 1)
				|| (move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn + 1)){
			valid = true;
		}
		else {
			valid = false;
		}
		return valid;
	}

}
