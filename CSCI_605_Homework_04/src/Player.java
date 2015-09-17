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
		System.out.println("Enter the column where to place the piece : ");
		Scanner reader = new Scanner(System.in);
		int c = reader.next().charAt(0);
		reader.close();
		return c;
	}

}
