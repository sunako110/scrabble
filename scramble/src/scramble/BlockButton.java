package scramble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BlockButton extends JButton implements ActionListener {
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
	
	String letter = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BlockButton() {
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
		
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		letter = "a";
		switch(letter) {
		case "a":
			setIcon(A);
			break;
		case "":
			setIcon(null);
			break;
		}
		
		
	}

	
}
