package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class King extends ChessPiece{
	/**
	 * Constructor for objects of class ChessPiece
	 * @param player that owns this King
	 */
	public King(Player player) {
		super(player);
	}
	/**
	 * Method to determine the type of the piece
	 * @return the type of piece
	 */
	public String type() {
		return "King";
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
		boolean valid = false;
			// checks to see if the piece is being moved one space for every direction
			if (((move.toRow == move.fromRow - 1) && (move.toColumn == move.fromColumn - 1))
		    || ((move.toRow == move.fromRow - 1) && (move.toColumn == move.fromColumn))
		    || ((move.toRow == move.fromRow - 1) && (move.toColumn == move.fromColumn + 1))
		    || ((move.toRow == move.fromRow) && (move.toColumn == move.fromColumn - 1))
		    || ((move.toRow == move.fromRow) && (move.toColumn == move.fromColumn + 1))
		    || ((move.toRow == move.fromRow + 1) && (move.toColumn == move.fromColumn -1))
		    || ((move.toRow == move.fromRow + 1) && (move.toColumn == move.fromColumn))
		    || ((move.toRow == move.fromRow +1) && (move.toColumn == move.fromColumn + 1))){
				valid = true;
				IChessPiece tempKing=board[move.fromRow][move.fromColumn];
				IChessPiece tempPiece=board[move.toRow][move.toColumn];
				board[move.toRow][move.toColumn]=tempKing;
				board[move.fromRow][move.fromColumn]=null;
				for(int r=0;r<8; r++) {
					for(int c=0;c<8; c++) {
						if(board[r][c]!=null){
							if(board[r][c].player()==player().next()){
								if((r==move.toRow)&&(c==move.toColumn)){
									//do nothing
								}
								else {
									Move testMove = new Move(r, c, move.toRow, move.toColumn);
									if(ChessPanel.model.pieceAt(testMove.fromRow, testMove.fromColumn).isValidMove(testMove, board)) {
										valid = false;
									}
								}
							}
						}
					}
				}
				board[move.fromRow][move.fromColumn]=tempKing;
				board[move.toRow][move.toColumn]=tempPiece;
			}
			else {
				valid = false;
			}
		return valid;
	}
}
