
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
			boolean lastMoveWin = false;
		
		char piece = board[lastMovePosition.X][lastMovePosition.Y];
		
		int y0 = Math.max(0, lastMovePosition.Y - 1),
			y1 = Math.min(lastMovePosition.Y + 1, BOARD_HEIGHT - 1),
			x0 = Math.max(0, lastMovePosition.X - 1),
			x1 = Math.min(lastMovePosition.X + 1, BOARD_WIDTH - 1);
		
		here:
		
		for (int j = y0; j <= y1; j++) {
			for (int i = x0; i <= x1; i++) {
				if (i == lastMovePosition.X && j == lastMovePosition.Y || board[i][j] != piece) {
					break; // we already know what is going on for the lastMove and only care about same values
				} else {
					int direction = (i - lastMovePosition.X != 0) ? j - lastMovePosition.Y / i - lastMovePosition.X : Integer.MAX_VALUE;
					
					// use to count if there are at least 4 pieces in a row
					int countPieces = 0;
					
					// we want to try every value 3 position before the lastMovePosition and 3 position after
					// so as not to miss any case (configuration:  oooxooo)
					int minX = Math.max(0, lastMovePosition.X - 3),
						maxX = Math.min(lastMovePosition.X + 3, BOARD_WIDTH -1),
						minY = Math.max(0, lastMovePosition.Y - 3),
						maxY = Math.min(lastMovePosition.X - 3, BOARD_HEIGHT -1);
					// besides we have to handle the diagonals
					int minXY = Math.max(minX - lastMovePosition.X, minY - lastMovePosition.Y),
						maxXY = Math.min(maxX - lastMovePosition.X, maxY - lastMovePosition.Y);
					
					switch (direction) { // we check if there is 4 pieces in the same direction
					case -1:
						for (int elt = minXY; elt < maxXY; elt++) {
							 if (board[lastMovePosition.X + elt][lastMovePosition.Y - elt] == piece) {
								 countPieces++;
								 // if we already have our 4 pieces, we can break
								 if (countPieces == 4) {
									 lastMoveWin = true;
									 break here;
								 }
							 } else {
								 countPieces = 0;
							 }
						}
						break;
					case 0:
						for (int elt = minX; elt < maxX; elt++) {
							 if (board[elt][lastMovePosition.Y] == piece) {
								 countPieces++;
								 // if we already have our 4 pieces, we can break
								 if (countPieces == 4) {
									 lastMoveWin = true;
									 break here;
								 }
							 } else {
								 countPieces = 0;
							 }
						}
						break;
					case 1:
						for (int elt = minXY; elt < maxXY; elt++) {
							 if (board[lastMovePosition.X + elt][lastMovePosition.Y + elt] == piece) {
								 countPieces++;
								 // if we already have our 4 pieces, we can break
								 if (countPieces == 4) {
									 lastMoveWin = true;
									 break here;
								 }
							 } else {
								 countPieces = 0;
							 }
						}
						break;
					default: // when we have MAX_VALUE
						for (int elt = minY; elt < maxY; elt++) {
							 if (board[lastMovePosition.X][elt] == piece) {
								 countPieces++;
								 // if we already have our 4 pieces, we can break
								 if (countPieces == 4) {
									 break;
								 }
							 } else {
								 countPieces = 0;
							 }
						}
						break;
					}
				}
			}
		}
		
		return lastMoveWin;
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
