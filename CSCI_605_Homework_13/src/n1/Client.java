package n1;

import java.rmi.*;

public class Client {
	public static void main(String args[] ) {
		int numbGuess;
		boolean play = true;
		try {
			MyInterface obj = (MyInterface)Naming.lookup("MyImplementation");
			numbGuess = obj.askIntToGuess();
			while(play) {
				int res = obj.askInput();
				if (res > 0) {
					System.out.println("It's less");
				} else if (res < 0) {
					System.out.println("It's more");
				} else {
					System.out.println("You did it");
					play = false;
				}
				
			}
		} catch (Exception e) {
			System.out.println("HelloC exception: " +
					e.getMessage());
			e.printStackTrace();
		}
	}
}