package ProgressReview;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ScrabbleClient {
	private ScrabbleClient() {}
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(null);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
