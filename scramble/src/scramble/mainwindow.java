package scramble;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainwindow{
	
	JFrame gameFrame = new JFrame("Scramble");
	final JPanel Field = new JPanel();
	JPanel numberField = new JPanel(null);
	BlockButton buttons[][] = new BlockButton[20][20];
	
	public mainwindow() {
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		numberField.setBounds(20, 20, 20 * 30, 20 * 30);
		gameFrame.setSize(20 + 20 * 30 + 150, 20 + 20 * 30 + 80);
		gameFrame.setLayout(null);
		
		setButtonsAndListeners(Field);
		
		gameFrame.add(Field);
		gameFrame.add(numberField);
	
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
	}
		
	public void setButtonsAndListeners(JPanel field) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				buttons[i][j] = new BlockButton();
		        field.add(buttons[i][j]);
			}
		}
	}
	
	public static void main(String[] args) {
		new mainwindow();
	}
}
