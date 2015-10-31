package n2;

import java.util.Vector;

public class Bridge 
{
	private Vector<Truck> waitingTrucks;
	private Vector<Truck> crossingTrucks;
	private volatile int mass;
	private volatile int nbTruck;
	public final static int MAX_MASS = 200000;
	public final static int MAX_TRUCKS = 4;
	
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
	//addTruckWait, add truck to  the waiting list
	public void addTruckWait(Truck trck)
	{
		waitingTrucks.add(trck);
	}
	
	//addTruckCrossing, add the truck trck to the crossing list
	public void addTruckCrossing(Truck trck)
	{
		if(addMass(trck.mass))
		{
			if(incNbTruck())
			{
				crossingTrucks.add(trck);
				waitingTrucks.remove(trck);
			}
			else
			{
				addMass(-trck.mass);
			}
		}
		else
		{
			addTruckWait(trck);
		}
	}
	
	//removeTruckCrossing, remove the truck trck from the crossing road
	public void removeTruckCrossing(Truck trck)
	{
		crossingTrucks.remove(trck);
		addMass(-trck.mass);
		decNbTruck();
	}
}
