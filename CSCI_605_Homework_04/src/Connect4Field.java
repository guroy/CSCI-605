import java.util.Arrays;

public class Connect4Field implements Connect4FieldInterface {
	static final int BOARD_WIDTH = 25;
	static final int BOARD_HEIGHT = 9;
	
	char[][] board;
	PlayerInterface player1;
	PlayerInterface player2;
	boolean isPlayer1Turn;
	
	public Connect4Field() {
		super();
	}

	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dropPieces(int column, char gamePiece) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean didLastMoveWin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItaDraw() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		char[][] gameBoard = new char[BOARD_WIDTH][BOARD_HEIGHT];
		int thin = 0;
		
		// create game board
		for (int j = 0; j < BOARD_HEIGHT; j++)
		{
			for (int i = 0; i < BOARD_WIDTH ; i++)
			{
				if(i>=thin && i<BOARD_WIDTH-thin)
					gameBoard[i][j] = 'o';
				else
					gameBoard[i][j] = ' ';					
			}
			
			thin++;
		}
		
		// modify attributes in the instance of Connect4Field
		board = gameBoard;
		player1 = playerA;
		player2 = playerB;
		isPlayer1Turn = true;
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
	public void playTheGame() {
		// TODO Auto-generated method stub

	}

}