import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GridButton extends JButton{
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
	int row;
	int column;
	
	char letter = 0;
	int letterIndex = -1;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GridButton() {
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
		
	}
	
	public void setLetter(char letter) {
		this.letter = letter;
		switch(letter) {
		case (char)'a':
			setIcon(A);
			break;
		case (char)'b':
			setIcon(B);
			break;
		case (char)'c':
			setIcon(C);
			break;
		case (char)'d':
			setIcon(D);
			break;
		case (char)'e':
			setIcon(E);
			break;
		case (char)'f':
			setIcon(F);
			break;
		case (char)'g':
			setIcon(G);
			break;
		case (char)'h':
			setIcon(H);
			break;
		case (char)'i':
			setIcon(I);
			break;
		case (char)'j':
			setIcon(J);
			break;
		case (char)'k':
			setIcon(K);
			break;
		case (char)'l':
			setIcon(L);
			break;
		case (char)'m':
			setIcon(M);
			break;
		case (char)'n':
			setIcon(N);
			break;
		case (char)'o':
			setIcon(O);
			break;
		case (char)'p':
			setIcon(P);
			break;
		case (char)'q':
			setIcon(Q);
			break;
		case (char)'r':
			setIcon(R);
			break;
		case (char)'s':
			setIcon(S);
			break;
		case (char)'t':
			setIcon(T);
			break;
		case (char)'u':
			setIcon(U);
			break;
		case (char)'v':
			setIcon(V);
			break;
		case (char)'w':
			setIcon(W);
			break;
		case (char)'x':
			setIcon(X);
			break;
		case (char)'y':
			setIcon(Y);
			break;
		case (char)'z':
			setIcon(Z);
			break;
		default:
			setIcon(null);
			break;
		}
	}
	
	public char getLetter() {
		return this.letter;
	}
	
	public boolean hasLetter() {
		return letter == 0;
	}
	
	public void setLetterIndex(int index) {
		this.letterIndex = index;
	}
	
	public int getLetterIndex() {
		return this.letterIndex;
	}
}