package exercise.n2;

/**
 * Convert Mandelbrot.java into a multithreaded version.
 *
 *
 * @version   $Id: Mandelbrot.java,v 1.0 2015/10/15 $
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
	
	public pixelThread(int max, int length, double zoom, int xin, int yin,int[] col, BufferedImage Img)
	{
		MAX = max;
		LENGTH = length;
		ZOOM = zoom;
		x = xin;
		y = yin;
		colors = col;
		theImage = Img;
	}

	public void run()
	{
		double zx, zy, cX, cY;
		zx = zy = 0;
		cX = (x - LENGTH) / ZOOM;
		cY = (y - LENGTH) / ZOOM;
		int iter = 0;
		double tmp;
		while ( (zx * zx + zy * zy < 10 ) && ( iter < MAX - 1 ) ) 
		{	// this is the part for the parallel part
			tmp = zx * zx - zy * zy + cX;				// this is the part for the parallel part
			zy = 2.0 * zx * zy + cY;					// this is the part for the parallel part
			zx = tmp;							// this is the part for the parallel part
			iter++;							// this is the part for the parallel part
		}								// this is the part for the parallel part
		setImg(iter);
	}
	
	private synchronized void setImg(int iter)
	{
		if ( iter > 0 )							// this is the part for the parallel part
			theImage.setRGB(x, y, colors[iter]);			// this is the part for the parallel part
		else								// this is the part for the parallel part
			theImage.setRGB(x, y, iter | (iter << 8));		// this is the part for the parallel part
	}
}
