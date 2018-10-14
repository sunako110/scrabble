import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
public class gameUI extends JFrame {
	public static final int BOARD_SIZE = 20;
	public static final int HAND_SIZE = 7;
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> wordList = new ArrayList<String>();
	
	JFrame gameFrame = new JFrame("Scrabble");
	JButton clearButton = new JButton("Clear");
	JButton commitButton = new JButton("Commit");
	JButton passButton = new JButton("Pass");
	JButton exitButton = new JButton("Exit");
	final JPanel Field = new JPanel();
//	DefaultListModel listModel = new DefaultListModel();
//	JList list = new JList(listModel);
	JDialog dialog;
	//JPanel votingField = new JPanel();
	GridButton buttons[][] = new GridButton[BOARD_SIZE][BOARD_SIZE];
	LetterButton letters[] = new LetterButton[HAND_SIZE];
	JButton vote[] = new JButton[10];
	JTextArea word[] = new JTextArea[10];
	JButton voteButtonYes = new JButton();
	JButton voteButtonNo = new JButton();
	JTextArea wordArea = new JTextArea();
	JTextArea currentPlayerArea = new JTextArea();
	static MessageThread messageThread;
	private Character commitStore[][] = new Character[BOARD_SIZE][BOARD_SIZE];
	private BufferedWriter output;
	char selectedLetter = 0;
	String username;
	boolean isFirstMove;
	
//	private int score = 0;
	
	public gameUI(MessageThread thread, BufferedWriter output, String playerName) {
		this.output = output;
		username = playerName;
		messageThread = thread;
		messageThread.addGameUI(this);
		gameFrame.setTitle("Scrabble: " + playerName);
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 40);
		gameFrame.setLayout(null);
		
		setGridButton(Field);
		setLetterBar(gameFrame);
		setClearButton(gameFrame);
		setCommitButton(gameFrame);
		setPassButton(gameFrame);
		setExitButton(gameFrame);
		addIntroduction(gameFrame);
		addVotingAreaLabel(gameFrame);
		addVotingAreaLabel(gameFrame);
		addVotingArea(gameFrame);
		addCurrentPlayerArea(gameFrame);
		
		gameFrame.add(Field);
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
		        	try {
						output.write("gameEnds& "+"\n");
						output.flush();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(gameFrame, "ERROR, we couldn't send data...");
					}
		        }
		    }
		});
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
		introduction.setText("Do you think they are all proper words?");
		introduction.setBounds(640, 250, 300, 20);
		introduction.setEditable(false);
		introduction.setBackground(null);
		frame.add(introduction);
	}
	
	public void addCurrentPlayerArea(JFrame frame) {
		currentPlayerArea = new JTextArea(5,30);
		currentPlayerArea.setText("Current Player: ");
		currentPlayerArea.setBounds(640, 400, 300, 20);
		currentPlayerArea.setEditable(false);
		currentPlayerArea.setBackground(null);
		frame.add(currentPlayerArea);
	}
	
	
	public void setLetterBar(JFrame frame) {
		ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LetterButton tmpbutton = (LetterButton) e.getSource();
				if (tmpbutton.isFocusPainted()) {
					refreshFocusPaint(letters);
					//tmpbutton.setBackground(new Color(3, 59, 90));
				} else {
					selectedLetter = tmpbutton.getLetter();
					refreshFocusPaint(letters);
					//tmpbutton.setBackground(Color.RED);
					tmpbutton.setFocusPainted(true);
				}
			}
		};	
			
		for (int i = 0; i < HAND_SIZE; i++) {
			// Request letter and send
			letters[i]= new LetterButton();
			letters[i].setBounds(640 + i*40, 200, 40, 40);
			letters[i].setFocusPainted(false);
/*			if(!player.getTurn()) {
				letters[i].setEnabled(false);
			}*/
	    //    letters[i].setForeground(new Color(0, 135, 200).brighter());
	    //    letters[i].setHorizontalTextPosition(SwingConstants.CENTER);
	   //     letters[i].setBorder(null);
	   //     letters[i].setBackground(new Color(3, 59, 90));
	  //      letters[i].setHoverBackgroundColor(new Color(3, 59, 90).brighter());
	  //      letters[i].setPressedBackgroundColor(Color.PINK);
			frame.add(letters[i]);
			letters[i].addActionListener(click);		
		}
	}
	
	public void refreshFocusPaint(LetterButton[] letters) {
		for(int j = 0; j < HAND_SIZE; j++) {
			letters[j].setFocusPainted(false);
		}
	}
		
	public void setGridButton(JPanel field) {
        ActionListener click = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GridButton tmpbutton = (GridButton) e.getSource();
				if (tmpbutton.isEnabled() && (selectedLetter != 0)) {
					if(!isFirstMove) {
						if(!testValidity(tmpbutton.getRow(),tmpbutton.getColumn())) {
							JOptionPane.showMessageDialog(gameFrame, "ERROR: the letters must connect together...Try again");
							selectedLetter = 0;
							refreshFocusPaint(letters);
							tmpbutton.setLetter((char) 0);
	
						}
					}else {
						isFirstMove = false;
					}
					
					tmpbutton.setLetter(selectedLetter);
					
					for (int i = 0; i < HAND_SIZE; i++) {
						if (letters[i].isFocusPainted()) {
							letters[i].setEnabled(false);
							letters[i].setSelected(true);
						}
					}
				}
			/*	if (tmpbutton.isEnabled() && selectedLetter == 0) {
					int index = tmpbutton.getLetterIndex();
					if (index != -1) {
						letters[index].setEnabled(true);
						letters[index].setSelected(false);
						tmpbutton.setLetter((char) 0);
					}
				}*/
				selectedLetter = 0;
			}
		};
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				buttons[i][j] = new GridButton();
				buttons[i][j].setLetterLocation(i,j);
				buttons[i][j].setFocusable(false);
		        field.add(buttons[i][j]);
				buttons[i][j].addActionListener(click);
			}
		}
	}
	
	public void setFirstMove() {
		boolean valid = true;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(buttons[i][j].getIcon()!=null) {
					valid = false;
				}
			}
		}
		isFirstMove = valid;
	}
	

	
	public void addVotingArea(JFrame frame) {		
		
		wordArea = new JTextArea(10,10);
		wordArea.setBounds(640, 300, 150,100);
		voteButtonYes = new JButton("Yes");
		voteButtonYes.setFocusable(false);
		voteButtonYes.setBounds(810, 300, 100, 40);
		voteButtonYes.setEnabled(false);
		voteButtonNo = new JButton("No");
		voteButtonNo.setFocusable(false);
		voteButtonNo.setBounds(810, 360, 100, 40);
		voteButtonNo.setEnabled(false);
		
		ActionListener clickYes = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					output.write("vote&yes\n");
					output.flush();
					voteButtonYes.setEnabled(false);
					voteButtonNo.setEnabled(false);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(gameFrame, "ERROR, we couldn't send data...");
				}
			}
		};
		voteButtonYes.addActionListener(clickYes);
		
		ActionListener clickNo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					output.write("vote&no\n");
					output.flush();
					voteButtonYes.setEnabled(false);
					voteButtonNo.setEnabled(false);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(gameFrame, "ERROR, we couldn't send data...");
				}
			}
		};
		voteButtonNo.addActionListener(clickNo);
		
		
		frame.add(wordArea);
		frame.add(voteButtonYes);
		frame.add(voteButtonNo);
		
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
				setFirstMove();	
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
				
				//Store new added letter and its position
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						if (buttons[i][j].getIcon() != null) {
							commitStore[i][j] = buttons[i][j].getLetter();
						}else {
							commitStore[i][j] = 0;
						}
					}
				}
				findWords(commitStore);
	/*			for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						if ((buttons[i][j].isEnabled()) && (buttons[i][j].getIcon() != null)) {
							//System.out.println("play check");
							buttons[i][j].setEnabled(false);
						}
					}
				}*/
				
		/*		for (int i = 0; i < HAND_SIZE; i++) {
					if (letters[i].isSelected()) {
						letters[i].setEnabled(true);
						letters[i].setSelected(false);
						letters[i].refreshLetter();
					}
					
				}*/
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
				try {
					output.write("pass& \n");
					output.flush();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(gameFrame, "ERROR, we couldn't send data...");
				}
				
				
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
	            		try {
							output.write("gameEnds& "+"\n");
							output.flush();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(gameFrame, "ERROR, we couldn't send data...");
						}
	            }
			}
		};
		exitButton.addActionListener(click);
	}
	

	
	
	
	public void startVoting(String word) {
		wordArea.setText(null);
		wordArea.setText(word);
		voteButtonYes.setEnabled(true);
		voteButtonNo.setEnabled(true);
	}
	
	public void wordNotAccepted() {
		JOptionPane.showMessageDialog(gameFrame, "Your word(s) are not accepted by all players.Please try again or press \"pass\" button.");
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
	
	
	public void endGame(String scoreBoard) {
		gameFrame.setVisible(false);
		new EndgameUI(scoreBoard);
	}
	

	
	public void setTurn(String username) {
		if(this.username.equals(username)) {
			currentPlayerArea.setText("Your Turn Now!");
			YourTurn();
		}else {
			currentPlayerArea.setText("Current Player: "+username);
			for(int i = 0; i<HAND_SIZE;i++) {
				letters[i].setEnabled(false);
			}
		clearButton.setEnabled(false);
		commitButton.setEnabled(false);
		passButton.setEnabled(false);
		voteButtonYes.setEnabled(false);
		voteButtonNo.setEnabled(false);
		}
		
	}
	
	public void YourTurn() {
		for(int i = 0; i<HAND_SIZE;i++) {
			letters[i].setEnabled(true);
		}
		clearButton.setEnabled(true);
		commitButton.setEnabled(true);
		passButton.setEnabled(true);
		voteButtonYes.setEnabled(false);
		voteButtonNo.setEnabled(false);
		selectedLetter = 0;
	}
	
		
	public void findWords(Character[][] tmpBoard) {
		ArrayList<String> newWordList = new ArrayList<String>();
		HashMap<String,String> actualWord = new HashMap<>();
		for (int i = 0; i < BOARD_SIZE; i++) {
			String word ="";
			String position = "";
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(tmpBoard[i][j]!=0) {
					word = word + tmpBoard[i][j].toString();
					position = position+Integer.toString(i)+","+Integer.toString(j)+",";
				}else {
					word = word + " ";
					position = position + Character.toString('&');
				}
			}
		//	actualWord.put(word, position);
			StringTokenizer st = new StringTokenizer(word);
			StringTokenizer sstt = new StringTokenizer(position,"&");
			while (st.hasMoreTokens()) {
		         String w = st.nextToken();
		         String p = sstt.nextToken("&");
		         if(!wordList.contains(w) && w.length()>1) {
		        	 newWordList.add(w);
		        	 actualWord.put(w,p);
		        	 wordList.add(w);
		         }
		     }
		}
		
		for (int j = 0; j < BOARD_SIZE; j++) {
			String word = "";
			String position = "";
			for (int i = 0; i < BOARD_SIZE; i++) {
				if(tmpBoard[i][j]!=0) {
					word = word + tmpBoard[i][j].toString();
					position = position+Integer.toString(i)+","+Integer.toString(j)+",";
				}else if(tmpBoard[i][j]==0) {
					word = word + " ";
					position = position + Character.toString('&');
				}
			}	
			
			StringTokenizer st = new StringTokenizer(word);
			StringTokenizer sstt = new StringTokenizer(position,"&");
			while (st.hasMoreTokens()) {
		         String w = st.nextToken();
		         String p = sstt.nextToken("&");
		         if(!wordList.contains(w) && w.length()>1) {
		        	 newWordList.add(w);
		        	 actualWord.put(w,p);
		        	 wordList.add(w);
		         }
		     }
		}
		Object[] possibilities = new Object[newWordList.size()];
		for(int i=0; i<newWordList.size();i++) {
			possibilities[i]=newWordList.get(i);
		}
		String word = (String)JOptionPane.showInputDialog(gameFrame,"Choose your word:",
				"",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
		if((word!=null)&&(word.length()>0)) {
			String pi = actualWord.get(word);
			StringTokenizer st = new StringTokenizer(pi,",");
			char[] tmp = new char[400];
			try {
				int c = 0;
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						if(buttons[i][j].getLetter() != '0' && !buttons[i][j].isEnabled()) {
							tmp[c] = (char)buttons[i][j].getLetter();
						}else {
							tmp[c]= '0';
						}
						c+=1;
					}
				}
				int count = 0;
				while(st.hasMoreTokens()) {
					int row, col,po;
					row = Integer.parseInt(st.nextToken(","));
					col = Integer.parseInt(st.nextToken(","));
					po = row*20+col;
					tmp[po]=word.charAt(count); 
					//System.out.println(tmp[po]);
					count+=1;
				}
				String pane = String.valueOf(tmp);
				//System.out.println(pane);
				output.write("commit&"+word+"\n");
				output.write("setPane&"+pane+"\n");
				//System.out.println(pane);
				output.flush();
			} catch (IOException e) {
				 JOptionPane.showMessageDialog(gameFrame, "ERROR, we couldn't send data...");
			}
	}
	}

	
	public void updatePane(String list) {
		int count = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				//System.out.println(list.charAt(count));
				if(list.charAt(count)!='0') {
					//System.out.println("why");
					buttons[i][j].setLetter(list.charAt(count)) ;
					buttons[i][j].setEnabled(false);
				}else {
					buttons[i][j].setLetter((char)0);
					buttons[i][j].setEnabled(true);
				}
				count+=1;
			}
		}
		
		wordList.clear();
		for (int i = 0; i < BOARD_SIZE; i++) {
			String word ="";
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(!buttons[i][j].isEnabled()) {
					word = word + Character.toString(buttons[i][j].getLetter());
				}else {
					word = word + " ";
				}
			}
		
			
			StringTokenizer st = new StringTokenizer(word);
			while (st.hasMoreTokens()) {
		         String w = st.nextToken();
		        	 wordList.add(w);
		         
		     }
		}
		
		for (int j = 0; j < BOARD_SIZE; j++) {
			String word = "";
			for (int i = 0; i < BOARD_SIZE; i++) {
				if(!buttons[i][j].isEnabled()) {
					word = word + Character.toString(buttons[i][j].getLetter());
				}else {
					word = word + " ";
				}
			}	
			
			StringTokenizer st = new StringTokenizer(word);
			while (st.hasMoreTokens()) {
		         String w = st.nextToken();
		        	 wordList.add(w);
		     }
		
		}
	}
	
	public void setFirstMove(String username) {
		if(this.username.equals(username)) {
			isFirstMove = true;
		}
	}
	
	public boolean testValidity(int tmprow,int tmpcolumn) {
		boolean valid = false;
		//test the buttons at four corners
		if(tmprow==0 && tmpcolumn==0) {
			if(!(buttons[tmprow+1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn+1].getIcon()==null)) {
				valid = true;
			}
		}else if(tmprow==19 && tmpcolumn==19) {
			if(!(buttons[tmprow-1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn-1].getIcon()==null)) {
				valid = true;
			}
		}else if(tmprow==0 && tmpcolumn==19) {
			if(!(buttons[tmprow+1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn-1].getIcon()==null)) {
				valid = true;
			}
		}else if(tmprow==19 && tmpcolumn==0) {
			if(!(buttons[tmprow-1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn+1].getIcon()==null)) {
				valid = true;
			}
		}
		//test the buttons on edges
		else if(tmprow==0) {
			if(!(buttons[tmprow+1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn+1].getIcon()==null
					&&buttons[tmprow][tmpcolumn-1].getIcon()==null)) {
				valid = true;
			}
		}else if(tmprow==19) {
			if(!(buttons[tmprow-1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn+1].getIcon()==null
					&&buttons[tmprow][tmpcolumn-1].getIcon()==null)) {
				valid = true;
			}
		}else if(tmpcolumn==0) {
			if(!(buttons[tmprow+1][tmpcolumn].getIcon()==null&&buttons[tmprow-1][tmpcolumn].getIcon()==null
					&&buttons[tmprow][tmpcolumn+1].getIcon()==null)) {
				valid = true;
			}
		}else if(tmpcolumn==19) {
			if(!(buttons[tmprow+1][tmpcolumn].getIcon()==null&&buttons[tmprow-1][tmpcolumn].getIcon()==null
					&&buttons[tmprow][tmpcolumn-1].getIcon()==null)) {
				valid = true;
			}
		}
		//test all other buttons
		else {
			if(!(buttons[tmprow-1][tmpcolumn].getIcon()==null&&buttons[tmprow][tmpcolumn+1].getIcon()==null
					&&buttons[tmprow][tmpcolumn-1].getIcon()==null&&buttons[tmprow+1][tmpcolumn].getIcon()==null)) {
				valid = true;
			}
		}
		
		return valid;
	}
	
		
		
/*		for (int i = 0; i < HAND_SIZE; i++) {
			// reset letterBar
			if (letters[i].isSelected()) {
				letters[i].setSelected(false);
				letters[i].refreshLetter();
			}
			System.out.println(player.getTurn());
			if(!player.getTurn()) {
				letters[i].setEnabled(false);
			}else {
				letters[i].setEnabled(true);
				
			}
			letters[i].setFocusPainted(false);
		}*/
	

	
	
/*	public static void main(String[] args) {
		new gameUI();
	}*/
}