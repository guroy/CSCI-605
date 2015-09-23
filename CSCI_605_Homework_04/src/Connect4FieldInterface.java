/**
 * Implement a Connect 4 game
 *
 *
 * @version   $Id: Connect4FieldInterface.java,v 1.0 2015/09/20 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public interface Connect4FieldInterface 
{
	public boolean checkIfPiecedCanBeDroppedIn(int column);
	public void dropPieces(int column, char gamePiece);
	boolean didLastMoveWin();
	public boolean isItaDraw();
	public void init( PlayerInterface playerA, PlayerInterface playerB );
	public String toString();
	public void playTheGame();
}
