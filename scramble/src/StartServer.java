import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.RMISecurityManager;

public class StartServer {
	
	public static void main(String[] args) {
		Registry registry;
		
		try {
			
				//System.setSecurityManager(new RMISecurityManager());
				//System.setProperty("java.security.policy","file:///home/.../<filename>.policy");
			 //	java.rmi.registry.LocateRegistry.createRegistry(1099);
				
				ScrabbleServerInt b=new ScrabbleServer();	
			 	
			 	registry = LocateRegistry.createRegistry(1099);
			 	registry.rebind("rmi://192.168.1.196/myabc", b);
			 	
			 	
				
				//Naming.rebind("rmi://10.13.90.195/myabc", b);
				System.out.println("[System] Server is ready.");
			}catch (Exception e) {
					System.out.println("Server failed: " + e);
			}
		
	}
}