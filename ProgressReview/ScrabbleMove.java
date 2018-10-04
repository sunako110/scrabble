package ProgressReview;

public class ScrabbleMove {
	// Represents placement of a single tile on board (tile and cell pairing)
	private ScrabbleTile tile;
	private ScrabbleCell cell;
	
	public ScrabbleMove() {
		this.tile = new ScrabbleTile();
		this.cell = new ScrabbleCell();
	}
	
	public boolean emptyMove() {
		return this.cell.emptyCell() || this.tile.emptyTile();
	}
	
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
