package ProgressReview;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ScrabbleGUI extends JFrame {
	public static final int BOARD_SIZE = 20;
	public static final int HAND_SIZE = 7;
	private static final long serialVersionUID = 1L;
	private static final char EMPTY_CHAR = (char) 0;
	private static final int NOT_SELECTED = -1;
	
	// Create buttons
	JButton clearButton = new JButton("Clear");
	JButton commitButton = new JButton("Commit");
	JButton passButton = new JButton("Pass");
	JButton exitButton = new JButton("Exit");	
	
	// Create frames
	JFrame gameFrame = new JFrame("Scrabble Game");
	final JPanel Field = new JPanel();
	JPanel votingField = new JPanel();
	
	// Create game elements
	ScrabbleGame game;
	ScrabblePlayer player = new ScrabblePlayer();
	LetterButtonGroup btnGroup = new LetterButtonGroup();
	GridButton board[][] = new GridButton[BOARD_SIZE][BOARD_SIZE];
	LetterButton letter[] = new LetterButton[HAND_SIZE];
	Color btnColor = new Color(69, 128, 224);
	JButton vote[] = new JButton[10];
	JTextArea word[] = new JTextArea[10];
	ScrabbleMove move[] = new ScrabbleMove[HAND_SIZE];
	private int indexSelected = NOT_SELECTED;
	
	public ScrabbleGUI() {
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		votingField.setLayout(new GridLayout(10,2,0,0));
		votingField.setBounds(640, 270, 280, 300);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 40);
		gameFrame.setLayout(null);
		
		this.game = new ScrabbleGame(new ScrabblePlayer[] {this.player});
		
		setGridButton(Field);
		setVotingArea(votingField);
		setLetterBar(gameFrame);
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
		
		
		
		for (int i = 0; i < HAND_SIZE; i++) {
			move[i] = new ScrabbleMove();
		}
	}
	
	public void addIntroduction(JFrame frame) {
		JTextArea introduction = new JTextArea(5,30);
		introduction.setText("How to play:\nPlease select a tile,\n"
				+ "and then select where you want to place it.");
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
	
	public void setLetterBar(JFrame frame) {
		// Change background of letter if selected
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean noneSelected = true;
				for (int i = 0; i < HAND_SIZE; i++) {
					if (!letter[i].isSelected()) {
						letter[i].setFocusPainted(false);
						letter[i].setBackground(btnColor);
					} else {
						noneSelected = false;
						letter[i].setBackground(Color.RED);
						indexSelected = i;
					}
				}
				if (noneSelected) {
					// If none are selected, set index to default value
					indexSelected = NOT_SELECTED;
				}
			}
		};
		
		// Create letter bar/player hand
		for (int i = 0; i < HAND_SIZE; i++) {
			// Create button
			ScrabbleTile tile = game.getTile();
			
			LetterButton lb = new LetterButton(tile);
			lb.setBounds(640 + i*40, 200, 40, 40);
	        
			// Set colours
			lb.setBackground(btnColor);
			lb.setRolloverEnabled(true);
			lb.setHoverBackgroundColor(Color.PINK);
			lb.setPressedBackgroundColor(Color.PINK);
			lb.setHorizontalTextPosition(SwingConstants.CENTER);
			lb.setBorder(null);
			
			// Show button
			frame.add(lb);
			lb.addActionListener(click);
			btnGroup.add(lb);
			letter[i] = lb;
		}
	}
	
	public void setGridButton(JPanel field) {
        ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GridButton btn = (GridButton) e.getSource();
				if (btn.isEnabled()) {
					int gridIndex = btn.getLetterIndex();
					
					// If grid button already has letter, re-enable the letter
					if (gridIndex != NOT_SELECTED) {
						letter[gridIndex].setEnabled(true);
						letter[gridIndex].setSelected(false);
					}
					
					// If a letter is selected, set grid button and disable letter
					if (indexSelected != NOT_SELECTED) {
						char lt = letter[indexSelected].getLetter();
						int col = btn.getColumn();
						int row = btn.getRow();
						
						// Set ScrabbleMove to be sent to server
						ScrabbleCell cell = new ScrabbleCell(col, row);
						ScrabbleTile tile = new ScrabbleTile(lt);
						ScrabbleMove m = new ScrabbleMove(tile, cell);
						move[indexSelected] = m;					
						
						// Set grid letter
						btn.setLetter(lt);
						btn.setLetterIndex(indexSelected);
						
						// Disable letter in letter bar
						letter[indexSelected].setEnabled(false);
						letter[indexSelected].setSelected(false);
						letter[indexSelected].setBackground(btnColor);
					} else {
						// If letter not selected, remove current grid button letter
						if (gridIndex != NOT_SELECTED) {
							btn.setLetter(EMPTY_CHAR);
							
							// Set empty ScrabbleMove
							move[gridIndex] = new ScrabbleMove();
						}
					}
				}
				indexSelected = NOT_SELECTED;
			}
		};
		
		// Create all grid buttons for the board
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new GridButton(i, j);
				board[i][j].setFocusable(false);
		        field.add(board[i][j]);
				board[i][j].addActionListener(click);
			}
		}
	}
	
	public void setVotingArea(JPanel field) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 2; j++) {
				if (j==0) {
					word[i] = new JTextArea();
					field.add(word[i]);
				} else {
					vote[i] = new JButton("YES");
					vote[i].setFocusable(false);
					field.add(vote[i]);
				}
			}
		}
	}
	
	public void setClearButton(JFrame frame) {
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Re-enable letter bar and clear current moves
				for(int i = 0; i < HAND_SIZE; i++) {
					letter[i].setEnabled(true);
					letter[i].setBackground(btnColor);
					move[i] = new ScrabbleMove();
					}
				// Re-enable all grid cells
				for (int i = 0; i < BOARD_SIZE; i++) { 
					for (int j = 0; j < BOARD_SIZE; j++) {
						if(board[i][j].isEnabled()) {
						board[i][j].setLetter(EMPTY_CHAR);
						}
					}
				}	
			}
		};
		
		// Create clear button
		clearButton.setBounds(640,150,90,40);
		clearButton.setFocusable(false);
		frame.add(clearButton);
		clearButton.addActionListener(click);
	}
	
	public void setCommitButton(JFrame frame) {
		ActionListener click = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < HAND_SIZE; i++) {
					if (!move[i].emptyMove()) {
						ScrabbleCell c = move[i].getCell();
						GridButton cell = board[c.getColumn()][c.getRow()];
						int gridIndex = cell.getLetterIndex();
						
						ScrabbleTile newLetter = game.getTile();
						letter[gridIndex].setTile(newLetter);
						letter[gridIndex].setEnabled(true);
						letter[gridIndex].setSelected(false);
						cell.setEnabled(false);
					}
					move[i] = new ScrabbleMove();
				}
			}
		};
		
		// Create commit button
		commitButton.addActionListener(click);
		commitButton.setBounds(735,150,90,40);
		commitButton.setFocusable(false);
		frame.add(commitButton);
	}
	
	public void setPassButton(JFrame frame) {
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		};
		
		// Create pass button
		passButton.setBounds(830,150,90,40);
		passButton.setFocusable(false);
		frame.add(passButton);
		passButton.addActionListener(click);
	}
	
	public void setExitButton(JFrame frame) {
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
		
		// Create exit button
		exitButton.setBounds(830,585,90,40);
		exitButton.setFocusable(false);
		frame.add(exitButton);
		exitButton.addActionListener(click);
	}

	public static void main(String[] args) {
		new ScrabbleGUI();
	}
}
