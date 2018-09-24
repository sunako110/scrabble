package Scrabble;

import java.util.Arrays;

public class ScrabbleCell {
	// Cell contains board coordinate information
	public static final int NUM_ADJACENT_CELLS = 4;
	
	private int column;
	private int row;
	
	public ScrabbleCell(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	// Finds all orthogonally adjacent cells of current cell: returned in array
	public ScrabbleCell[] adjacentCells() {
		int numAdj = 0;
		ScrabbleCell[] coord = new ScrabbleCell[NUM_ADJACENT_CELLS];
		ScrabbleCell[] c = new ScrabbleCell[NUM_ADJACENT_CELLS];
		
		// Create all 4 orthogonally adjacent cells
		c[0] = new ScrabbleCell(this.column - 1, this.row);
		c[1] = new ScrabbleCell(this.column + 1, this.row);
		c[2] = new ScrabbleCell(this.column, this.row - 1);
		c[3] = new ScrabbleCell(this.column, this.row + 1);
		
		// Only add valid cells
		for (int i = 0; i < c.length; i++) {
			if (c[i].validCell()) {
				coord[numAdj] = c[i];
				numAdj++;
			}
		}
		return Arrays.copyOf(coord, numAdj);
	}
	
	// Check if cell is valid
	public boolean validCell() {
		int size = ScrabbleGame.BOARD_SIZE;
		return !(this.column < 0 || this.column >= size || 
				this.row < 0 || this.row >= size);
	}
	
	// Getter methods
	public int getColumn() {
		return this.column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	@Override
	public String toString() {
		return "(" + this.column + "," + this.row + ")";
	}
}
