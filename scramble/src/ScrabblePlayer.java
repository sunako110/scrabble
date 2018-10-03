import java.util.StringJoiner;
import java.util.List;
import java.util.Arrays;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ScrabblePlayer extends UnicastRemoteObject implements ScrabblePlayerInt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private LoginUI login;
	public gameUI game;
	public static final int HAND_SIZE = 7;
	
	private ScrabbleTile[] hand = new ScrabbleTile[HAND_SIZE];
	private int numTiles = 0;
	private int score = 0;
	
	protected ScrabblePlayer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ScrabblePlayer (String n) throws RemoteException {
		username=n;
		}
	
	public void addPlayer(ArrayList<String> st){
		System.out.println(st);
		login.addPlayerPool(st);
	}

	public void setLoginUI(LoginUI t){ 
		login = t ; 
	} 	
	
	@Override
	public void update(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() throws RemoteException {
		return username;
	}
	
	// Add a tile to hand if not full
/*	public boolean addTile(ScrabbleTile tile) {
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
	
	// Get hand as ScrabbleTile[] object
	public ScrabbleTile[] getHand() {
		return this.hand;
	}
*/	
	// Print all tiles in hand with delimiter ','
/*	public char[] printHand() {
		char [] letters = new char[HAND_SIZE]; 
		for (int i = 0; i < numTiles; i++) {
			letters[i] = hand[i].getLetter();
		}
		game.setLetterBar();
		return letters;
	}
*/	
	public void cannotStartGame() throws RemoteException {
		login.btnStart.setEnabled(false);
	}
	
	public void startGame() {
		System.out.println("starting game...");
		login.startNewGame();
	}
	
	public void setNewGame(gameUI game) {
		this.game = game;
	}
	
	
	// Getter and setter methods
	public int getNumTiles() {
		return this.numTiles;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void updateScore(int points) {
		this.score += points;
	}
}