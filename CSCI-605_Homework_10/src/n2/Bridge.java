package n2;

import java.util.Vector;

public class Bridge 
{
	private volatile int mass;
	private volatile int nbTruck;
	public final static int MAX_MASS = 200000;
	public final static int MAX_TRUCKS = 4;
	public static int nbWaitingTruck = 0;
	
	Bridge()
	{
		mass = 0;
		nbTruck = 0;
		
	}
	
	//getMass, return the mass on the bridge
	public int getMass()
	{
		return mass;
	}
	//addMass, add mass to the mass total already on the bridge
	public boolean addMass(int mass)
	{
		if(this.mass + mass <= MAX_MASS)
		{
			this.mass += mass;
			return true;
		}
		else
		{
			System.out.println("The truck can't access the bridge => too heavy");
			return false;
		}
	}
	//getnbTruck, return the number of truck(s) on the bridge currently
	public int getnbTruck()
	{
		return nbTruck;
	}
	//incNbTruck, increment the number of trucks on the bridge if possible return true, else return false
	public boolean incNbTruck()
	{
		if(nbTruck < MAX_TRUCKS)
		{
			nbTruck++;
			return true;
		}
		else
		{
			System.out.println("There is too many truck on the bridge");
			return false;
		}
			
	}
	//decNbTruck, decrement the number of trucks on the bridge if possible return true, else return false
	public boolean decNbTruck()
	{
		if(nbTruck >= 1)
		{
			nbTruck--;
			return true;
		}
		else
		{
			System.out.println("There is no truck to remove from the bridge");
			return false;
		}
	}
	
	//addTruckCrossing, add the truck trck to the crossing list
	public void addTruckCrossing(Truck trck)
	{
		incNbTruck();
		addMass(trck.mass);
		trck.start();
	}
	
	//removeTruckCrossing, remove the truck trck from the crossing road
	public void removeTruckCrossing(Truck trck)
	{
		try
		{
//			crossingTrucks.remove(trck);
			addMass(-trck.mass);
			decNbTruck();
//			waitingTrucks.remove(trck);
			System.err.println("Truck remove, nb truck on the bridge: " + nbTruck+"\n");
		}
		catch(Exception e)
		{
			System.out.println("Error when deleting truck"+trck.id+" from the crossing list");
			System.out.println(e.getMessage());
		}
	}
}
