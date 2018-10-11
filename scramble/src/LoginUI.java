import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoginUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame loginFrame = new JFrame("Login");
	final JPanel contentPane = new JPanel();;
	JTextField usernameField = new JTextField();;
	JTextField addressField = new JTextField();;
	
	JTextArea textArea = new JTextArea();
	JButton btnConnect = new JButton("Join the Game");
	JButton btnStart = new JButton("Start the Game");
	
	private ScrabblePlayer client;
	private ScrabbleServerInt server;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new LoginUI();
		System.out.println("Waiting for connection...");
		//LoginUI c = new LoginUI();
	}

	 public void doConnect(){
		    if (btnConnect.getText().equals("Join the Game")){
		    //	if (usernameField.getText().length()<2){JOptionPane.showMessageDialog(frame, "You need to type a name."); return;}
		  //  	if (ip.getText().length()<2){JOptionPane.showMessageDialog(frame, "You need to type an IP."); return;}	    	
		    	try{
		    		Registry registry = LocateRegistry.getRegistry(addressField.getText());
					client = new ScrabblePlayer(usernameField.getText());
		    		client.setLoginUI(this);
					server=(ScrabbleServerInt)registry.lookup("rmi://"+addressField.getText()+"/myabc");
		    	}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(contentPane, "ERROR, we wouldn't connect....");}		  
		      }else{
			}
		  } 
	 
	
	 public void print(Vector<ScrabblePlayerInt> v) throws RemoteException {
		 for(int i = 0; i < v.size(); i++) {
				System.out.println(v.get(i).getName());
			}
	 }
	 
	 public void startNewGame() {
		 //loginFrame.dispose();
		  loginFrame.setVisible(false);
		 client.setNewGame(new gameUI(client,server));
	 }
	 
	public void addPlayerPool(ArrayList<String> name) {
		textArea.setText(null);
		for(int i=0;i<name.size();i++) {
			textArea.append(name.get(i) + "\n");
		}
		
		//textArea.getText()+"\n"+
	}
	
	/**
	 * Create the frame.
	 */
	public LoginUI() {
		
		
	//	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	contentPane.setOpaque(false);
	//	contentPane.setLayout(null);
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
		    	  doConnect();
					try {
						if(server.checkName(usernameField.getText())==true) {
							server.login(client);
							server.clearName();
							server.addNametoList();
							server.publish(usernameField.getText());
							btnConnect.setEnabled(false);
						}else {
							JOptionPane.showMessageDialog(null, "The username has existed, please input again.", 
									"Invalid username", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
						print(server.getConnected());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	   
		           		try {
							server.startGame();
							//client.startGame();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
			    	  
			   } 
			});

		
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}