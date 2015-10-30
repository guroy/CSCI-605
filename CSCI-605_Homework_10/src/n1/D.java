package n1;

//File D.java
/*
* This code will always end up in a dead lock.
*/
public class D extends Thread	
{
	private String info;
	Object o_1;
	Object o_2;
	Object stop;

	public D (String info, Object o_1, Object o_2, Object stop) 
	{
		this.info    	= info;
		this.o_1    	= o_1;
		this.o_2    	= o_2;
		this.stop    	= stop;
	}
	public void run () 
	{
		synchronized ( o_1 ) 
		{
			System.out.println(info);
			try {
				synchronized ( stop ) 
				{
					if ( info == "0")	
					{
						new D("1", o_2, o_1, stop).start(); // order of args
						stop.wait();
					} else
						synchronized ( stop ) 
						{
							stop.notify();
						}
				}
				synchronized ( o_2 ) {
					System.out.println("I will not get there");
				}
			} catch ( Exception e ) { }
		}
	}
	public static void main (String args []) 
	{
		Object o_1 = new Object();
		Object o_2 = new Object();
		Object stop = new Object();
		new D("0", o_1, o_2, stop).start();
	}
}
