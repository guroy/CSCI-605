package exercise.n4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Hangman game, guess the word that is display on the screen by proposing letters.
 *
 *
 * @version   $Id: Hangman.java,v 1.0 2015/09/01 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 * Revisions: 
 *
 * 	Initial revision
 *
 */

public class Hangman 
{
	final int NBTRY = 8;
	int nbplayers;
	int currentPlayer;
	int nbGoodLetter;
	Vector<Player> players;
	String file;
	String toguess;
	char todisplay[];

	//Constructor
	Hangman(String list[])
	{
		file = list[0];
		nbplayers = list.length -1;
		currentPlayer = 0; 
		nbGoodLetter = 0;
		players = new Vector<Player>();
		initPlayers(list);
		toguess = getWord(file);
		todisplay = new char[toguess.length()];
	}

	void initGame()
	{
		nbGoodLetter = 0;
		players.get(currentPlayer).nbtry = 8;
		initToDisplay();
		updateGame();
	}

	//Initialize the list of player
	void initPlayers(String tabPlayer[])
	{
		for(int i = 1; i < nbplayers+1; i++)
		{
			players.add(new Player(tabPlayer[i],0));
			System.out.println("Player " + i + " : " + players.get(i-1).name);
		}
	}

	//get a random line
	int genRandLine()
	{
		int res;
		// get a number between 0 and 109851 that is the number of word in my file
		res = (int) Math.floor(Math.random()*109851);
		return res;
	}

	//Read the txt file to get a word
	public String getWord(String file)
	{
		String res = null;
		try
		{	
			String filePath = new File("").getAbsolutePath()+"\\src\\" + file;
			BufferedReader buf = new BufferedReader(new FileReader(filePath));
			int count =0;
			int randLine = genRandLine();
			while(count != randLine)
			{
				res = buf.readLine();
				count++;
			}
			
			buf.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		return res;
	}

	void initToDisplay()
	{
		for(int i = 0; i < toguess.length(); i++)
		{
			todisplay[i] ='_';
		}
	}

	void displayToDisplay()
	{
		for(int i = 0; i < todisplay.length; i++)
		{
			System.out.print(todisplay[i] + " ");
		}
		System.out.println("");
	}

	void askLetter()
	{
		System.out.println("Enter a letter");
		Scanner reader = new Scanner(System.in);
		char c = reader.next().charAt(0);
		checkLetter(c);
		reader.close();
	}

	void checkLetter(char a)
	{
		boolean check = false;
		for(int i = 0; i < toguess.length(); i++)
		{
			if(toguess.charAt(i) == a)
			{
				todisplay[i] = a;
				check = true;
				nbGoodLetter++;
			}
		}

		if(!check)
		{
			players.get(currentPlayer).nbtry--;
		}
	}

	void updateGame()
	{
		System.out.println("Current Player : " + players.get(currentPlayer).name + ", Numbers of try : " + players.get(currentPlayer).nbtry);
		displayToDisplay();

	}

	void changePlayer()
	{
		if(currentPlayer == 2)
		{
			endGame();
		}
		else
		{
			currentPlayer++;
			initGame();
		}
	}

	void updateScore()
	{
		players.get(currentPlayer).score = players.get(currentPlayer).nbtry * 5;
	}

	boolean endGame()
	{
		System.out.println("End of the game, score : ");
		displayScore();

		//THE REPLAY PART OF THE ALGORITHM DOESNT WORK SO WE DECIDE TO STOP PROPOSE THIS OPTION
		//System.out.println("Would you like to replay ? (O/N)");
		//boolean quit = true;
		//boolean replay = false;
		//while(quit)
		//{
		//	Scanner reader = new Scanner(System.in);
		//	char c = reader.next().charAt(0);
		//	if(c == 'o')
		//	{
		//		quit = false;
		//		replay = true;
		//	}
		//	else if (c == 'n')
		//	{
		//		quit = false;
		//	}
		//	else
		//	{
		//		System.out.println("Please enter o to continue or n to stop");
		//	}
		//}

		return false;
	}

	void displayScore()
	{
		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i).name  + " " + players.get(i).score);
		}
	}

	void play()
	{
		boolean play = true;
		boolean game = true;
		while(game)
		{
			initGame();
			while(play)
			{
				if(nbGoodLetter == todisplay.length)
				{
					updateGame();
					updateScore();
					nbGoodLetter = 0;
					System.out.println("Yeeaaay");
					changePlayer();
				}
				else if(players.get(currentPlayer).nbtry > 0 )
				{
					askLetter();
					updateGame();
				}
				else if(currentPlayer < 2)
				{
					changePlayer();
				}
				else
				{
					play = false;
				}
			}
			game = endGame();
		}
	}

	public static void main(String args[])
	{
		Hangman game = new Hangman(args);
		game.play();
	}
}

