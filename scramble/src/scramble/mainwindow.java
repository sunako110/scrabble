package scramble;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class mainwindow{
	ImageIcon A;
	ImageIcon B;
	ImageIcon C;
	ImageIcon D;
	ImageIcon E;
	ImageIcon F;
	ImageIcon G;
	ImageIcon H;
	ImageIcon I;
	ImageIcon J;
	ImageIcon K;
	ImageIcon L;
	ImageIcon M;
	ImageIcon N;
	ImageIcon O;
	ImageIcon P;
	ImageIcon Q;
	ImageIcon R;
	ImageIcon S;
	ImageIcon T;
	ImageIcon U;
	ImageIcon V;
	ImageIcon W;
	ImageIcon X;
	ImageIcon Y;
	ImageIcon Z;
	
	JFrame gameFrame = new JFrame("Scramble");
	JButton clearButton = new JButton("clear");
	JButton playButton = new JButton("Play");
	JButton passButton = new JButton("Pass");
	final JPanel Field = new JPanel();
	//JPanel numberField = new JPanel(null);
	GridButton buttons[][] = new GridButton[20][20];
	LetterButton letters[] = new LetterButton[7];
	
	char selectedLetter = 0;
	char letter = 0;
	
	public mainwindow() {
		Field.setLayout(new GridLayout(20, 20, 0, 0));
		Field.setBounds(20, 20, 20 * 30, 20 * 30);
		Field.setOpaque(false);
		gameFrame.setSize(20 + 20 * 30 + 320, 20 + 20 * 30 + 80);
		gameFrame.setLayout(null);
		
		setGridButton(Field);
		setLetterBar(gameFrame);
		setClearButton(gameFrame);
		setPlayButton(gameFrame);
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
	
	public void setClearButton(JFrame frame) {
		clearButton.setBounds(640,150,80,40);
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
	
	
	public void setPlayButton(JFrame frame) {
		playButton.setBounds(725,150,80,40);
		playButton.setFocusable(false);
		frame.add(playButton);
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
					
					A = new ImageIcon("src/image/letter-a.png");
					B = new ImageIcon("src/image/letter-b.png");
					C = new ImageIcon("src/image/letter-c.png");
					D = new ImageIcon("src/image/letter-d.png");
					E = new ImageIcon("src/image/letter-e.png");
					F = new ImageIcon("src/image/letter-f.png");
					G = new ImageIcon("src/image/letter-g.png");
					H = new ImageIcon("src/image/letter-h.png");
					I = new ImageIcon("src/image/letter-i.png");
					J = new ImageIcon("src/image/letter-j.png");
					K = new ImageIcon("src/image/letter-k.png");
					L = new ImageIcon("src/image/letter-l.png");
					M = new ImageIcon("src/image/letter-m.png");
					N = new ImageIcon("src/image/letter-n.png");
					O = new ImageIcon("src/image/letter-o.png");
					P = new ImageIcon("src/image/letter-p.png");
					Q = new ImageIcon("src/image/letter-q.png");
					R = new ImageIcon("src/image/letter-r.png");
					S = new ImageIcon("src/image/letter-s.png");
					T = new ImageIcon("src/image/letter-t.png");
					U = new ImageIcon("src/image/letter-u.png");
					V = new ImageIcon("src/image/letter-v.png");
					W = new ImageIcon("src/image/letter-w.png");
					X = new ImageIcon("src/image/letter-x.png");
					Y = new ImageIcon("src/image/letter-y.png");
					Z = new ImageIcon("src/image/letter-z.png");
					
					Random r = new Random();
					char c = (char) (r.nextInt(26) + 'a');
					char letter = c;
					
					switch(letter) {
					case (char)'a':
						letters[i].setIcon(A);
						letters[i].letter = letter;
						break;
					case (char)'b':
						letters[i].setIcon(B);
						letters[i].letter = letter;
						break;
					case (char)'c':
						letters[i].setIcon(C);
						letters[i].letter = letter;
						break;
					case (char)'d':
						letters[i].setIcon(D);
						letters[i].letter = letter;
						break;
					case (char)'e':
						letters[i].setIcon(E);
						letters[i].letter = letter;
						break;
					case (char)'f':
						letters[i].setIcon(F);
						letters[i].letter = letter;
						break;
					case (char)'g':
						letters[i].setIcon(G);
						letters[i].letter = letter;
						break;
					case (char)'h':
						letters[i].setIcon(H);
						letters[i].letter = letter;
						break;
					case (char)'i':
						letters[i].setIcon(I);
						letters[i].letter = letter;
						break;
					case (char)'j':
						letters[i].setIcon(J);
						letters[i].letter = letter;
						break;
					case (char)'k':
						letters[i].setIcon(K);
						letters[i].letter = letter;
						break;
					case (char)'l':
						letters[i].setIcon(L);
						letters[i].letter = letter;
						break;
					case (char)'m':
						letters[i].setIcon(M);
						letters[i].letter = letter;
						break;
					case (char)'n':
						letters[i].setIcon(N);
						letters[i].letter = letter;
						break;
					case (char)'o':
						letters[i].setIcon(O);
						letters[i].letter = letter;
						break;
					case (char)'p':
						letters[i].setIcon(P);
						letters[i].letter = letter;
						break;
					case (char)'q':
						letters[i].setIcon(Q);
						letters[i].letter = letter;
						break;
					case (char)'r':
						letters[i].setIcon(R);
						letters[i].letter = letter;
						break;
					case (char)'s':
						letters[i].setIcon(S);
						letters[i].letter = letter;
						break;
					case (char)'t':
						letters[i].setIcon(T);
						letters[i].letter = letter;
						break;
					case (char)'u':
						letters[i].setIcon(U);
						letters[i].letter = letter;
						break;
					case (char)'v':
						letters[i].setIcon(V);
						letters[i].letter = letter;
						break;
					case (char)'w':
						letters[i].setIcon(W);
						letters[i].letter = letter;
						break;
					case (char)'x':
						letters[i].setIcon(X);
						letters[i].letter = letter;
						break;
					case (char)'y':
						letters[i].setIcon(Y);
						letters[i].letter = letter;
						break;
					case (char)'z':
						letters[i].setIcon(Z);
					    letters[i].letter = letter;
						break;
					default:
						letters[i].setIcon(null);
						letters[i].letter = letter;
						break;
					}
				}
			}
		};
		playButton.addActionListener(click);
	}

	
	public static void main(String[] args) {
		new mainwindow();
	}
}
