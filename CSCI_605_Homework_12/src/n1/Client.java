package n1;

import java.net.*;
import java.util.Scanner;

public class Client 
{
	String hostname = "yps";
	int port = 53;
	
	String name;
	int toGuess;
	private Scanner keyboard;

	public Client() 
	{
		
	}

	/**
	 * Parse the commandlind arguments and sets variables.
	 */
	public void handleArgs(String args[]) {
		boolean exit = true;
		while (exit) {
			if (args[0].equals(name))
			{
				System.out.println("Enter a number ");
				keyboard = new Scanner(System.in);
				System.out.println(name + " enter number : ");
				String input = keyboard.nextLine();
				if(Integer.parseInt(input) < toGuess)
				{
					System.out.println("+");
				}
				else if(Integer.parseInt(input) > toGuess)
				{
					System.out.println("-");
				}
				else
				{
					System.out.println(name + " win the game");
					exit = false;
				}
			} else if (args[0].equals("game_over")) {
				exit = false;
			}
		}
	}

	public void doTheJob()	{
		try {
			byte buf[] = new byte[64];
			InetAddress aInetAddress = InetAddress.getByName(hostname);
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(buf,
					buf.length, aInetAddress, port);
			socket.send(packet);

			System.out.println("host: " +  hostname );
			System.out.println("port: " +  port );
			System.out.println("after creation");
			socket.receive(dp);
			System.out.println("received: -" +
					new String(dp.getData() ) + "-"  );
			socket.close();
		} catch (Exception e) {
			System.out.println (e);
			e.printStackTrace();
		}
	}
	
	public static void main (String args[]) {
		
	}
}
