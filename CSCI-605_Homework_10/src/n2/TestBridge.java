package n2;

public class TestBridge 
{
	public static void main(String args[])
	{
		Bridge bridge = new Bridge();
		try
		{
			int newTruck = 0;
			while(true)
			{
				newTruck = (int)(Math.random()*4000000);
				if(newTruck < 2)
				{
					bridge.addTruckCrossing(new Truck(bridge));
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occured during the test");
			System.out.println(e.getMessage());
		}
	}
}
