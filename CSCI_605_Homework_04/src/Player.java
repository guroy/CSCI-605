
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
		return 0;
	}

}
