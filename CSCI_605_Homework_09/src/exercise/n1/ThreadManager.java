package exercise.n1;

public class ThreadManager 
{
	int x, y;
	int xMax, yMax;
	int nbThread;
	
	ThreadManager(int xM, int yM)
	{
		xMax = xM;
		yMax = yM;
		x = 0;
		y = 0;
		nbThread =  0;
	}
	
	void newLine()
	{
		if(x == xMax - 1)
			{
				y++;
				x = 0;
			}
		else
			x++;
	}
	
	boolean isFinished()
	{
		return x == (xMax - 1) && y == (yMax - 1);
	}
}
