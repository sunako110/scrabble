import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MessageThread extends Thread {
	private BufferedReader reader;
	private gameUI game;
	private LoginUI login;
	
	public MessageThread(BufferedReader reader, LoginUI login) {
		this.reader = reader;
		this.login = login;
	}
	
	public void addGameUI(gameUI game) {
		this.game = game;
	}
	
	
	public void run() {
		String message = "";
		String command, list;
		while (true) {
			try {
				message = reader.readLine();
				command = message.substring(0, message.indexOf("&"));
				list = message.substring(message.indexOf("&")+1);
				if (command.equals("updateuser"))
				{
					StringTokenizer st = new StringTokenizer(list);
					login.textArea.setText(null);
					while(st.hasMoreTokens()) {
						login.textArea.append(st.nextToken() + "\n");
					}
				} else if (command.equals("start")) {
					login.loginFrame.setVisible(false);
					login.startNewGame();
					if(list.equals("false")) {
						game.notYourTurn();
					}
				} else if (command.equals("commit")) {
					
				} else if (command.equals("voteReject")) {
					game.wordNotAccepted();
					
				} else if (command.equals("voteAccept")) {
					game.updatePane(list);
					
				} else if (command.equals("gameEnds")) {
					game.endGame(list);
					
				} else if (command.equals("wordForVote")) {
					game.startVoting(list);
					
				} else if (command.equals("setTurn")) {
					if(list.equals("false")) {
						game.notYourTurn();
					}else {
						game.YourTurn();
					}
					
				} 
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
