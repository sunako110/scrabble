package scramble;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ScrabbleServer extends UnicastRemoteObject implements ScrabbleServerInt {

	private Vector<ScrabbleClientInt> v=new Vector<ScrabbleClientInt>();
	ArrayList<ScrabblePlayer> playerList = new ArrayList<ScrabblePlayer>();
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

/*	@Override
	public void publishPlayer() throws RemoteException {
		playerList.clear();
		for(int i=0;i<v.size();i++){
			ScrabblePlayer n=(ScrabblePlayer)v.get(i);
			playerList.add(n);
		}
	
		
			for(int i=0;i<v.size();i++){
			    try{
			     	ScrabbleClientInt tmp=(ScrabbleClientInt)v.get(i);
			     	tmp.addPlayerList(playerList);
			    }catch(Exception e){
			    	//problem with the client not connected.
			    	//Better to remove it
			    }
			}
	}*/
	
	

	@Override
	public Vector<ScrabbleClientInt> getConnected() throws RemoteException {
		return v;
	}

	@Override
	public void publish(String s) throws RemoteException {
		playerList.clear();
		nameList.clear();
		for(int i=0;i<v.size();i++){
			ScrabbleClientInt n=(ScrabbleClientInt)v.get(i);
			playerList.add(new ScrabblePlayer(n.getName()));
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

}