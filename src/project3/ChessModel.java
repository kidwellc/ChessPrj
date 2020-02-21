package project3;
import java.util.Random;

/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class ChessModel implements IChessModel {
    Pawn p;
    Rook r;
    Bishop b;
    Queen q;
    King k;
    Knight kt;
    
    public int kingRow=-1;
    public int kingCol=-1;
    public Move escapeMove;
    
	static IChessPiece[][] board;
	private Player player;
	/**
	 * Constructor for class ChessModel
	 */
	@SuppressWarnings("static-access")
	public ChessModel() {
		player = player.WHITE;
        board = new ChessPiece[8][8];
        for (int i=0;i<8;i++) {
        	for (int j=0;j<8;j++) {
        		board[j][i]=null;
        	}
            p = new Pawn(Player.BLACK);
            board[1][i] = p;
            p = new Pawn(Player.WHITE);
            board[6][i] = p;
        }
        r = new Rook(Player.BLACK);
        board[0][0] = r;
        r = new Rook(Player.BLACK);
        board[0][7] = r;
        r = new Rook(Player.WHITE);
        board[7][0] = r;
        r = new Rook(Player.WHITE);
        board[7][7] = r;
        b = new Bishop(Player.BLACK);
        board[0][2] = b;
        b = new Bishop(Player.BLACK);
        board[0][5] = b;
        b = new Bishop(Player.WHITE);
        board[7][2] = b;
        b = new Bishop(Player.WHITE);
        board[7][5] = b;
        kt = new Knight(Player.BLACK);
        board[0][1] = kt;
        kt = new Knight(Player.BLACK);
        board[0][6] = kt;
        kt = new Knight(Player.WHITE);
        board[7][1] = kt;
        kt = new Knight(Player.WHITE);
        board[7][6] = kt;
        q = new Queen(Player.BLACK);
        board[0][3] = q;
        q = new Queen(Player.WHITE);
        board[7][3] = q;
        k = new King(Player.BLACK);
        board[0][4] = k;
        k = new King(Player.WHITE);
        board[7][4] = k;
	}
	/**
	 * Tests for Checkmate
	 * @return true if Checkmat, false not
	 */
	public boolean isComplete(Player p) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				escapeMove = new Move(kingRow,kingCol,r,c);
				if(pieceAt(escapeMove.fromRow, escapeMove.fromColumn).isValidMove(escapeMove, board)) {
					return false; 
				}
			}
		}
		
		for (int fromRow = 0; fromRow < 8; fromRow++) {
			for (int fromCol = 0; fromCol < 8; fromCol++) {
				if(board[fromRow][fromCol]!=null) {
					if(board[fromRow][fromCol].player()==p) {
						for (int toRow = 0; toRow < 8; toRow++) {
							for (int toCol = 0; toCol < 8; toCol++) {
								Move escapeMove = new Move(fromRow,fromCol,toRow,toCol);
								if(pieceAt(escapeMove.fromRow, escapeMove.fromColumn).isValidMove(escapeMove, board)) {
									IChessPiece tempFrom=board[escapeMove.fromRow][escapeMove.fromColumn];
									IChessPiece tempTo=board[escapeMove.toRow][escapeMove.toColumn];
									board[escapeMove.toRow][escapeMove.toColumn]=tempFrom;
									board[escapeMove.fromRow][escapeMove.fromColumn]=null;
									if(inCheck(p)) {
										//we're still in check, 
										//so undo move and continue looking
										board[escapeMove.fromRow][escapeMove.fromColumn]=tempFrom;
										board[escapeMove.toRow][escapeMove.toColumn]=tempTo;
									}
									else {
										//we're no longer in check, so there's a way out of check mate
										//so undo move and exit
										board[escapeMove.fromRow][escapeMove.fromColumn]=tempFrom;
										board[escapeMove.toRow][escapeMove.toColumn]=tempTo;
										return false; 
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * Moves the piece from location to location
	 * @param move describes the move to be made.
	 */
	public void move(Move move) {
		board[move.toRow][move.toColumn]=board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn]=null;
	}
	/**
	 * Report whether the current player is in check
	 * @param  p the Player being attacked 
	 * @return true if the current player is in check, false if not
	 */
	public boolean inCheck(Player p) {
		for(int r=0;r<8; r++) {
			for(int c=0;c<8; c++) {
				if(board[r][c]!=null) {
					if(board[r][c].type()=="King") {
						if(board[r][c].player()==p) {
							kingRow=r;
							kingCol=c;
						}
					}
				}
			}
		}
		for(int r=0;r<8; r++) {
			for(int c=0;c<8; c++) {
				if(board[r][c]!=null) {
					if(board[r][c].player()==p.next()) {
						Move move = new Move(r, c, kingRow, kingCol);
						if(board[r][c].isValidMove(move, board)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * Gets current player
	 * @return color of current player
	 */
	public Player currentPlayer() {
		return player;
	}
	/**
	 * Gets the specified piece 
	 * @param row of that piece
	 * @param column of that piece
	 * @return the piece at that location 
	 */
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}
	/**
	 * Computer player for chess
	 * @param p
	 */
	public void computerMove(Player p) {
		if(inCheck(p)) {
			if(!isComplete(p)) {
				move(escapeMove);
				return;
			}
		}

		for (int fromRow = 0; fromRow < 8; fromRow++) {
			for (int fromCol = 0; fromCol < 8; fromCol++) {
				if(board[fromRow][fromCol]!=null) {
					if(board[fromRow][fromCol].player()==p) {
						for (int toRow = 0; toRow < 8; toRow++) {
							for (int toCol = 0; toCol < 8; toCol++) {
								Move attackMove = new Move(fromRow,fromCol,toRow,toCol);
								if(pieceAt(attackMove.fromRow, attackMove.fromColumn).isValidMove(attackMove, board)) {
									IChessPiece tempFrom=board[attackMove.fromRow][attackMove.fromColumn];
									IChessPiece tempTo=board[attackMove.toRow][attackMove.toColumn];
									board[attackMove.toRow][attackMove.toColumn]=tempFrom;
									board[attackMove.fromRow][attackMove.fromColumn]=null;
									if(inCheck(p.next())) {
										return;
									}
									else {
										board[attackMove.fromRow][attackMove.fromColumn]=tempFrom;
										board[attackMove.toRow][attackMove.toColumn]=tempTo;
									}
								}
							}
						}
					}
				}
			}
		}

		Random rand = new Random();
		int fromRow = rand.nextInt(7);
		int fromCol = rand.nextInt(7);
		int toRow = rand.nextInt(7);
		int toCol = rand.nextInt(7);
		Move move;
		do {
			do {
				do {
					fromRow = rand.nextInt(7);
					fromCol = rand.nextInt(7);
				} while (board[fromRow][fromCol]==null);
			} while(board[fromRow][fromCol].player()!=p);
			toRow = rand.nextInt(7);
			toCol = rand.nextInt(7);
			move = new Move(fromRow,fromCol,toRow,toCol);
		} while(!pieceAt(fromRow, fromCol).isValidMove(move, board));
		move(move);
		return;
	}
}
