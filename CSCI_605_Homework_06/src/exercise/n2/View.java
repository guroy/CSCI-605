package exercise.n2;

public class View 
{
	Controller control;
	
	public View()
	{
		control = new Controller();
	}
	
	void displayBoard()
	{
		System.out.println(control.model);
	}
	
	void displayEndGame()
	{
		if(control.model.indexWinner != -1)
			System.out.println("The winner is: " + control.model.thePlayers[control.model.indexWinner].getName());
		else
			System.out.println("Draw!");
	}
	
	public void dropPieces(int column, char gamePiece) 
	{
		int i = 0;
		char buf;
		if(control.model.checkIfPiecedCanBeDroppedIn(column))
		{					
			do
			{
				buf = control.model.board[i][column];
				if (buf != 'o') {
					break;
				}
				i++;
			} while (i < control.model.BOARD_HEIGHT);

			control.model.board[i-1][column] = gamePiece;
			control.model.lastMovePosition.X = column;
			control.model.lastMovePosition.Y = i-1;
		}
		else
		{
			System.out.println("dropPieces wrong input : column : (" + column + ") is out of range or full");
			
		}
		control.updateModel = true;
	}
}
