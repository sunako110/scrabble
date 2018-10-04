package ProgressReview;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ScrabbleRemoteInterface extends Remote {
	ScrabbleTile[][] getBoard() throws RemoteException;
	ScrabbleTile[] getHand(ScrabblePlayer player) throws RemoteException;
	void playerTurn(ScrabbleTurn turn) throws RemoteException;
	boolean currentTurn(ScrabblePlayer player) throws RemoteException;
}
