package ProgressReview;

import java.util.StringJoiner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ScrabblePlayer {
	public static final int HAND_SIZE = 7;
	
	private ScrabbleTile[] hand = new ScrabbleTile[HAND_SIZE];
	private boolean currentTurn = false;
	private int numTiles = 0;
	private int score = 0;
	
	// Add a tile to hand if not full
	public boolean addTile(ScrabbleTile tile) {
		if (numTiles == HAND_SIZE) {
			return false;
		}
		hand[numTiles] = tile;
		numTiles++;
		return true;
	}
	
	// Checks whether player has a single tile
	public boolean hasTile(ScrabbleTile tile) {
		for (int i = 0; i < numTiles; i++) {
			if (tile == this.hand[i]) {
				return true;
			}
		}
		return false;
	}
	
	// Checks whether player has all tiles for the turn
	public boolean hasTiles(ScrabbleTurn turn) {
		List<ScrabbleTile> tiles = new ArrayList<ScrabbleTile>(Arrays.asList(this.hand));
		ScrabbleMove[] move = turn.getMoves();
		
		for(ScrabbleMove m : move) {
			if (!tiles.remove(m.getTile())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean useTiles(ScrabbleTurn turn) {
		if (!hasTiles(turn)) {
			return false;
		}
		for (ScrabbleMove move : turn.getMoves()) {
			
			numTiles--;
		}
		return true;
	}
	
	// Get hand as ScrabbleTile[] object
	public ScrabbleTile[] getHand() {
		return this.hand;
	}
	
	// Print all tiles in hand with delimiter ','
	public String printHand() {
		StringJoiner sj = new StringJoiner(",", "{", "}");
		for (int i = 0; i < numTiles; i++) {
			sj.add(hand[i].toString());
		}
		return sj.toString();
	}
	
	// Getter and setter methods
	public int getNumTiles() {
		return this.numTiles;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public boolean getPlayerTurn() {
		return this.currentTurn;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void updateScore(int points) {
		this.score += points;
	}
}
