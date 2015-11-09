package n1;

import java.util.Scanner;

public class Player extends Thread
{
	String name;
	int score;
	private Object o; 
	
	Player(String name, Object o)
	{
		this.name = name;
		score = 0;
		this.o = o;
	}
	
	public void run()
	{
		synchronized(o)
		{
			try 
			{
				o.wait();
			} 
			catch (InterruptedException e) 
			{
			}
			Scanner keyboard = new Scanner(System.in);
			boolean exit = true;
			while(exit)
			{
				if(keyboard.nextLine().equals("a"))
				{
					System.out.println("Player 1");
					exit = false;
				}
				else if(keyboard.nextLine().equals("p"))
				{
					System.out.println("Player 2");
					exit = false;
				}
			}
//			final CountDownLatch latch = new CountDownLatch(1);
//			KeyEventDispatcher dispatcher = new KeyEventDispatcher()
//			{
//						public boolean dispatchKeyEvent(KeyEvent e)
//						{
//							boolean res = false;
//							if(name == "player 1" && e.getKeyCode() == KeyEvent.VK_SPACE)
//							{
//								System.out.println("player 1");
//								res =  true;
//							}
//							else if(name == "player 2" && e.getKeyCode() == KeyEvent.VK_LEFT)
//							{
//								System.out.println("player 2");
//								res = true;
//							}
//							return res;
//						}
//			};
//			
//			KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
//			try
//			{
//				latch.await();
//			}
//			catch (InterruptedException e1) 
//			{
//			}
//			KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
			
			
		}
	}
}
