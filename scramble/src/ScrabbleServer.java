package scramble;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ScrabbleServer extends UnicastRemoteObject implements ScrabbleServerInt {
	public static final int BOARD_SIZE = 20;
	private Vector<ScrabblePlayerInt> playerList=new Vector<ScrabblePlayerInt>();
	ArrayList<String> nameList = new ArrayList<String>();
	//ScrabbleGame game;
	int playerNum, playerSeq,voteNum, voteYes;
	Character[][] board = new Character[BOARD_SIZE][BOARD_SIZE];
	Character[][] tmpBoard = new Character[BOARD_SIZE][BOARD_SIZE];
	ArrayList<String> wordList = new ArrayList<String>();
	
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
		playerNum++;
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
		for(int i=0;i<playerNum;i++){
			ScrabblePlayerInt n=(ScrabblePlayerInt)playerList.get(i);
			nameList.add(n.getName());
		}
	
		
			for(int i=0;i<playerNum;i++){
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
		Collections.shuffle(Arrays.asList(playerList));
		playerSeq = 0;
		playerList.get(playerSeq).setTurn(true);
		for(int i = 0; i < playerNum; i++) {
			//System.out.println(playerList.get(i).getName());
			playerList.get(i).startGame();
		}
	/*	for(int i = 0; i < playerList.size()-1; i++) {
			System.out.println(playerList.get(i).getName());
			ScrabblePlayerInt tmp = (ScrabblePlayerInt)playerList.get(i);
			tmp.startGame();
			
		}*/
	}
	
	public void sendWord(Character[][] board) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				tmpBoard[i][j]=board[i][j];
			}
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			String word = "";
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(tmpBoard[i][j]!=0) {
					word = word + tmpBoard[i][j].toString();
				}
			}
			if(!word.equals("")&&!(word.length()==1)) {
				wordList.add(word);
			}
		}
		for (int j = 0; j < BOARD_SIZE; j++) {
			String word = "";
			for (int i = 0; i < BOARD_SIZE; i++) {
				if(tmpBoard[i][j]!=0) {
					word = word + tmpBoard[i][j].toString();
				}
			}
			if(!word.equals("")&&!(word.length()==1)) {
				wordList.add(word);
			}
		}
		for(int i=0; i < wordList.size();i++) {
			System.out.println(wordList.get(i));
		}
		for(int i=0;i<playerNum;i++){
		    try{
		     	ScrabblePlayerInt tmp=(ScrabblePlayerInt)playerList.get(i);
		     	tmp.addWord(wordList);
		    }catch(Exception e){
		    	//problem with the client not connected.
		    	//Better to remove it
		    }
		}
		voteNum = 0;
		voteYes = 0;
	}
	
	synchronized public void vote(boolean votes) {
		voteNum++;
		if(votes ==true) {
			voteYes++;
		}
		if(voteNum == playerNum) {
			if(voteYes != playerNum) {
				try {
					System.out.println(playerList.get(playerSeq).getName() + "'s word(s) are not accepted.");
					playerList.get(playerSeq).rejected();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						board[i][j]=tmpBoard[i][j];
					}
				}
				for(int i=0;i<playerNum;i++){
				    try{
				     	ScrabblePlayerInt tmp=(ScrabblePlayerInt)playerList.get(i);
				     	tmp.newTurn(board);
				    }catch(Exception e){
				    	//problem with the client not connected.
				    	//Better to remove it
				    }
				}
				try {
					playerList.get(playerSeq).setTurn(false);
					playerSeq++;
					playerList.get(playerSeq).setTurn(true);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	

}