import java.rmi.Naming;

public class StartSerever {
	public static void main(String[] args) {
		try {
				//System.setSecurityManager(new RMISecurityManager());
			 	java.rmi.registry.LocateRegistry.createRegistry(1099);
			 	
				ScrabbleServerInt b=new ScrabbleServer();	
				Naming.rebind("rmi://10.13.81.164/myabc", b);
				System.out.println("[System] Chat Server is ready.");
			}catch (Exception e) {
					System.out.println("Chat Server failed: " + e);
			}
	}
}
