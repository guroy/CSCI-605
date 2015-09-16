
public class Connect4Field implements Connect4FieldInterface {
	char[][] board;
	Player player1, player2;
	boolean isPlayer1Turn;
	
	public Connect4Field(char[][] board, Player player1, Player player2, boolean isPlayer1Turn) {
		super();
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.isPlayer1Turn = isPlayer1Turn;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void playTheGame() {
		// TODO Auto-generated method stub

	}

}
