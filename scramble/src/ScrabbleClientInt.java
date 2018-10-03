package scramble;

import java.rmi.*;
import java.util.ArrayList;
 

public interface ScrabbleClientInt extends Remote{
	public void update (String name)throws RemoteException ;
	public String getName()throws RemoteException ;
	public void addPlayer(ArrayList<String> s) throws RemoteException;
	public boolean addTile(ScrabbleTile tile)throws RemoteException;
	public boolean hasTile(ScrabbleTile tile)throws RemoteException;
	public boolean hasTiles(ScrabbleTurn turn)throws RemoteException;
	public ScrabbleTile[] getHand()throws RemoteException;
	public String printHand()throws RemoteException;
	public int getScore()throws RemoteException;
	public void updateScore(int points)throws RemoteException;
}
