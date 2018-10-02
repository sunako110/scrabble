import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ScrabbleClient extends UnicastRemoteObject implements ScrabbleClientInt{

	private String name;
	private LoginUI loginUi;	
//	private GameUI gameUI;

	
	protected ScrabbleClient() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ScrabbleClient (String n) throws RemoteException {
		name=n;
		}
	
	
	public void addPlayer(String st){
		System.out.println(st);
		loginUi.addPlayerPool(st);
	}

	public void setLoginUI(LoginUI t){ 
		loginUi = t ; 
	} 	
	
	@Override
	public void update(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

}
