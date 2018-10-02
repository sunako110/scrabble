import java.rmi.*;
 

public interface ScrabbleClientInt extends Remote{
	public void update (String name)throws RemoteException ;
	public String getName()throws RemoteException ;
	public void addPlayer(String s) throws RemoteException;
}
