package Scrabble;

// Default constants for the (score, frequency) of each letter

public enum TileAttributes {
	// Uses frequencies of SuperScrabble
	
	A(1, 16), B(3, 4), C(3, 6), D(2, 8), E(1, 24), F(4, 4), 
	G(2, 5), H(4, 5), I(1, 13), J(8, 2), K(5, 2), L(1, 7), 
	M(3, 6), N(1, 13), O(1, 15), P(3, 4), Q(10, 2), R(1, 13), 
	S(1, 10), T(1, 15), U(1, 7), V(4, 3), W(4, 4), X(8, 2), 
	Y(4, 4), Z(10, 2), BLANK(0, 4);

	// Character representation of empty and blank tiles
	public static final char EMPTY_SYMBOL = '*';
	public static final char BLANK_SYMBOL = '#';	
	
	private final int score;
	private final int frequency;
	
	TileAttributes(int score, int frequency) {
		this.score = score;
		this.frequency = frequency;
	}
	
	// Getter methods
	public int getFrequency() {
		return this.frequency;
	}
	
	public int getScore() {
		return this.score;
	}
	
	// Finds total number of tiles by summing all frequencies
	public static int totalNumTiles() {
		int total = 0;
		
		for (TileAttributes t : TileAttributes.values()) {
			total += t.getFrequency();
		}
		return total;
	}
}