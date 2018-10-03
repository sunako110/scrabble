import java.rmi.Naming;

public class StartServer {
	public static void main(String[] args) {
		try {
				//System.setSecurityManager(new RMISecurityManager());
			 	java.rmi.registry.LocateRegistry.createRegistry(1099);
			 	
				ScrabbleServerInt b=new ScrabbleServer();	
				Naming.rebind("rmi://10.13.90.195/myabc", b);
				System.out.println("[System] Server is ready.");
			}catch (Exception e) {
					System.out.println("Server failed: " + e);
			}
		
	}
}