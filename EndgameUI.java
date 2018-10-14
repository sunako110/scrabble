import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class EndgameUI extends JFrame {
	private JFrame frame;
//	private ScrabblePlayer player;
//	private ScrabbleServerInt server;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */

	public EndgameUI(String scoreBoard) {

		frame = new JFrame("Scoreboard");
		frame.setLayout(null);
		frame.setBounds(100, 100, 300, 450);
	
		JLabel lblGameEnd = new JLabel("Game end!");
		lblGameEnd.setBounds(100, 20, 91, 16);
		frame.add(lblGameEnd);
		
		JTextArea textArea = new JTextArea();
	/*	String scoreBoard = "" ;
		for(String name: a.keySet() ) {
			String printOut ="User "+ name +  " got a score of " +a.get(name);
			scoreBoard = scoreBoard + printOut + "\n";
			System.out.println(scoreBoard);
		}*/
		StringTokenizer st = new StringTokenizer(scoreBoard);
		String results = "";
		while(st.hasMoreTokens()) {
			results += "user "+st.nextToken()+" has score of "+st.nextToken()+"\n";
		}
		
		
		
		
		textArea.setText(results);
		textArea.setSize(275,400);
		textArea.setBounds(20,50,250,400);
		textArea.setBackground(null);
		
		frame.add(textArea);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


}