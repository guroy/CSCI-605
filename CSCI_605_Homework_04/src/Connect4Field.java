
public class Connect4Field implements Connect4FieldInterface {
	static final int BOARD_WIDTH = 25;
	static final int BOARD_HEIGHT = 9;
	
	char[][] board;
	PlayerInterface player1;
	PlayerInterface player2;
	boolean isPlayer1Turn;
	boolean endgame;
	Position lastMovePosition;
	
	// store the position of a piece in the board
	class Position { 
		int X;
		int Y;
	}
	
	public Connect4Field() {
		super();
		endgame = false;
		
		board = new char[BOARD_WIDTH][BOARD_HEIGHT];
		int thin = 0;
		
		// create game board
		for (int j = 0; j < BOARD_HEIGHT; j++)
		{
			for (int i = 0; i < BOARD_WIDTH ; i++)
			{
				if(i>=thin && i<BOARD_WIDTH-thin)
					board[i][j] = 'o';
				else
					board[i][j] = ' ';					
			}
			
			thin++;
		}
	}

	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) 
	{
		return board[0][column] == 'o';
	}

	@Override
	public void dropPieces(int column, char gamePiece) 
	{
		int i = 0;
		char buf;
		if(column >= 0 && column < BOARD_WIDTH)
		{
			buf = board[i][column];
			while(buf == 'o')
			{
				buf = board[i][column];
				i++;
			}
			board[i][column] = gamePiece;
			lastMovePosition.X = i;
			lastMovePosition.Y = column;
		}
		else
		{
			System.out.println("dropPieces wrong input : column : (" + column + ") is out of range");
		}
	}

	@Override
	public boolean didLastMoveWin() 
	{
		return 0 == lastMovePosition.X;
	}

	@Override
	public boolean isItaDraw() 
	{
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			if(checkIfPiecedCanBeDroppedIn(i))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB)
	{		
		// modify attributes in the instance of Connect4Field
		player1 = playerA;
		player2 = playerB;
		isPlayer1Turn = true;
		lastMovePosition = new Position();
	}

	@Override
	public String toString() {
		String displayBoard = "";
		for (int j = 0; j < BOARD_HEIGHT; j++)
		{
			for (int i = 0; i < BOARD_WIDTH ; i++)
			{
				displayBoard += board[i][j];					
			}
			
			displayBoard += '\n';
		}
		return displayBoard;
	}

	@Override
	public void playTheGame()
	{
		int indexPlay;
		while(!endgame)
		{
			toString();
			if(isItaDraw())
			{
				endgame = true;
			}
			else
			{
				if(isPlayer1Turn)
				{
					System.out.println("Player 1 turn,");
					indexPlay = player1.nextMove();
					if(checkIfPiecedCanBeDroppedIn(indexPlay))
					{
						dropPieces(indexPlay,player1.getGamePiece());
						if(didLastMoveWin())
						{
							endgame = true;
							System.out.println("Player 1 win !");
							break;
						}
						else
						{
							isPlayer1Turn = false;
						}
					}
					else
					{
						System.out.println("You can't place a piece in this column ! play again");
						continue;
					}
				}
				else
				{
					System.out.println("Player 1 turn,");
					indexPlay = player2.nextMove();
					if(checkIfPiecedCanBeDroppedIn(indexPlay))
					{
						dropPieces(indexPlay,player2.getGamePiece());
						if(didLastMoveWin())
						{
							endgame = true;
							System.out.println("Player 2 win !");
							break;
						}
						else
						{
							isPlayer1Turn = true;
						}
					}
					else
					{
						System.out.println("You can't place a piece in this column ! play again");
						continue;
					}
				}
			}
			
		}
	}

}
