package n1;

public class Game {
	public static void main(String args[])
	{
		Object o = new Object();
		boolean play= true;
		int randValue = (int)(Math.random()*1000);
		Player player1 = new Player("player 1", o, randValue, play);
		Player player2 = new Player("player 2", o, randValue, play);
		player1.start();
		player2.start();
		
		System.out.println("You have to find the right number,");
		System.out.println("When it's your turn, enter a number between 0 and 1000");
		System.out.println("if the computer give back + the number to find is higher and if it's - the number is lower");
		System.out.println("the first player to find win");
		synchronized(o)
		{
			o.notifyAll();
		}
	}
}
