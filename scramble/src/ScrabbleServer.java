import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ScrabbleServer extends UnicastRemoteObject implements ScrabbleServerInt {

	private Vector<ScrabblePlayerInt> playerList=new Vector<ScrabblePlayerInt>();
	ArrayList<String> nameList = new ArrayList<String>();
	ScrabbleGame game;
	
	
	protected ScrabbleServer() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(ScrabblePlayerInt a) throws RemoteException {
		System.out.println(a.getName() + "  got connected....");	
		//a.tell("You have Connected successfully.");
		//publish(a.getName()+ " has just connected.");
		playerList.add(a);
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
			     	ScrabblePlayerInt tmp=(ScrabblePlayerInt)v.get(i);
			     	tmp.addPlayerList(playerList);
			    }catch(Exception e){
			    	//problem with the client not connected.
			    	//Better to remove it
			    }
			}
	}*/
	
	

	@Override
	public Vector<ScrabblePlayerInt> getConnected() throws RemoteException {
		return playerList;
	}

	@Override
	public void publish(String s) throws RemoteException {
		nameList.clear();
		for(int i=0;i<playerList.size();i++){
			ScrabblePlayerInt n=(ScrabblePlayerInt)playerList.get(i);
			nameList.add(n.getName());
		}
	
		
			for(int i=0;i<playerList.size();i++){
			    try{
			     	ScrabblePlayerInt tmp=(ScrabblePlayerInt)playerList.get(i);
			     	tmp.addPlayer(nameList);
			    }catch(Exception e){
			    	//problem with the client not connected.
			    	//Better to remove it
			    }
			}
			
/*			for(int i = 0; i <playerList.size()-1;i++) {
				ScrabblePlayerInt tmp=(ScrabblePlayerInt)playerList.get(i);
				tmp.cannotStartGame();
			}
	*/	
	}

	@Override
	public void startGame() throws RemoteException {
		
		for(int i = 0; i < playerList.size(); i++) {
			//System.out.println(playerList.get(i).getName());
			playerList.get(i).startGame();
		}
	/*	for(int i = 0; i < playerList.size()-1; i++) {
			System.out.println(playerList.get(i).getName());
			ScrabblePlayerInt tmp = (ScrabblePlayerInt)playerList.get(i);
			tmp.startGame();
			
		}*/
	}
	
	

}