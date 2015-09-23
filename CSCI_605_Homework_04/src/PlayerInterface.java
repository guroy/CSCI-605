/**
 * Implement a Connect 4 game
 *
 *
 * @version   $Id: PlayerInterface.java,v 1.0 2015/09/20 $
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
