package Scrabble;

public class ScrabbleMove {
	// Represents placement of a single tile on board (tile and cell pairing)
	private ScrabbleTile tile;
	private ScrabbleCell cell;
	
	public ScrabbleMove(ScrabbleTile tile, ScrabbleCell cell) {
		this.tile = tile;
		this.cell = cell;
	}
	
	// Getter methods
	public ScrabbleTile getTile() {
		return this.tile;
	}
	
	public ScrabbleCell getCell() {
		return this.cell;
	}
}
