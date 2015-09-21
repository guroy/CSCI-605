
public class Connect4Field implements Connect4FieldInterface {
	static final int BOARD_WIDTH = 25;
	static final int BOARD_HEIGHT = 9;

	char[][] board;
	PlayerInterface thePlayers[];
	Position lastMovePosition;

	// store the position of a piece in the board
	class Position { 
		int X;
		int Y;
	}

	public Connect4Field() {
		super();

		board = new char[BOARD_HEIGHT][BOARD_WIDTH];
		int thin = 0;

		// create game board
		for (int j = 0; j < BOARD_HEIGHT; j++)
		{
			for (int i = 0; i < BOARD_WIDTH ; i++)
			{
				if(i>=thin && i<BOARD_WIDTH-thin)
					board[j][i] = 'o';
				else
					board[j][i] = ' ';					
			}

			thin++;
		}
		
		thePlayers = new Player[2];

		lastMovePosition = new Position();
	}

	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) 
	{
		return (column >= 0 && column < BOARD_WIDTH) ? board[0][column] == 'o' : false;
	}

	@Override
	public void dropPieces(int column, char gamePiece) 
	{
		int i = 0;
		char buf;
		if(checkIfPiecedCanBeDroppedIn(column))
		{					
			do
			{
				buf = board[i][column];
				if (buf != 'o') {
					break;
				}
				i++;
			} while (i < BOARD_HEIGHT);

			board[i-1][column] = gamePiece;
			lastMovePosition.X = column;
			lastMovePosition.Y = i-1;
		}
		else
		{
			System.out.println("dropPieces wrong input : column : (" + column + ") is out of range or full");
		}
	}

	@Override
	public boolean didLastMoveWin() {
		boolean lastMoveWin = false;

		char piece = board[lastMovePosition.Y][lastMovePosition.X];

		int y0 = Math.max(0, lastMovePosition.Y - 1),
			y1 = Math.min(lastMovePosition.Y + 1, BOARD_HEIGHT - 1),
			x0 = Math.max(0, lastMovePosition.X - 1),
			x1 = Math.min(lastMovePosition.X + 1, BOARD_WIDTH - 1);

		here:

			for (int j = y0; j <= y1; j++) {
				for (int i = x0; i <= x1; i++) {
					if (i == lastMovePosition.X && j == lastMovePosition.Y || board[j][i] != piece) {
						continue; // we already know what is going on for the lastMove and only care about same values
					} else {
						int direction = (i - lastMovePosition.X != 0) ? j - lastMovePosition.Y / i - lastMovePosition.X : Integer.MAX_VALUE;

						// use to count if there are at least 4 pieces in one row
						int countPieces = 0;

						// we want to try every value 3 position before the lastMovePosition and 3 position after
						// so as not to miss any case (configuration:  oooxooo)
						int minX = Math.max(0, lastMovePosition.X - 3),
							maxX = Math.min(lastMovePosition.X + 3, BOARD_WIDTH),
							minY = Math.max(0, lastMovePosition.Y - 3),
							maxY = Math.min(lastMovePosition.X + 3, BOARD_HEIGHT);
						// besides we have to handle the diagonals
						int minXY = Math.max(minX - lastMovePosition.X, minY - lastMovePosition.Y),
							maxXY = Math.min(maxX - lastMovePosition.X, maxY - lastMovePosition.Y);

						switch (direction) { // we check if there is 4 pieces in the same direction
						case -1:
							for (int elt = minXY; elt < maxXY; elt++) {
								if (board[lastMovePosition.Y - elt][lastMovePosition.X + elt] == piece) {
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
								if (board[lastMovePosition.Y][elt] == piece) {
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
								if (board[lastMovePosition.Y + elt][lastMovePosition.X + elt] == piece) {
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
								if (board[elt][lastMovePosition.X] == piece) {
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
		thePlayers[0] = playerA;
		thePlayers[1] = playerB;
	}

	@Override
	public String toString() {
		String displayBoard = "";
		for (int j = 0; j < BOARD_HEIGHT; j++)
		{
			for (int i = 0; i < BOARD_WIDTH ; i++)
			{
				displayBoard += board[j][i];					
			}

			displayBoard += '\n';
		}
		return displayBoard;
	}

	@Override
	public void playTheGame()
	{
		int column;
//		System.out.println(this);
		boolean gameIsOver = false;

		do {
			for ( int index = 0; index < 2; index ++ )      {
				System.out.println(this);
				if ( isItaDraw() )      {
					System.out.println("Draw");
					gameIsOver = true;

				} else {
					column = thePlayers[index].nextMove();
					dropPieces(column, thePlayers[index].getGamePiece() );
					
					if ( didLastMoveWin() ) {
						gameIsOver = true;
						System.out.println("The winner is: " + thePlayers[index].getName());
					}
				}
			}

		}  while ( ! gameIsOver  );

	}

}
