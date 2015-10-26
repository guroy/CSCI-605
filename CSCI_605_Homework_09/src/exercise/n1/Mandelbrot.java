package exercise.n1;

/**
 * Convert Mandelbrot.java into a multithreaded version.
 *
 *
 * @version   $Id: Mandelbrot.java,v 1.0 2015/10/19 $
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
	public void createSet()	
	{
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		//ExecutorService threadPool = Executors.newFixedThreadPool(nbProc);
		ThreadManager manager = new ThreadManager(LENGTH,LENGTH);
		new pixelThread(MAX, LENGTH, ZOOM, colors, theImage, manager).start();
		repaint();
		System.out.println(manager.nbThread);
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
