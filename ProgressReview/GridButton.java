package ProgressReview;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GridButton extends JButton {
	private static final long serialVersionUID = 1L;
	private static final int INVALID_INDEX = -1;
	private static final char EMPTY_CHAR = (char) 0;
	
	// Instance variables
	private int column;
	private int row;
	private char letter = EMPTY_CHAR;
	private int letterIndex = INVALID_INDEX;

	public GridButton() {

	}
	
	public GridButton(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	// Set letter and icon
	public void setLetter(char letter) {
		this.letter = letter;
		char ch = Character.toLowerCase(letter);
		ImageIcon icon = new ImageIcon(LetterButton.iconLocation(ch));
		setIcon(icon);
	}
	
	public char getLetter() {
		return this.letter;
	}
	
	public void setEmpty() {
		setIcon(null);
		this.letter = EMPTY_CHAR;
		this.letterIndex = INVALID_INDEX;
	}
	
	public void setLetterIndex(int index) {
		this.letterIndex = index;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getLetterIndex() {
		return this.letterIndex;
	}
}
