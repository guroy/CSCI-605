package n1;

import java.util.Scanner;

public class Player extends Thread
{
	String name;
	int score;
	Object o; 
	int toGuess;
	public static boolean exit;

	Player(String name, Object o, int toGuess, boolean play)
	{
		this.name = name;
		score = 0;
		this.o = o;
		this.toGuess = toGuess;
		exit= play;
	}

	public void run()
	{
		System.out.println("You have to find the right number,");
		System.out.println("When it's your turn, enter a number between 0 and 1000");
		System.out.println("if the computer give back + the number to find is higher and if it's - the number is lower");
		System.out.println("the first player to find win");
		
		synchronized(o)
		{
			try 
			{
				o.wait();
			} 
			catch (InterruptedException e) 
			{
			}
			System.out.println(name+ " turn");
			Scanner keyboard = new Scanner(System.in);
			while(exit)
			{
				o.notify();
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
				try {
					o.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			o.notify();
		}
	}
}
