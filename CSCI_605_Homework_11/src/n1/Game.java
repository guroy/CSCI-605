package n1;

public class Game {
	public static void main(String args[])
	{
		Object o = new Object();
		Player player1 = new Player("player 1", o);
		Player player2 = new Player("player 2", o);
		player1.start();
		player2.start();
		
		System.out.println("Welcome to the DUEL game.");
		System.out.println("Be faster than your ennemy or die.");
		System.out.println("When 'GO' appears on screen, ");
		System.out.println("each player needs to enter his key as fast as possible.");
		System.out.println("Player 1 KEY: L-SHIFT, Player 2 KEY: R-SHIFT.");
		System.out.println();
		System.out.println("Ready...");
		
		int randValue = 0;
		boolean display = true;
		while(display)
		{
			randValue = (int)(Math.random()*1000000000);
			if(randValue < 2)
			{
				display = false;
			}
		}
		System.out.println("G0!!");
		synchronized(o)
		{
			o.notifyAll();
		}
	}
}
