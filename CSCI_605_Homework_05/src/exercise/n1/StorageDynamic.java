/**
 * 
 */
package exercise.n1;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: StorageDynamic.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public class StorageDynamic<E, V> implements Storage<E, V> {

	Object[] storage;
	int currentIndex;
	
	/**
	 * 
	 */
	public StorageDynamic() {
		currentIndex = -1;
		storage = null;
	}

	public boolean add(E e) {
		if(currentIndex == -1)
		{
			storage = new Object[1];
			currentIndex = 0;
		}
		else
		{
			currentIndex++;
			Object[] buf = new Object[currentIndex + 1];
			for(int i = 0; i < buf.length - 1; i++)
			{
				buf[i] = storage[i];
			}
			storage = buf;
		}
		storage[currentIndex] = e;
		return true;
	}

	public boolean add(int index, E element) 
	{
		if(currentIndex == -1 && index != 0)
		{
			return false;
		}
		else
		{
			int j;
			Object[] buf = new Object[currentIndex + 2];
			for(int i = 0; i < buf.length - 1; i++)
			{
				if(i <= index)
				{
					j = i;
				}
				else
				{
					j = i+1;
				}
				if(i == index)
				{
					buf[i] = element;
				}
				else
				{	
					buf[i] = storage[j];
				}
			}
			storage = buf;
		}
		return true;
	}

	public void addElement(E obj) {
		add(obj);
		
	}

	public void addElement(E obj, V elem) {
		add(obj);
		if(currentIndex == -1)
		{
			storage = new Object[1];
			currentIndex = 0;
		}
		else
		{
			currentIndex++;
			Object[] buf = new Object[currentIndex + 1];
			for(int i = 0; i < buf.length - 1; i++)
			{
				buf[i] = storage[i];
			}
			storage = buf;
		}
		storage[currentIndex] = elem;
	}

	public int capacity() 
	{
		if(currentIndex != -1)
		{
		return storage.length;
		}
		else 
			return -1;
	}

	public void clear() 
	{
		storage = null;
		currentIndex = -1;
	}

	public E firstElement() 
	{
		if(currentIndex != -1)
		{
		return (E)storage[0];
		}
		else
			return null;
	}

	public E get(int index) 
	{
		if(currentIndex != -1)
		{
		return (E)storage[index-1];
		}
		else
			return null;
	}

	public E lastElement() {
		if(currentIndex != -1)
		{
		return (E)storage[currentIndex];
		}
		else
			return null;
	}


}
