package scramble;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class mainwindow{
	
	JFrame gameFrame = new JFrame("Scramble");
	JButton clearButton = new JButton("clear");
	final JPanel Field = new JPanel();
	//JPanel numberField = new JPanel(null);
	GridButton buttons[][] = new GridButton[20][20];
	LetterButton letters[] = new LetterButton[7];
	
	char selectedLetter = 0;
	
	public mainwindow() {
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 80);
		gameFrame.setLayout(null);
		
		setGridButton(Field);
		setLetterBar(gameFrame);
		setClearButton(gameFrame);
		addLabelToFrame(gameFrame);
		
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
			letters[i]= new LetterButton();
			letters[i].setBounds(640 + i*40, 200, 40, 40);
			letters[i].setFocusPainted(false);
			frame.add(letters[i]);
			LetterButton tmpbutton = letters[i];
			ActionListener click = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tmpbutton.isFocusPainted()) {
						for(int j = 0; j < 7; j++) {
							refreshFocusPaint(letters);
						}
					}else {
						refreshFocusPaint(letters);
						tmpbutton.setFocusPainted(true);
					}
					if(tmpbutton.isFocusPainted()) {
						selectedLetter = tmpbutton.getLetter();
					}
				}
			};
			letters[i].addActionListener(click);		
		}
	}
	
	public void refreshFocusPaint(LetterButton[] letters) {
		for(int j = 0; j < 7; j++) {
			letters[j].setFocusPainted(false);
		}
	}
		
	public void setGridButton(JPanel field) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				buttons[i][j] = new GridButton();
				buttons[i][j].setFocusable(false);
		        field.add(buttons[i][j]);
		        GridButton tmpbutton = buttons[i][j];
		        ActionListener click = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selectedLetter!=0) {
							tmpbutton.setIcon(selectedLetter);
							tmpbutton.setLetter(selectedLetter);
							for(int k = 0; k<7; k++) {
								if(letters[k].isFocusPainted()) {
									letters[k].setEnabled(false);
								}
							}
						}
					}
				};
				buttons[i][j].addActionListener(click);
			}
		}
		
	}
	
	public void setClearButton(JFrame frame) {
		clearButton.setBounds(640,150,100,40);
		clearButton.setFocusable(false);
		frame.add(clearButton);
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 7; i++) {
					letters[i].setEnabled(true);
					letters[i].setFocusPainted(false);
					}
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						buttons[i][j].setIcon(null);
					}
				}	
				
			}
		};
		clearButton.addActionListener(click);
	}
	
	public static void main(String[] args) {
		new mainwindow();
	}
}
