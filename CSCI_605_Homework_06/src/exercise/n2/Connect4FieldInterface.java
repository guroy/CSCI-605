package exercise.n2;

/**
 * Re-design and re-implement HW 4.1, 4.2, and 4.3 using the the MVC paradigm.
 *
 *
 * @version   $Id: Connect4FieldInterface.java,v 1.0 2015/09/29 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public interface Connect4FieldInterface 
{
	public boolean checkIfPiecedCanBeDroppedIn(int column);
	boolean didLastMoveWin();
	public boolean isItaDraw();
	public void init( PlayerInterface playerA, PlayerInterface playerB );
	public String toString();
}
