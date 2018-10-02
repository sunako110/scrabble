import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoginUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField addressField;
	
	JTextArea textArea;
	JButton btnConnect;
	JButton btnStart;
	JList lst;
	
	private ScrabbleClient client;
	private ScrabbleServerInt server;
	
	ArrayList<String> nameListUI = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("Hello World !");
		//LoginUI c = new LoginUI();
	}

	 public void doConnect(){
		    if (btnConnect.getText().equals("Join the Game")){
		    //	if (usernameField.getText().length()<2){JOptionPane.showMessageDialog(frame, "You need to type a name."); return;}
		  //  	if (ip.getText().length()<2){JOptionPane.showMessageDialog(frame, "You need to type an IP."); return;}	    	
		    	try{
					client = new ScrabbleClient(usernameField.getText());
		    		    client.setLoginUI(this);
					server=(ScrabbleServerInt)Naming.lookup("rmi://"+addressField.getText()+"/myabc");
					server.login(client);
					server.publishPlayer();
		    	}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(contentPane, "ERROR, we wouldn't connect....");}		  
		      }else{
			}
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
		
		lst=new JList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(46, 27, 66, 16);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(124, 22, 130, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblServerAddress = new JLabel("Server Address:");
		lblServerAddress.setBounds(17, 74, 102, 16);
		contentPane.add(lblServerAddress);
		
		addressField = new JTextField();
		addressField.setBounds(124, 69, 130, 26);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		btnConnect = new JButton("Join the Game");
		btnConnect.setBounds(327, 69, 117, 29);
		contentPane.add(btnConnect);
		
		textArea = new JTextArea();
		textArea.setBounds(17, 148, 235, 112);
		contentPane.add(textArea);
		
		JLabel lblAvailablePlayers = new JLabel("Available Players:");
		lblAvailablePlayers.setBounds(17, 116, 117, 16);
		contentPane.add(lblAvailablePlayers);
		
		btnStart = new JButton("Start the Game");
		btnStart.setBounds(327, 213, 117, 29);
		contentPane.add(btnStart);
		
		btnConnect.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){ 
		    	  doConnect();   
		   } 
		});
		
		
		
	}
}
