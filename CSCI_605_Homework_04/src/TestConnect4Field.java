
public class TestConnect4Field 
{
	public Connect4Field aConnect4Field = new Connect4Field();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
	//public Player bPlayer = new Player(aConnect4Field, "B",'*');
	public PlayerAI bPlayer = new PlayerAI(aConnect4Field, '*');
	
	public void dropTest( int column ) 
	{
		System.out.println("Can it be dropped in " +
				   column + ": " 	   +
				   aConnect4Field.checkIfPiecedCanBeDroppedIn(column));
	}
	public void testIt() 
	{
		aConnect4Field = new Connect4Field();
		aPlayer = new Player(aConnect4Field, "A", '+');
		bPlayer = new PlayerAI(aConnect4Field, '*');
//		aConnect4((Connect4Field)field).board[8][15] = 'x';
//		System.out.println(aConnect4Field);
//		dropTest(-1);
//		dropTest(0);
//		dropTest(1);
//		aConnect4Field.dropPieces(1, '+');
//		System.out.println(aConnect4Field);
//		aConnect4Field.dropPieces(1, '*');
//		System.out.println(aConnect4Field);
//		aConnect4Field.didLastMoveWin();
//		System.out.println(aConnect4Field.isItaDraw());
		aConnect4Field.init(aPlayer, bPlayer);
		aConnect4Field.playTheGame();
	}
	public static void main( String[] args ) 
	{
		new TestConnect4Field().testIt();
	}
}
