package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChessPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ImageIcon iconWPawn;
	private ImageIcon iconBPawn;
	private ImageIcon iconWRook;
	private ImageIcon iconBRook;
	private ImageIcon iconWBishop;
	private ImageIcon iconBBishop;
	private ImageIcon iconWKnight;
	private ImageIcon iconBKnight;
	private ImageIcon iconWQueen;
	private ImageIcon iconBQueen;
	private ImageIcon iconWKing;
	private ImageIcon iconBKing;

	private boolean computerPlayer=false;
	private IChessPiece fromSquare;
	private IChessPiece toSquare;
	private Move move;
	private Player currentPlayer;
	private int startRow;
	private int startCol;
	private boolean buttonFlag;
	private final int BSIZE =8;
	private static JButton[][] board;
	public static ChessModel model;
	// declare other instance variables as needed
	//private ButtonListener buttonListener = new ButtonListener();
	/**
	 * Constructor for ChessPanel
	 */
	public ChessPanel() {
		buttonFlag = false;
		currentPlayer = Player.WHITE;
		model = new ChessModel(); 
		setLayout(new GridLayout(BSIZE,BSIZE));
		ButtonListener listener = new ButtonListener();

		iconWPawn = new ImageIcon ("WPawn.png");
		iconWPawn = new ImageIcon ("WPawn.png");
		iconBPawn = new ImageIcon ("BPawn.png");
		iconWRook = new ImageIcon ("WRook.png");
		iconBRook = new ImageIcon ("BRook.png");
		iconWBishop = new ImageIcon ("WBishop.png");
		iconBBishop = new ImageIcon ("BBishop.png");
		iconWKnight = new ImageIcon ("WKnight.png");
		iconBKnight = new ImageIcon ("BKnight.png");
		iconWQueen = new ImageIcon ("WQueen.png");
		iconBQueen = new ImageIcon ("BQueen.png");
		iconWKing = new ImageIcon ("WKing.png");
		iconBKing = new ImageIcon ("BKing.png");

		board = new JButton[BSIZE][BSIZE];
		for (int row = 0; row < BSIZE; row++) {
			for (int col = 0; col < BSIZE; col++) {
				board[row][col] = new JButton ("");
				board[row][col].addActionListener(listener);
				board[row][col].setSize(100,100);
				if ((row+col)%2==0) {
					board[row][col].setBackground(Color.WHITE);
				}
				else {
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
				add(board[row][col]);
			}
		}
		displayBoard();
		Object[] options = {"Computer","Human"};
    	int n = JOptionPane.showOptionDialog(null,
        	    "Select your opponent:",
        	    "Chess",
        	    JOptionPane.YES_NO_OPTION,
        	    JOptionPane.QUESTION_MESSAGE,
        	    null,
        	    options,
        	    options[0]);
    	if(n==0) {
    		computerPlayer=true;
    	}
	}

	
	/**
	 * Method that updates the board's diplay
	 */
	private void displayBoard() {
		for (int row = 0; row < BSIZE; row++) {
			for (int col = 0; col < BSIZE; col++) {
				board[row][col].setIcon(null);
				if(ChessModel.board[row][col]!=null) {
					switch(ChessModel.board[row][col].type()) {
					case "Pawn":
						board[row][col].setIcon((ChessModel.board[row][col].player() == Player.WHITE)? iconWPawn : iconBPawn);
						break;
					case "Rook":
						board[row][col].setIcon((ChessModel.board[row][col].player() == Player.WHITE)? iconWRook : iconBRook);
						break;
					case "Bishop":
						board[row][col].setIcon((ChessModel.board[row][col].player() == Player.WHITE)? iconWBishop : iconBBishop);
						break;
					case "Queen":
						board[row][col].setIcon((ChessModel.board[row][col].player() == Player.WHITE)? iconWQueen : iconBQueen);
						break;
					case "King":
						board[row][col].setIcon((ChessModel.board[row][col].player() == Player.WHITE)? iconWKing : iconBKing);
						break;
					case "Knight":
						board[row][col].setIcon((ChessModel.board[row][col].player() == Player.WHITE)? iconWKnight : iconBKnight);
						break;

					}
				}
			}
		}
	}
	/**
	 * Helper Method to set the color of the board 
	 */
	private void SetBackgroundColor () {
		for (int row = 0; row < BSIZE; row++) {
			for (int col = 0; col < BSIZE; col++) {
				if ((row+col)%2==0) {
					board[row][col].setBackground(Color.WHITE);
				}
				else {
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
		displayBoard();
	}
	/**
	 * Helper Method to promote pawn to queen when it reaches 
	 * other side of board  
	 */
	@SuppressWarnings("static-access")
	public void  CheckForPromotion() {
		for(int col=0; col< BSIZE; col++) {
			if(model.pieceAt(0, col)!= null) {
				if(model.pieceAt(0, col).player() == Player.WHITE) {
					if(model.pieceAt(0, col).type() == "Pawn") {
						model.q = new Queen(Player.WHITE);
				        model.board[0][col] = model.q;
					}
				}
			}
			if(model.pieceAt(7, col)!= null) {
				if(model.pieceAt(7, col).player() == Player.BLACK) {
					if(model.pieceAt(7, col).type() == "Pawn") {
						model.q = new Queen(Player.BLACK);
				        model.board[7][col] = model.q;
					}
				}
			}
		}
	}
	/**
	 * Represents a listener for button push (action) events
	 */
	private class ButtonListener implements ActionListener {
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent event) {
			JComponent comp = (JComponent) event.getSource();
			for (int row = 0; row < BSIZE; row++) {
				for (int col = 0; col < BSIZE; col++) {
					if (board[row][col] == comp) {
						if(!buttonFlag) {
							if(model.pieceAt(row, col)==null) {
								return;
							}
							if(model.pieceAt(row, col).player() != currentPlayer) {
								return;
							}
							board[row][col].setBackground(Color.cyan);
							startRow=row;
							startCol=col;
							for (int r = 0; r < BSIZE; r++) {
								for (int c = 0; c < BSIZE; c++) {
									move = new Move(row,col,r,c);
									if(model.pieceAt(move.fromRow, move.fromColumn)!= null) {
										if(model.pieceAt(move.fromRow, move.fromColumn).isValidMove(move, model.board)) {
											board[r][c].setBackground(Color.yellow);	
										}
									}
								}
							}
							buttonFlag = true;
						}
						else {
							if((startRow==row)&&(startCol==col)){
						        SetBackgroundColor();
						        buttonFlag = false;
								if(model.inCheck(currentPlayer)) {
									board[model.kingRow][model.kingCol].setBackground(Color.red);	
								}
						        return;
							}	
							fromSquare=ChessModel.board[startRow][startCol];
							toSquare=ChessModel.board[row][col];

							move = new Move(startRow, startCol, row,col);
							if(model.pieceAt(move.fromRow, move.fromColumn)!= null) {
								if(model.pieceAt(move.fromRow, move.fromColumn).isValidMove(move, model.board)) {
									model.move(move);
								}
								else {
									return;
								}
							}
							else {
								return;
							}
							SetBackgroundColor();
							if(model.inCheck(currentPlayer)) {
								ChessModel.board[startRow][startCol]=fromSquare;
								ChessModel.board[row][col]=toSquare;
						        SetBackgroundColor();
						        buttonFlag = false;
								if(model.inCheck(currentPlayer)) {
									board[model.kingRow][model.kingCol].setBackground(Color.red);	
								}
						        return;
							}
							buttonFlag = false;
							CheckForPromotion();
							SetBackgroundColor();
							currentPlayer = currentPlayer.next();
							if(model.inCheck(currentPlayer)) {
								board[model.kingRow][model.kingCol].setBackground(Color.red);
								if(model.isComplete(currentPlayer)) {
									if(currentPlayer == Player.WHITE) {
										JOptionPane.showMessageDialog(null, "Checkmate Black Wins!");
									}
									else {
										JOptionPane.showMessageDialog(null, "Checkmate White Wins!");
									}
									System.exit(1);
								}
							}
							if(computerPlayer) {
								model.computerMove(currentPlayer);
								CheckForPromotion();
								SetBackgroundColor();
								if(model.inCheck(currentPlayer)) {
									board[model.kingRow][model.kingCol].setBackground(Color.red);
									if(model.isComplete(currentPlayer)) {
										if(currentPlayer == Player.BLACK) {
											JOptionPane.showMessageDialog(null, "Check Mate White Wins!");
										}
										else {
											JOptionPane.showMessageDialog(null, "Check Mate Black Wins!");
										}
										System.exit(1);
									}
								}
								buttonFlag = false;
								currentPlayer = currentPlayer.next();
								if(model.inCheck(currentPlayer)) {
									board[model.kingRow][model.kingCol].setBackground(Color.red);
									if(model.isComplete(currentPlayer)) {
										if(currentPlayer == Player.BLACK) {
											JOptionPane.showMessageDialog(null, "Check Mate White Wins!");
										}
										else {
											JOptionPane.showMessageDialog(null, "Check Mate Black Wins!");
										}
										System.exit(1);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}