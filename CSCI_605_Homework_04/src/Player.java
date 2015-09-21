import java.util.Scanner;

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
		int res = 0;
		try
		{
			System.out.println("Enter the column where to place the piece : ");
			Scanner reader = new Scanner(System.in);
			res = reader.nextInt();
			System.in.reset();
			reader.close();
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		
		}
		return res;
	}

}
