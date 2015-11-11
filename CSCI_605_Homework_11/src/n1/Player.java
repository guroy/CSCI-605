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
