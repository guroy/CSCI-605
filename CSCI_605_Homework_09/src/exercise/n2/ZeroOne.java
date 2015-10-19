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
	private String info;
	static Object o = new Object();
	static boolean oneIsRunning = false;
					     
	public ZeroOne (String info) {
		this.info    = info;
	}
	public void run () {
		while ( true )	{
			synchronized ( o ) {
				o.notify();
				System.out.print(info);
				try {
					if ( ! oneIsRunning )	{
						( new ZeroOne("1") ).start();
						oneIsRunning = true;
					}
					sleep(300);
					o.wait();
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		new ZeroOne("0").start();
	}
}
