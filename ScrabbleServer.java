import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ScrabbleServer extends UnicastRemoteObject implements ScrabbleServerInt {

	private Vector v=new Vector();
	ArrayList<String> nameList = new ArrayList<String>();
	
	
	protected ScrabbleServer() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(ScrabbleClientInt a) throws RemoteException {
		System.out.println(a.getName() + "  got connected....");	
		//a.tell("You have Connected successfully.");
		//publish(a.getName()+ " has just connected.");
		v.add(a);
		return true;	
	}

	@Override
	public void publishPlayer() throws RemoteException {
		nameList.clear();
		for(int i=0;i<v.size();i++){
			ScrabbleClientInt n=(ScrabbleClientInt)v.get(i);
     		nameList.add(n.getName());
		}
	
		
			for(int i=0;i<v.size();i++){
			    try{
			     	ScrabbleClientInt tmp=(ScrabbleClientInt)v.get(i);
			     	tmp.addPlayer(nameList);
			    }catch(Exception e){
			    	//problem with the client not connected.
			    	//Better to remove it
			    }
			}
	}
	
	

	@Override
	public Vector getConnected() throws RemoteException {
		return v;
	}

	@Override
	public void publish(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
