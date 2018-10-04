import java.rmi.*;
import java.util.*;


public interface ScrabbleServerInt extends Remote {
	public boolean login (ScrabblePlayerInt a)throws RemoteException ;
	public void publish (String s)throws RemoteException ;
	public Vector<ScrabblePlayerInt> getConnected() throws RemoteException ;
	//public void publishPlayer() throws RemoteException;
	public void startGame() throws RemoteException;
	public void sendWord(Character[][] commitStore)throws RemoteException;
	public void vote(boolean b)throws RemoteException;
}