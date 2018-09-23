package Scrabble;

import java.util.StringJoiner;

public class ScrabblePlayer {
	public static final int HAND_SIZE = 7;
	
	private ScrabbleTile[] hand = new ScrabbleTile[HAND_SIZE];
	private int numTiles = 0;
	private int score = 0;
	
	public boolean addTile(ScrabbleTile tile) {
		if (numTiles == HAND_SIZE) {
			return false;
		}
		hand[numTiles] = tile;
		numTiles++;
		return true;
	}
	
	public ScrabbleTile[] getHand() {
		return this.hand;
	}
	
	public String printHand() {
		StringJoiner sj = new StringJoiner(",", "{", "}");
		
		for (int i = 0; i < numTiles; i++) {
			sj.add(hand[i].toString());
		}
		return sj.toString();
	}
	
	public int getNumTiles() {
		return this.numTiles;
	}
	
	public int getScore() {
		return this.score;
	}
	
}
