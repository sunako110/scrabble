import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ScrabbleServer implements Runnable {
	protected Socket clientSocket = null;
	
	public ScrabbleServer(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	
	
	public void out(String out) throws UnsupportedEncodingException, IOException {
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
		output.write(out+"\n");	
		output.flush();
	}


	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			String message = null, command = null, content = null;
			while((message = input.readLine()) != null) {
				command = message.substring(0,message.indexOf("&"));
				content = message.substring(message.indexOf("&")+1);
				if(command.equals("connect")) {
					System.out.println("connect");
					ScrabbleManager.getSrabbleManager().add(this,content);
				}else if (command.equals("start")) {
					System.out.println("start");
					ScrabbleManager.getSrabbleManager().startGame();;	
				}else if (command.equals("vote")) {
					if(content.equals("yes")) {
						System.out.println("yes");
						ScrabbleManager.getSrabbleManager().addVote();
					}else if (content.equals("no")) {
						System.out.println("no");
						ScrabbleManager.getSrabbleManager().voteReject();
					}
				}else if(command.equals("pass")) {
					ScrabbleManager.getSrabbleManager().addPass();		
				}else if(command.equals("commit")) {
					//System.out.println(content);
					ScrabbleManager.getSrabbleManager().setThisScore(content.length());
					ScrabbleManager.getSrabbleManager().publish("wordForVote&"+content);
				}else if (command.equals("setPane")) {
					//System.out.println(content);
					ScrabbleManager.getSrabbleManager().setPane(content);
				}else if(command.equals("gameEnds")) {
					System.out.println("End game...");
					ScrabbleManager.getSrabbleManager().endGame();
				}
				
				
				
				
			}
		} catch (IOException e) {
			System.out.println("Error in sending message.");
			System.out.println("**********************************************************************");
		}
		
		
		
		
	}
}
