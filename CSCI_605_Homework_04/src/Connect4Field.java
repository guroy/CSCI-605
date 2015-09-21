
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
		
		thePlayers = new PlayerInterface[2];

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

		if (board[y0][x0] == piece || board[y1][x1] == piece) {
			int x_min = Math.max(0, lastMovePosition.X - 3),
				y_min = Math.max(0, lastMovePosition.Y - 3);
					
			int countPieces = 0;
			for (int i = 0; i < 7; i++) {
				if (y_min + i >= 0 && y_min + i < BOARD_HEIGHT &&
					x_min + i >= 0 && x_min + i < BOARD_WIDTH) {
					if (board[y_min + i][x_min + i] == piece) {
						countPieces++;
						// if we already have our 4 pieces, we can break
						if (countPieces == 4) {
							lastMoveWin = true;
							break;
						}
					} else {
						countPieces = 0;
					}
				}
			}
		} else if (board[y1][x0] == piece || board[y0][x1] == piece) {
			int x_min = Math.max(0, lastMovePosition.X - 3),
				y_max = Math.min(lastMovePosition.Y + 3, BOARD_HEIGHT - 1);
			
			int countPieces = 0;
			for (int i = 0; i < 7; i++) {
				if (y_max - i >= 0 && y_max - i < BOARD_HEIGHT &&
					x_min + i >= 0 && x_min + i < BOARD_WIDTH) {
					if (board[y_max - i][x_min + i] == piece) {
						countPieces++;
						// if we already have our 4 pieces, we can break
						if (countPieces == 4) {
							lastMoveWin = true;
							break;
						}
					} else {
						countPieces = 0;
					}
				}
			}
			
		} else if (board[lastMovePosition.Y][x0] == piece || board[lastMovePosition.Y][x1] == piece) { // horizontal lines
			int x_min = Math.max(0, lastMovePosition.X - 3),
				x_max = Math.min(lastMovePosition.X + 3, BOARD_WIDTH - 1);
			
			int countPieces = 0;
			
			for (int i = x_min; i <= x_max; i++) {
				if (board[lastMovePosition.Y][i] == piece) {
					countPieces++;
					// if we already have our 4 pieces, we can break
					if (countPieces == 4) {
						lastMoveWin = true;
						break;
					}
				} else {
					countPieces = 0;
				}
			}
		} else if (board[y1][lastMovePosition.X] == piece) { // test vertical lines
			int y_min = lastMovePosition.Y,
				y_max = Math.min(lastMovePosition.Y + 3, BOARD_HEIGHT - 1);
			
			int countPieces = 0;
			
			for (int i = y_min; i <= y_max; i++) {
				if (board[i][lastMovePosition.X] == piece) {
					countPieces++;
					// if we already have our 4 pieces, we can break
					if (countPieces == 4) {
						lastMoveWin = true;
						break;
					}
				} else {
					countPieces = 0;
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
			for ( int index = 0; index < 2; index ++ )     
			{
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
