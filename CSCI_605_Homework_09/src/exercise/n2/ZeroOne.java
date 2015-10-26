package exercise.n2;

/**
 * Convert into a multithreaded version.
 *
 *
 * @version   $Id: ZeroOne.java,v 1.0 2015/10/19 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 *
 */

public class ZeroOne extends Thread	{
	private int info;
	static Object o = new Object();
	static boolean oneIsRunning = false;
	private static volatile int index = 0;
					     
	public ZeroOne (int info) 
	{
		this.info = info;
	}
	
	public void run ()
	{
		while ( true )	
		{
			synchronized (o)
			{
				o.notify();
				if(info == index)
				{
					System.out.print(info+", ");

					index++;
				}
				if(index == 100)
				{
					index = 0;
				}
				try 
				{
					o.wait();
				}
				catch ( Exception e ) 
				{	
				}
			}
		}
	}
	public static void main (String args []) {
		for(int i = 0; i < 100; i++) 
		{
			new ZeroOne(i).start();
		}
	}
}
