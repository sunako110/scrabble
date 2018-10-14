import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class ScrabbleManager {
	private ScrabbleManager() {}
	private static final ScrabbleManager manager = new ScrabbleManager();
	public static ScrabbleManager getSrabbleManager() {
		return manager;	
	}
	
	ArrayList<String> nameList = new ArrayList<String>();
	Vector<ScrabbleServer> playerList = new Vector<ScrabbleServer>();
	HashMap<ScrabbleServer,Integer> scoreBoard = new HashMap<>();
	int voteNum = 0;
	int passNum = 0;
	int playerSeq;
	int score = 0;
	String pane = "";
	
	public void add(ScrabbleServer client, String username) throws UnsupportedEncodingException, IOException {
		playerList.add(client);
		System.out.println("add");
		publish(updateUserPool(username));
	}
	
	public void updateScoreBoard() {
		for(int i=0;i<playerList.size();i++) {
			scoreBoard.put(playerList.get(i),0);
		}
	}
	
	public String updateUserPool(String username) {
		String output = "updateuser&";
		System.out.println(username);
		nameList.add(username);
		for(int i = 0; i<playerList.size();i++) {
			output += nameList.get(i) + " ";
		}
		return output;
	}
	
	public void clearVoteNumber() {
		voteNum = 0;
	}
	
	public void addVote() throws UnsupportedEncodingException, IOException {
		voteNum+=1;
		if(voteNum == playerList.size()) {
			publish("voteAccept&"+pane);
			clearVoteNumber();
			scoreBoard.put(playerList.get(playerSeq), scoreBoard.get(playerList.get(playerSeq))+score);
			playerSeq++;
			if(playerSeq==playerList.size()) {
				clearPlayerSeq();
			}
			setTurn(playerSeq);
			
		}
	}
	
	public void voteReject() throws UnsupportedEncodingException, IOException {
		ScrabbleServer tmp = playerList.get(playerSeq);
		tmp.out("voteReject& ");
		voteNum = 0;
	}
	
	public void clearPassNum() {
		passNum = 0;
	}
	
	public void clearPlayerSeq() {
		playerSeq = 0;
	}
	
	public void addPass() throws UnsupportedEncodingException, IOException {
		passNum += 1;
		if(passNum == playerList.size()) {
			 System.out.println("End game...");
			 String st = "";
			 for(ScrabbleServer key: scoreBoard.keySet()) {
				 st += nameList.get(playerList.indexOf(key))+" "+scoreBoard.get(key)+" ";
			 }
			 publish("gameEnds&"+st);
		}else {
			playerSeq++;
			if(playerSeq==playerList.size()) {
				clearPlayerSeq();
				clearPassNum();
			}
			setTurn(playerSeq);
		}
		
	}
	
	public void setPane(String pane) {
		this.pane = pane;
	}
	
	public void setThisScore(int s) {
		this.score = s;
	}
	
	
	public void startGame() throws UnsupportedEncodingException, IOException{
		clearPlayerSeq();
		clearPassNum();
		clearVoteNumber();
		updateScoreBoard();
		ScrabbleServer tmp = playerList.get(0);
		tmp.out("start&true");
		for(int i = 1; i<playerList.size();i++) {
			tmp = playerList.get(i);
			tmp.out("start&false");
		}
	}
	
	public void setTurn(int playerSeq) throws UnsupportedEncodingException, IOException{
		for(int i = 0; i<playerList.size();i++) {
			if(i==playerSeq) {
				ScrabbleServer tmp = playerList.get(i);
				tmp.out("setTurn&true");
			}else {
				ScrabbleServer tmp = playerList.get(i);
				tmp.out("setTurn&false");
			}
		}
		
		
	}
	
	public void endGame() throws UnsupportedEncodingException, IOException {
		System.out.println("End game...");
		 String st = "";
		 for(ScrabbleServer key: scoreBoard.keySet()) {
			 st += nameList.get(playerList.indexOf(key))+" "+scoreBoard.get(key)+" ";
		 }
		 publish("gameEnds&"+st);
	}
	
	
	public void publish(String output) throws UnsupportedEncodingException, IOException {
		for(int i = 0; i<playerList.size();i++) {
			ScrabbleServer tmp = playerList.get(i);
			tmp.out(output);
		}	
	}
}
