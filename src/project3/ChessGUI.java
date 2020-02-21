package project3;
/**
 * @author Christina Kidwell
 * @version winter 2018
 */
import java.awt.Dimension;
import javax.swing.JFrame;

public class ChessGUI {
	/**
	 * Sets up frame and fills it with cessPanel 
	 * @param args input string from command line  
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ChessPanel panel = new ChessPanel ();
		frame.getContentPane().add(panel);
		
		frame.setResizable(true);
		frame.setPreferredSize(new Dimension(800, 800));
		frame.pack();
		frame.setVisible(true);
	}
}
