import java.util.Scanner;

/**
 * Implement a Connect 4 game
 *
 *
 * @version   $Id: Player.java,v 1.0 2015/09/20 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */


public class Player implements PlayerInterface 
{
	char GamePiece;
	String Name;
	Connect4FieldInterface field;
	
	Player(Connect4FieldInterface theField, String name, char gamePiece)
	{
		field = theField;
		Name = name;
		GamePiece = gamePiece;
	}
	

	@Override
	public char getGamePiece()
	{
		return GamePiece;
	}

	@Override
	public String getName() 
	{
		return Name;
	}

	@Override
	public int nextMove()
	{
		return askLetter();
	}

	int askLetter()
	{
		int res = -1;
		try
		{
			while(res < 0)
			{
				System.out.println("Enter the column where to place the piece : ");
				Scanner reader = new Scanner(System.in);
				res = reader.nextInt();
				//check if res is out of range
				if(res < 0 || res > 25)
				{
					System.out.println("Wrong input : column : " + res + " is out of range");
					res = -1;
				}
				System.in.reset();
				reader.close();
			}
		}
		catch(Exception ex)
		{
			if(res < 0)
				res = askLetter();
		}
		return res;
	}

}
