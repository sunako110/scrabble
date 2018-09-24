package Scrabble;

public class ScrabbleTurn {
	// Represents a player turn as a set of multiple moves
	private ScrabblePlayer player;
	private ScrabbleMove[] move;
	
	public ScrabbleTurn(ScrabblePlayer p, ScrabbleMove[] m) {
		this.player = p;
		this.move = m;
	}
	
	// Getter methods
	public ScrabblePlayer getPlayer() {
		return this.player;
	}
	
	public ScrabbleMove[] getMoves() {
		return this.move;
	}
}
