package exercise.n2;

/**
 * Re-design and re-implement HW 4.1, 4.2, and 4.3 using the the MVC paradigm.
 *
 *
 * @version   $Id: TestConnect4Field.java,v 1.0 2015/09/29 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */


public class TestConnect4Field 
{
	public View observer= new View();
	public Connect4FieldModel aConnect4Field = new Connect4FieldModel();
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
		//aConnect4Field = new Connect4FieldModel();
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

		int column;
		boolean gameIsOver = false;

		do {
			for ( int index = 0; index < 2; index ++ )     
			{
				observer.displayBoard();
				System.out.println(this);
				if ( aConnect4Field.isItaDraw() )      {
					aConnect4Field.indexWinner = -1;
					gameIsOver = true;

				} else {
					column = aConnect4Field.thePlayers[index].nextMove();
					observer.dropPieces(column, aConnect4Field.thePlayers[index].getGamePiece() );
					
					if ( aConnect4Field.didLastMoveWin() ) {
						gameIsOver = true;
						aConnect4Field.indexWinner = index;
					}
				}
			}

		}  while ( ! gameIsOver  );
}
	
	
	public static void main( String[] args)
	{
		new TestConnect4Field().testIt();
	}
}