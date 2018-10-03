package scramble;

import java.rmi.*;
import java.util.*;


public interface ScrabbleServerInt extends Remote {
	public boolean login (ScrabbleClientInt a)throws RemoteException ;
	public void publish (String s)throws RemoteException ;
	public Vector<ScrabbleClientInt> getConnected() throws RemoteException ;
	//public void publishPlayer() throws RemoteException;
}
