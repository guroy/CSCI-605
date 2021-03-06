package exercise.n2;

/**
 * Re-design and re-implement HW 4.1, 4.2, and 4.3 using the the MVC paradigm.
 *
 *
 * @version   $Id: PlayerAI.java,v 1.0 2015/09/29 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public class PlayerAI implements PlayerInterface {
	char GamePiece;
	String Name;
	Connect4FieldModel field;
	
	PlayerAI(Connect4FieldModel theField, char gamePiece)
	{
		field = theField;
		Name = "AI";
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
	
	public int positionDrop(int column)
	{
		int res = 0;
		do
		{
			char buf;
			buf = field.board[res][column];
			if (buf != 'o') {
				break;
			}
			res++;
		} while (res < Connect4FieldModel.BOARD_HEIGHT);
		return res-1;
	}

	@Override
	public int nextMove()
	{
		int position;
		int res = -1;
		for(int i = 0; i < Connect4FieldModel.BOARD_WIDTH; i++)
		{
			if(field.checkIfPiecedCanBeDroppedIn(i))
			{
				position = positionDrop(i);
				// Test if the position is next to the left side of the board
				if(position == 0)
				{
					//test if the position is next to the top of the board
					if(i == 0)
					{
						if(((Connect4FieldModel)field).board[position][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i] == GamePiece)
						{
							res = i;
							break;
						}
					}
					//test if the position is next to the bottom of the board
					else if(i == Connect4FieldModel.BOARD_WIDTH-1)
					{
						if(((Connect4FieldModel)field).board[position+1][i] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}
					else
					{
						//other case
						if(((Connect4FieldModel)field).board[position][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}
				}
				//test if the position is next to the right of the board
				else if(position == Connect4FieldModel.BOARD_HEIGHT-1)
				{
					//test if the position is next to the top of the board
					if(i == 0)
					{
						if(((Connect4FieldModel)field).board[position-1][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}
					//test if the position is next to the bottom of the board
					else if(i == Connect4FieldModel.BOARD_WIDTH-1)
					{
						if(((Connect4FieldModel)field).board[position-1][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}
					else
					{
						//other position
						if(((Connect4FieldModel)field).board[position-1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position-1][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}

				}
				//all the other case 
				else
				{
					if(i == 0)
					{
						if(((Connect4FieldModel)field).board[position-1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i] == GamePiece)
						{
							res = i;
							break;
						}
					}
					else if(i == Connect4FieldModel.BOARD_WIDTH-1)
					{
						if(((Connect4FieldModel)field).board[position+1][i] == GamePiece ||
								((Connect4FieldModel)field).board[position-1][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}
					else
					{
						if(((Connect4FieldModel)field).board[position-1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i+1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i] == GamePiece ||
								((Connect4FieldModel)field).board[position-1][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position][i-1] == GamePiece ||
								((Connect4FieldModel)field).board[position+1][i-1] == GamePiece)
						{
							res = i;
							break;
						}
					}

				}

			} // end if
		}// end for
		//case no position with game piece next available so random move
		if(res == -1)
		{
			res = (int)(Math.random()*24);
		}
		return res;
	}
}

