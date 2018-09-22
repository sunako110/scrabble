package scramble;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class mainwindow{
	
	JFrame gameFrame = new JFrame("Scramble");
	final JPanel Field = new JPanel();
	//JPanel numberField = new JPanel(null);
	GridButton buttons[][] = new GridButton[20][20];
	LetterButton letters[] = new LetterButton[7];
	ClearButton clearButton; 
	
	public mainwindow() {
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 80);
		gameFrame.setLayout(null);
		
		setGridButton(Field);
		setLetterBar(gameFrame);
		addLabelToFrame(gameFrame);
		setClearButton(gameFrame);
		
		
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 7; i++) {
					letters[i].setEnabled(true);
					}
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						buttons[i][j].setIcon(null);
					}
				}	
				
			}
		});
		
		gameFrame.add(Field);
	
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		

	}
	
	public void addLabelToFrame(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("How to play:\n"
		+ "Please select the alphabet first,\n" +"and then select where you want to place it.");
		introduction.setBounds(640, 20, 300, 100);
		introduction.setEditable(false);
		introduction.setBackground(null);
		frame.add(introduction);
	}
	
	public void setLetterBar(JFrame frame) {
		for(int i = 0; i < 7; i++) {
			letters[i] = new LetterButton();
			letters[i].setBounds(640 + i*40, 100, 40, 40);
			frame.add(letters[i]);
			System.out.println(letters[i].getLetter());
		}
	}
		
	public void setGridButton(JPanel field) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				buttons[i][j] = new GridButton();
		        field.add(buttons[i][j]);
			}
		}
	}
	
	
	public void setClearButton(JFrame frame) {
		clearButton = new ClearButton("Clear");
		clearButton.setBounds(640,150,100,40);
		frame.add(clearButton);
	}
	
	
	
	public static void main(String[] args) {
		new mainwindow();
	}
}
