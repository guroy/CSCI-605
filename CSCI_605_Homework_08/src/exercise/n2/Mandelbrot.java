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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Mandelbrot extends JFrame {

	private final int MAX 	= 5000;
	private final int LENGTH   	= 800;
	private final double ZOOM  	= 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];

	public Mandelbrot() {
		super("Mandelbrot Set");

		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void createSet()	{
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		int nbProc = Runtime.getRuntime().availableProcessors();
		//ExecutorService threadPool = Executors.newFixedThreadPool(nbProc);
		Thread[] multiT = new Thread[nbProc];
		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth()-nbProc; x+=nbProc) 
			{
				for(int i = 0; i < nbProc; i++)
				{
					multiT[i] = new Thread(new pixelThread(MAX, LENGTH, ZOOM, x+i, y, colors, theImage));
					multiT[i].start();
				}
//				Thread T1 = new Thread(new pixelThread(MAX, LENGTH, ZOOM, x, y, colors, theImage));
//				Thread T2 = new Thread(new pixelThread(MAX, LENGTH, ZOOM, x+1, y, colors, theImage));
//				Thread T3 = new Thread(new pixelThread(MAX, LENGTH, ZOOM, x+2, y, colors, theImage));
//				Thread T4 = new Thread(new pixelThread(MAX, LENGTH, ZOOM, x+3, y, colors, theImage));
				
//				T1.start();
//				T2.start();
//				T3.start();
//				T4.start();
				try
				{
					for(int i = 0; i < nbProc; i++)
					{
						multiT[i].join();
					}
//					T1.join();
//					T2.join();
//					T3.join();
//					T4.join();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			repaint();
		}
	}
	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index/256f, 1, index/(index+8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) {
		Mandelbrot aMandelbrot = new Mandelbrot();
		aMandelbrot.setVisible(true);
		aMandelbrot.createSet();
	}
}
