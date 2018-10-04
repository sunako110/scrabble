package ProgressReview;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class LetterButton extends JToggleButton {
	
	private static final long serialVersionUID = 1L;
	private static String DIRECTORY = "src/image/";
	public static int ALPHABET_LENGTH = 26;
	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor;
	
	private ScrabbleTile tile;
	
	@Override
	public void setContentAreaFilled(boolean b) {
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(pressedBackgroundColor);
		} else if (getModel().isRollover()) {
			g.setColor(hoverBackgroundColor);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(0,  0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	
	public LetterButton(ScrabbleTile tile) {
		this.tile = tile;
		ImageIcon icon = new ImageIcon(iconLocation(tile.getLetter()));
		setIcon(icon);
		super.setContentAreaFilled(false);
	}
	
	// Get image location
	public static String iconLocation(char letter) {
		return DIRECTORY + "letter-" + Character.toLowerCase(letter) + ".png";
	}		
	
	// Getter and setter methods
	public char getLetter() {
		return this.tile.getLetter();
	}
	
	public ScrabbleTile getTile() {
		return this.tile;
	}
	
	public void setTile(ScrabbleTile tile) {
		this.tile = tile;
		ImageIcon icon = new ImageIcon(iconLocation(this.tile.getLetter()));
		setIcon(icon);
	}
	
	// Appearance methods
	public Color getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}
	
	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}
	
	public Color getPressedBackgroundColor() {
		return pressedBackgroundColor;
	}
	
	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
		this.pressedBackgroundColor = pressedBackgroundColor;
	}
	

}