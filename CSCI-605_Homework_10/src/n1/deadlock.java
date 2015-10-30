package n1;

public class deadlock extends Thread
{
	String name;
	Object o1 ;
	Object o2 ;
	Object stop;
	
	deadlock(String name, Object o1, Object o2, Object stop)
	{
		this.name = name;
		this.o1 = o1;
		this.o2 = o2;
		this.stop = stop;
	}
	
	public void run()
	{
			synchronized(o1)
			{
				System.out.println("o1 is locked by " + name);
				try
				{
					synchronized(stop)
					{
						if(name == "0")
						{
							new deadlock("1",o2,o1,stop).start(); // create a new thread with the same parameters but invert o1 and o2
							System.out.println(name + " is waiting for stop");
							stop.wait();
							System.out.println(name + " exit stop.wait()");
						}//end if
						else
						{
							System.out.println(name + " notify stop");
							stop.notify();
						}// end else
					}//end synchronization on stop
					System.out.println(name + " is waiting for o2");
					synchronized(o2)
					{
						System.out.println(name + " get o2");
						System.out.println("I will not get there until someone use notify properly");
					}// end synchronization on o2
				}//end try
				catch(Exception e)
				{
					System.err.println("something go wrong in thread " + name);
				}
			}//end sync o1
	}//end run
	
	public static void main (String args []) 
	{
		Object o_1 = new Object();
		Object o_2 = new Object();
		Object stop = new Object();
		new deadlock("0", o_1, o_2, stop).start();
	}
}
