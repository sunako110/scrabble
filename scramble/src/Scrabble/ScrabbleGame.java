package Scrabble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScrabbleGame {
	public static final int BOARD_SIZE = 20;
	
	private ScrabbleTile[][] board;
	private Character[] tileBag;
	private int tilesLeft;
	private ScrabblePlayer[] player;
	private int numPlayers;
	
	public ScrabbleGame(ScrabblePlayer[] player) {
		// Initialize empty board
		board = new ScrabbleTile[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; i < BOARD_SIZE; j++) {
				board[i][j] = new ScrabbleTile();
			}
		}
		
		// Create fixed tile bag
		int index = 0;
		tilesLeft = TileAttributes.totalNumTiles();
		tileBag = new Character[tilesLeft];
		
		for (TileAttributes t : TileAttributes.values()) {
			for (int i = 0; i < t.getFrequency(); i++) {
				char letter;
				
				if (t.toString().equals("BLANK")) {
					letter = TileAttributes.BLANK_SYMBOL;
				} else {
					letter = t.toString().charAt(0);
				}
				
				tileBag[index] = letter;				
				index++;
			}
		}
		
		List<Character> list = Arrays.asList(tileBag);
		Collections.shuffle(list);
		tileBag = list.toArray(new Character[list.size()]);
		
		this.player = player;
		this.numPlayers = player.length;
	}
	
	public boolean placeTile(ScrabbleTile tile, int column, int row) {
		if (!emptyTile(column, row)) {
			return false;
		}
		board[column][row] = tile;
		return true;
	}
	
	public boolean emptyTile(int column, int row) {
		return board[column][row].getLetter() == TileAttributes.EMPTY_SYMBOL;
	}
	
	public ScrabbleTile getTile() {
		if (tilesLeft == 0) {
			return new ScrabbleTile();
		}
		tilesLeft--;
		return new ScrabbleTile(tileBag[tilesLeft]);
	}
	
	public void fillHand(ScrabblePlayer player) { 
		while (player.getNumTiles() < ScrabblePlayer.HAND_SIZE) {
			player.addTile(getTile());
		}
	}
	
	public void startHand() {
		for (int i = 0; i < numPlayers; i++) {
			fillHand(player[i]);
		}
	}
	
}
