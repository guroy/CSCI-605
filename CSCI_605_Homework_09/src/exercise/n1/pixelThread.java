package exercise.n1;

/**
 * Convert Mandelbrot.java into a multithreaded version.
 *
 *
 * @version   $Id: pixelThread.java,v 1.0 2015/10/19 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 *
 */

// original from: http://rosettacode.org/wiki/Mandelbrot_set#Java
// modified for color
import java.awt.image.BufferedImage;

public class pixelThread extends Thread 
{
	private int MAX;
	private int LENGTH;
	private double ZOOM;
	private int x, y;
	private int[] colors;
	private BufferedImage theImage;
	private ThreadManager manager;
	
	public pixelThread(int max, int length, double zoom,int[] col, BufferedImage Img, ThreadManager man)
	{
		MAX = max;
		LENGTH = length;
		ZOOM = zoom;
		x = man.x;
		y = man.y;
		colors = col;
		theImage = Img;
		manager = man;
		man.nbThread++;
	}

	public void run()
	{

		while(!manager.isFinished())
		{

			synchronized(manager)
			{
				manager.notify();
				x = manager.x;
				y = manager.y;
				manager.newLine();
				int iter = setIter();

				if(iter >= MAX)
				{
					iter = 1;
				}
				setImg(iter);

				try
				{
					System.out.println(manager.x + " " +manager.y);
					if(!manager.isFinished() && manager.nbThread < 50)
					{
						new pixelThread(MAX, LENGTH, ZOOM, colors, theImage, manager).start();	
					}
					manager.wait();
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private synchronized int setIter()
	{
		double zx, zy, cX, cY;
		double tmp;
		int iter = 0;
		
		zx = zy = 0;
		cX = (x - LENGTH) / ZOOM;
		cY = (y - LENGTH) / ZOOM;
		while ( (zx * zx + zy * zy < 10 ) && ( iter < MAX - 1 ) ) 
		{	// this is the part for the parallel part
			tmp = zx * zx - zy * zy + cX;				// this is the part for the parallel part
			zy = 2.0 * zx * zy + cY;					// this is the part for the parallel part
			zx = tmp;							// this is the part for the parallel part
			iter++;							// this is the part for the parallel part
		}								// this is the part for the parallel part
		return iter;
	}
	
	private synchronized void setImg(int iter)
	{
		if ( iter > 0 )							// this is the part for the parallel part
			theImage.setRGB(x, y, colors[iter]);			// this is the part for the parallel part
		else								// this is the part for the parallel part
			theImage.setRGB(x, y, iter | (iter << 8));		// this is the part for the parallel part
	}
}
