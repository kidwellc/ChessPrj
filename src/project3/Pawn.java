package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class Pawn extends ChessPiece{
	/**
	 * Constructor for objects of class ChessPiece
	 * @param player that owns this Pawn
	 */
    public Pawn(Player player) {
        super(player);
    }
    /**
	 * Method to determine the type of the piece
	 * @return the type of piece
	 */
    public String type() {
        return "Pawn";
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
        boolean valid = false;
        if (board[move.fromRow][move.fromColumn].player() == Player.BLACK) {
            // checks to see if the piece is being moved diagonally down and over one space
            if (((move.toRow - 1 == move.fromRow) && (move.toColumn + 1 == move.fromColumn))
            || ((move.toRow - 1 == move.fromRow) && (move.toColumn - 1 == move.fromColumn))) {
            	// checks to see if this piece is taking another player's piece
                if (board[move.toRow][move.toColumn] != null) {
                    valid = true;
                }
            }
            // checks to see if this piece is a black pawn in row 2
            if (move.fromRow == 1) {
            	// checks to see if this piece is being moved 2 spaces forward
                if ((move.toColumn == move.fromColumn) && (move.toRow == 3)) {
                	// checks to see if this piece has a clear path
                    if ((board[2][move.fromColumn]) == null && (board[3][move.fromColumn]) == null) {
                        valid = true;
                    }
                }
            }
            // checks to see if this piece is being moved down one row
            if ((move.toColumn == move.fromColumn) && (move.toRow - 1 == move.fromRow)) {
            	// checks to see if this piece has a clear path
                if ((board[move.toRow][move.toColumn]) == null) {
                    valid = true;
                }
            }
        }

        // checks to see if it is white's turn
        else if (board[move.fromRow][move.fromColumn].player() == Player.WHITE) {
            // checks to see if the piece is being moved diagonally up and over one space
            if (((move.toRow + 1 == move.fromRow) && (move.toColumn + 1 == move.fromColumn))
            || ((move.toRow + 1 == move.fromRow) && (move.toColumn - 1 == move.fromColumn))) {
            	// checks to see if this piece is taking another player's piece
                if (board[move.toRow][move.toColumn] != null) {
                    valid = true;
                }
            }

            // checks to see if this piece is a white pawn in row 7
            if (move.fromRow == 6) {
            	// checks to see if this piece is being moved 2 spaces forward
                if ((move.toColumn == move.fromColumn) && (move.toRow == 4)) {
                	// checks to see if this piece has a clear path
                    if ((board[5][move.fromColumn]) == null && (board[4][move.fromColumn]) == null) {
                        valid = true;
                    }
                }
            }
            // checks to see if this piece is being moved up one row
            if ((move.toColumn == move.fromColumn) && (move.toRow + 1 == move.fromRow)) {
            // checks to see if this piece has a clear path
                if ((board[move.toRow][move.toColumn]) == null) {
                    valid = true;
                }
            }
        }
        return valid;
    }
}