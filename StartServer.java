import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class StartServer {
	public static void main(String[] args) {
		ServerSocket listeningSocket = null;
		Socket clientSocket = null;	
		int port = 5555;
		int i = 0; //counter to keep track of the number of clients
		try {
			listeningSocket = new ServerSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in opening the socket.");
			return;
		} catch (IllegalArgumentException e) {
			System.out.println("Port Number is invalid.");
			System.exit(0);
		}
		System.out.println("Server listening on port " + port +" for a connection");
		while (true) {
			try {
				clientSocket = listeningSocket.accept(); 
			} catch(IOException e) {
				System.out.println("Error: cannot accept client request. Exiting now.");
				return;
			}
			i++;
			System.out.println("Client connection number " + i + " accepted:");
			System.out.println("Remote Port: " + clientSocket.getPort());
			System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
			System.out.println("Local Port: " + clientSocket.getLocalPort());
			System.out.println("**********************************************************************");
			try {
				//create a new thread for each incoming message
				new Thread(new ScrabbleServer(clientSocket)).start();
			} catch(Exception e) {
				System.out.println("Error in creating the thread for client with conection number " + i + ".");
			}	
		}
	}
}

