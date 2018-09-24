package Scrabble;

import java.util.Arrays;
import java.util.Collections;

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
			for (int j = 0; j < BOARD_SIZE; j++) {
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
		
		// Randomize tile sequence and Player order
		Collections.shuffle(Arrays.asList(tileBag));
		Collections.shuffle(Arrays.asList(player));
		
		this.player = player;
		this.numPlayers = player.length;
	}
	
	public String printTiles() {
		return Arrays.toString(tileBag);
	}
	
	public ScrabbleTile getBoardCell(ScrabbleCell cell) {
		return board[cell.getColumn()][cell.getRow()];
	}
	
	public boolean emptyCell(ScrabbleCell cell) {
		return getBoardCell(cell).getLetter() == TileAttributes.EMPTY_SYMBOL;
	}
	
	public boolean placeTile(ScrabbleMove move) {
		ScrabbleCell cell = move.getCell();		
		if (!emptyCell(cell)) {
			return false;
		}
		board[cell.getColumn()][cell.getRow()] = move.getTile();
		return true;
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
	
	public void dealHand() {
		for (int i = 0; i < numPlayers; i++) {
			fillHand(player[i]);
		}
	}
	
	// Checks whether player has all tiles to make move
	// At least one tile must also be adjacent to an existing tile
	public boolean validMove(ScrabbleTurn turn) {
		ScrabblePlayer player = turn.getPlayer();
		
		if (!player.hasTiles(turn)) {
			return false;
		}
		
		// At least one tile per turn must be adjacent to existing tiles
		for (ScrabbleMove move : turn.getMoves()) {
			ScrabbleCell[] adj = move.getCell().adjacentCells();
			for (ScrabbleCell cell : adj) {
				if (!emptyCell(cell)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean firstMove(ScrabbleMove[] move) {
		return move.length >= 2;
	}
}
