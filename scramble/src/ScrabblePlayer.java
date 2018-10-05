import java.util.StringJoiner;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
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
	public EndgameUI end;
	public static final int HAND_SIZE = 7;
	
	private ScrabbleTile[] hand = new ScrabbleTile[HAND_SIZE];
	private int numTiles = 0;
	private int score = 0;
	private boolean isTurn = false;
	
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
	

	//start game UI
	public void startGame() {
		System.out.println("starting game...");
		login.startNewGame();
	}
	
	//set game UI for the player
	public void setNewGame(gameUI game) {
		this.game = game;
	}
	

	public void endGame(Map<String,Integer> a) {
		System.out.println("ending game...");
		game.endGame(a);
	}
	
	//set game UI for the player
	public void setEndGame(EndgameUI end) {
		this.end = end;
	}
	
	
	
	public void addScore(ArrayList<String> a) throws RemoteException {
		for(int i=0;i<a.size();i++) {
			score += a.get(i).length();
		}
	}
	
	//display new word to in the text area
	public void addWord(ArrayList<String> word) {
		game.addWordList(word);
	}
	
	public void setTurn(boolean turn) {
		isTurn = turn;
	}

	
	public boolean getTurn() {
		return isTurn;
	}
	
	public void rejected() {
		game.wordNotAccepted();
	}
	
	public void newTurn(Character[][] board) {
		game.setNewTurn(board);
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