package scramble;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class gameUI extends JFrame {
	public static final int BOARD_SIZE = 20;
	public static final int HAND_SIZE = 7;
	private static final long serialVersionUID = 1L;
	
	JFrame gameFrame = new JFrame("Scrabble");
	JButton clearButton = new JButton("Clear");
	JButton commitButton = new JButton("Commit");
	JButton passButton = new JButton("Pass");
	JButton exitButton = new JButton("Exit");
	final JPanel Field = new JPanel();
	JPanel votingField = new JPanel();
	GridButton buttons[][] = new GridButton[BOARD_SIZE][BOARD_SIZE];
	LetterButton letters[] = new LetterButton[HAND_SIZE];
	JButton vote[] = new JButton[10];
	JTextArea word[] = new JTextArea[10];
	
	char selectedLetter = 0;
	
	public gameUI(String name) {
		gameFrame.setTitle("Scrabble: " + name);
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		votingField.setLayout(new GridLayout(10,2,0,0));
		votingField.setBounds(640, 270, 280, 300);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 40);
		gameFrame.setLayout(null);
		
		setGridButton();
		setVotingArea(votingField);
		setClearButton(gameFrame);
		setCommitButton(gameFrame);
		setPassButton(gameFrame);
		setExitButton(gameFrame);
		addIntroduction(gameFrame);
		addVotingAreaLabel(gameFrame);
		
		gameFrame.add(Field);
		gameFrame.add(votingField);
	
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(gameFrame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            	System.exit(0);
		        }
		    }
		});
	}
	
	public void addIntroduction(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("How to play:\n"
		+ "Please select the alphabet first,\n" + "and then select where you want to place it.");
		introduction.setBounds(640, 20, 300, 100);
		introduction.setEditable(false);
		introduction.setBackground(null);
		frame.add(introduction);
	}
	
	public void addVotingAreaLabel(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("Do you think it is a word?");
		introduction.setBounds(640, 250, 300, 20);
		introduction.setEditable(false);
		introduction.setBackground(null);
		frame.add(introduction);
	}
	
	
	public void setLetterBar() {
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LetterButton tmpbutton = (LetterButton) e.getSource();
				if (tmpbutton.isFocusPainted()) {
					for (int j = 0; j < HAND_SIZE; j++) {
						refreshFocusPaint(letters);
					}
				} else {
					refreshFocusPaint(letters);
					tmpbutton.setFocusPainted(true);
				}
				if (tmpbutton.isFocusPainted()) {
					selectedLetter = tmpbutton.getLetter();
				}
			}
		};	
			
		for (int i = 0; i < HAND_SIZE; i++) {
			letters[i]= new LetterButton();
			letters[i].setBounds(640 + i*40, 200, 40, 40);
			letters[i].setFocusPainted(false);
			gameFrame.add(letters[i]);
			letters[i].addActionListener(click);		
		}
	}
	
	public void refreshFocusPaint(LetterButton[] letters) {
		for(int j = 0; j < HAND_SIZE; j++) {
			letters[j].setFocusPainted(false);
		}
	}
		
	public void setGridButton() {
        ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GridButton tmpbutton = (GridButton) e.getSource();
				if (tmpbutton.isEnabled() && (selectedLetter != 0)) {
					int index = tmpbutton.getLetterIndex();
					if (index != -1) {
						letters[index].setEnabled(true);
						letters[index].setSelected(false);
					}
					tmpbutton.setLetter(selectedLetter);
					
					for (int i = 0; i < HAND_SIZE; i++) {
						if (letters[i].isFocusPainted()) {
							tmpbutton.setLetterIndex(i);
							letters[i].setEnabled(false);
							letters[i].setSelected(true);
						}
					}
				}
				selectedLetter = 0;
			}
		};
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				buttons[i][j] = new GridButton();
				buttons[i][j].setFocusable(false);
				Field.add(buttons[i][j]);
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
				for(int i = 0; i < HAND_SIZE; i++) {
					letters[i].setEnabled(true);
					letters[i].setFocusPainted(false);
					}
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						if(buttons[i][j].isEnabled()) {
						buttons[i][j].setLetter((char) 0);
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
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						if ((buttons[i][j].isEnabled()) && (buttons[i][j].getIcon() != null)) {
							//System.out.println("play check");
							buttons[i][j].setEnabled(false);
						}
					}
				}
				for (int i = 0; i < HAND_SIZE; i++) {
					if (letters[i].isSelected()) {
						letters[i].setEnabled(true);
						letters[i].setSelected(false);
						letters[i].refreshLetter();
					}
					
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
	            int confirm = JOptionPane.showOptionDialog(frame,
	            		"Are you sure you want to close this window?", 
	            		"Close Window?", JOptionPane.YES_NO_OPTION,
	                    JOptionPane.QUESTION_MESSAGE, null, null, null);
	            if (confirm == JOptionPane.YES_OPTION) {
	                System.exit(0);
	            }
			}
		};
		exitButton.addActionListener(click);
	}

	
/*	public static void main(String[] args) {
		new mainwindow();
	}*/
}