import java.rmi.*;
import java.util.ArrayList;
import java.util.Map;
 

public interface ScrabblePlayerInt extends Remote{
	public void update (String name)throws RemoteException ;
	public String getName()throws RemoteException ;
	public void addPlayer(ArrayList<String> s) throws RemoteException;
//	public boolean addTile(ScrabbleTile tile)throws RemoteException;
//	public boolean hasTile(ScrabbleTile tile)throws RemoteException;
//	public boolean hasTiles(ScrabbleTurn turn)throws RemoteException;
//	public ScrabbleTile[] getHand()throws RemoteException;
	public int getScore()throws RemoteException;
	public void updateScore(int points)throws RemoteException;
//	public char[] printHand()throws RemoteException;
//	public void cannotStartGame()throws RemoteException;
	public void startGame()throws RemoteException;
	public void setTurn(boolean turn)throws RemoteException;
	boolean getTurn()throws RemoteException;
	public int getNumTiles()throws RemoteException;
	public void addWord(ArrayList<String> wordList)throws RemoteException;
	public void rejected()throws RemoteException;
	public void newTurn(Character[][] board)throws RemoteException;
	public void addScore(ArrayList<String> a) throws RemoteException;
	public void endGame(Map<String,Integer> a) throws RemoteException;
	
}