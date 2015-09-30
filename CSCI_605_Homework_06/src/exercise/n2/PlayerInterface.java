package exercise.n2;

/**
 * Re-design and re-implement HW 4.1, 4.2, and 4.3 using the the MVC paradigm.
 *
 *
 * @version   $Id: PlayerInterface.java,v 1.0 2015/09/29 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public interface PlayerInterface 
{
// this is how your constructor has to be
// Player(Connect4FieldInterface theField, String name, char gamePiece)
	
	public char getGamePiece();
	public String getName();
	public int  nextMove();
}
