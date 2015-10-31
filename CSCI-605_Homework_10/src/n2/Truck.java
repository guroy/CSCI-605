package n2;

public class Truck extends Thread
{
	int id;
	int mass;
	Bridge road;
	static int count = 0;
	
	Truck(Bridge road)
	{
		//generate a random int between 100 and 100000
		mass = 100 + (int)(Math.random() * 100000);
		id = count++;
		this.road = road;
	}
	
	public void run()
	{
		synchronized(road)
		{
			if()
			System.out.println("Truck"+id+" is crossing the bridge");
			try
			{
				sleep(1000);
			}
			catch(InterruptedException e)
			{
				System.out.println("Truck"+id+" had a problem on the bridge");
			}
			System.out.println("Truck"+id+" have crossed the bridge");
		}
	}

}
