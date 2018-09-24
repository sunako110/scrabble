package Scrabble;

public class ScrabbleTile {

	private char letter;
	private int points;
	
	// Empty constructor initializes empty tile (to empty character symbol)
	public ScrabbleTile() {
		this.letter = TileAttributes.EMPTY_SYMBOL;
		this.points = 0;
	}
	
	// Create tile with letter and default so
	public ScrabbleTile(char letter) {
		this.letter = letter;
		
		// Convert char to default score value
		try {
			String ch = Character.toString(letter).toUpperCase();
			
			// Convert blank tile character symbol
			if (letter == TileAttributes.BLANK_SYMBOL) {
				ch = "BLANK";
			}
			
			int score = TileAttributes.valueOf(ch).getScore();
			this.points = score;
		} catch (IllegalArgumentException e) {
			this.points = 0;
		}
	}
	
	// Assign different points to tile
	public ScrabbleTile(char letter, int points) {
		this.letter = letter;
		this.points = points;
	}
	
	// Getter and Setter methods
	public char getLetter() {
		return this.letter;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	// String print values
	@Override
	public String toString() {
		return "(\"" + this.letter + "\", " + this.points + ")";
	}
	
	// Tiles are equal if both letter and point fields are the same
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !ScrabbleTile.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final ScrabbleTile tile = (ScrabbleTile) obj;
		
		return (this.letter == tile.letter && this.points == tile.points);
	}
}
