package n1;

import java.rmi.*;

public class Server {

	public static void main(String args[])
	{
		// System.setSecurityManager(new RMISecurityManager());

		try {
			MyInterface obj = new MyImplementation();
			Naming.rebind("//spiegel/IamAhelloImplementationObject", obj);
			System.out.println("HelloServer bound in registry");
		} catch (Exception e) {
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
