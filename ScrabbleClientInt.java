import java.rmi.*;
import java.util.ArrayList;
 

public interface ScrabbleClientInt extends Remote{
	public void update (String name)throws RemoteException ;
	public String getName()throws RemoteException ;
	public void addPlayer(ArrayList<String> s) throws RemoteException;
}
