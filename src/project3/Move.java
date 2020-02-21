package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
public class Move {
	public int fromRow, fromColumn, toRow, toColumn;

	public Move() {
	}

	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}