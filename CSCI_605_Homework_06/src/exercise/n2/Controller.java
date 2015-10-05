package exercise.n2;

public class Controller 
{
	Connect4FieldModel model;
	boolean updateView;
	boolean updateModel;
	
	Controller()
	{
		model = new Connect4FieldModel();
		updateView = false;
		updateModel = false;
	}
	
	void updateGame()
	{
		if(updateModel)
		{
			
			updateModel = false;
		}
	}
}
