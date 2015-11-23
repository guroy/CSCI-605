package n1;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class MyImplementation extends UnicastRemoteObject implements MyInterface {
	public MyImplementation() throws RemoteException {
	}

	public String askInput() {
		Scanner keyboard = new Scanner(System.in);
		return keyboard.nextLine();
	}

	public int checkInput(int number) {
		// TODO Auto-generated method stub
		return number - askIntToGuess();
	}

	public int askIntToGuess() {
		int randValue = (int)(Math.random()*1000);
		return randValue;
	}
}
