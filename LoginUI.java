import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class LoginUI extends JFrame {
	JFrame loginFrame = new JFrame("Login");
	final JPanel contentPane = new JPanel();;
	JTextField usernameField = new JTextField();;
	JTextField addressField = new JTextField();;
	
	JTextArea textArea = new JTextArea();
	JButton btnConnect = new JButton("Join the Game");
	JButton btnStart = new JButton("Start the Game");
	String username;
	static Socket clientSocket = null;
	static BufferedReader input = null;
	static BufferedWriter output = null;
	static MessageThread messageThread;
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		new LoginUI();
		System.out.println("Waiting for connection...");	
	}
	
	
	public void doConnect(String ip, String name) throws UnknownHostException, IOException{
		int port = 5555;
		clientSocket = new Socket(ip,port);	
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));
		output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"));
		output.write("connect"+"&"+usernameField.getText()+"\n");
		output.flush();
		messageThread = new MessageThread(input, this);
		messageThread.start();
		btnConnect.setEnabled(false);
		username = name;
		
	}
	
	public void abruptClosing() {
		JOptionPane.showMessageDialog(contentPane, "ERROR, connection to server closed...");
		System.exit(0);
	}
	
	public void repeatedName() {
		JOptionPane.showMessageDialog(contentPane, "ERROR: username is already existed, please try another username...and connect again");
		usernameField.setText(null);
		addressField.setText(null);
		btnConnect.setEnabled(true);
	}
	
	public void startNewGame() {
		new gameUI(messageThread,output,username);
	}
	
	public LoginUI() {

			loginFrame.setBounds(100,100,400, 300);
			loginFrame.setLayout(null);
			
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setBounds(46, 27, 66, 16);
			loginFrame.add(lblUsername);
			
			usernameField.setBounds(124, 22, 130, 26);
			loginFrame.add(usernameField);
			usernameField.setColumns(10);
			
			JLabel lblServerAddress = new JLabel("Server Address:");
			lblServerAddress.setBounds(17, 74, 102, 16);
			loginFrame.add(lblServerAddress);
			
			addressField.setBounds(124, 69, 130, 26);
			addressField.setColumns(10);
			loginFrame.add(addressField);

			btnConnect.setBounds(275, 69, 117, 29);
			loginFrame.add(btnConnect);
			
			textArea.setBounds(17, 148, 235, 112);
			loginFrame.add(textArea);
			
			JLabel lblAvailablePlayers = new JLabel("Available Players:");
			lblAvailablePlayers.setBounds(17, 116, 117, 16);
			loginFrame.add(lblAvailablePlayers);
			
			btnStart.setBounds(275, 213, 117, 29);
			loginFrame.add(btnStart);
			
			btnConnect.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e){ 
				   try {
					   String hostIp = addressField.getText().trim();
					   String name = usernameField.getText().trim();
					   if (name.equals("") || hostIp.equals("")) {
						   throw new NoSuchElementException(); 
					   }
					  doConnect(hostIp,name);
				   }catch(NoSuchElementException exc) {
					   JOptionPane.showMessageDialog(contentPane, "ERROR, please enter a username or ip address....");
					   addressField.setText(null);
					   usernameField.setText(null);
				   }catch(UnknownHostException exc) {
					   JOptionPane.showMessageDialog(contentPane, "ERROR, we couldn't find the server...");
				   }catch(Exception exc) {
					   JOptionPane.showMessageDialog(contentPane, "ERROR, we couldn't connect...");
				   }
				   
			    	 
			   } 
			});
			
			btnStart.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					  int confirm = JOptionPane.showOptionDialog(loginFrame,
			            	"Are you sure you want to start a new game?", 
			            	"Start Game?", JOptionPane.YES_NO_OPTION,
			                   JOptionPane.QUESTION_MESSAGE, null, null, null);
			           if (confirm == JOptionPane.YES_OPTION) {
			        	   try {
							output.write("start& \n");
							output.flush();
						} catch (IOException exc) {
							 JOptionPane.showMessageDialog(contentPane, "ERROR, we couldn't start game...");
						}
			        	   //after start new game
			            }
				    	  
				   } 
				});

			loginFrame.setLocationRelativeTo(null);
			loginFrame.setVisible(true);
			loginFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
		}
}
