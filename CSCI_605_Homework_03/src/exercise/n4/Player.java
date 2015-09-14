package exercise.n4;

/**
 * Player class, contain the parameters of a Player for the hangman game
 *
 *
 * @version   $Id: Player.java,v 1.0 2015/09/01 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 * Revisions: 
 *
 * 	Initial revision
 *
 */

public class Player 
{
	String name;
	int score;
	int nbtry;

	//Constructor
	Player(String player, int initscore)
	{
		name = player;
		score = initscore;
		nbtry = 8;
	}

	//Update Score
	void updateScore(int newScore)
	{
		score = newScore;
	}

	void updateTry(int newTry)
	{
		nbtry = newTry;
	}

}
