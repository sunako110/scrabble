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
	JButton clearButton = new JButton("Clear");
	JButton commitButton = new JButton("Commit");
	JButton passButton = new JButton("Pass");
	JButton exitButton = new JButton("exit");
	final JPanel Field = new JPanel();
	JPanel votingField = new JPanel();
	GridButton buttons[][] = new GridButton[20][20];
	LetterButton letters[] = new LetterButton[7];
	JButton vote[] = new JButton[10];
	JTextArea word[] = new JTextArea[10];
	
	char selectedLetter = 0;
	
	public mainwindow() {
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		votingField.setLayout(new GridLayout(10,2,0,0));
		votingField.setBounds(640, 270, 280, 300);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 40);
		gameFrame.setLayout(null);
		
		setGridButton(Field);
		setVotingArea(votingField);
		setLetterBar(gameFrame);
		setClearButton(gameFrame);
		setCommitButton(gameFrame);
		setPassButton(gameFrame);
		setExitButton(gameFrame);
		addIntroduction(gameFrame);
		addVotingAreaLable(gameFrame);
		
		gameFrame.add(Field);
		gameFrame.add(votingField);
	
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		

	}
	
	public void addIntroduction(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("How to play:\n"
		+ "Please select the alphabet first,\n" +"and then select where you want to place it.");
		introduction.setBounds(640, 20, 300, 100);
		introduction.setEditable(false);
		introduction.setBackground(null);
		frame.add(introduction);
	}
	
	public void addVotingAreaLable(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("Do you think it is a word?");
		introduction.setBounds(640, 250, 300, 20);
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
						if(tmpbutton.isEnabled()&&(selectedLetter!=0)) {
							tmpbutton.setLetter(selectedLetter);
							for(int k = 0; k<7; k++) {
								if(letters[k].isFocusPainted()) {
									letters[k].setEnabled(false);
								}
							}
						}
						selectedLetter = 0;
					}
				};
				buttons[i][j].addActionListener(click);
			}
		}
		
	}
	
	public void setVotingArea(JPanel field) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 2; j++) {
				if(j==0) {
					word[i] = new JTextArea();
					field.add(word[i]);
				}else {
					vote[i] = new JButton("YES");
					vote[i].setFocusable(false);
					field.add(vote[i]);
				}
			}
		}
	}
	
	public void setClearButton(JFrame frame) {
		clearButton.setBounds(640,150,90,40);
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
						if(buttons[i][j].isEnabled()) {
						buttons[i][j].setLetter((char)0);
						}
					}
				}	
				
			}
		};
		clearButton.addActionListener(click);
	}
	
	
	public void setCommitButton(JFrame frame) {
		commitButton.setBounds(735,150,90,40);
		commitButton.setFocusable(false);
		frame.add(commitButton);
		ActionListener click = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if((buttons[i][j].isEnabled()) && (buttons[i][j].getIcon()!=null)) {
							//System.out.println("play check");
							buttons[i][j].setEnabled(false);
							
						}
					}
				}	
				for(int i = 0; i < 7; i++) {
					letters[i].setEnabled(true);
					letters[i].refreshLetter();
				}
			}
		};
		commitButton.addActionListener(click);
	}
	
	public void setPassButton(JFrame frame) {
		passButton.setBounds(830,150,90,40);
		passButton.setFocusable(false);
		frame.add(passButton);
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		};
		passButton.addActionListener(click);
	}
	
	public void setExitButton(JFrame frame) {
		exitButton.setBounds(830,585,90,40);
		exitButton.setFocusable(false);
		frame.add(exitButton);
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
		exitButton.addActionListener(click);
	}

	
	public static void main(String[] args) {
		new mainwindow();
	}
}
