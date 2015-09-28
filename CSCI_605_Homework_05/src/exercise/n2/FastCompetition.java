package exercise.n2;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: FastCompetition.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 * @param <E>
 *
 */


public class FastCompetition<E> implements Competition<E> {
	Object[] values;
	int index;
	
	public FastCompetition() 
	{
		values = new Object[1000000];
		index = 0;
	}
	public FastCompetition(int size) 
	{
		values = new Object[size];
		index = 0;
	}

	@Override
	public boolean add(E e) {
		if (index < 999999) 
		{
			values[index] = e;
			index++;
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		boolean result = false;
		for(int i = 0; i < index; i++) 
		{
			if (o.equals(values[i])) 
			{
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean remove(Object o)
	{
		boolean res = false;
		boolean loop = true;
		int count = 0;
		while(loop && count < index)
		{
			if(!o.equals(values[count]))
			{
				count++;
			}
			else
			{
				loop = false;
				for(; count < this.index-1; count++)
				{
					values[count] = values[count+1];
				}
				index--;
				res = true;
			}
		}
		return res;
	}

	@Override
	public E elementAt(int index)
{
		if (0 <= index && this.index >= 0 && index < this.index ) 
		{
			return (E)values[index];
		}
		return null;
	}

	@Override
	public Competition<E> sort()
	{
		E tmp;
		for (int i = 0; i < index; i++) {
			for (int j = i; j < index; j++) {
				if (((String)values[j]).compareTo((String)values[i]) < 0) {
					tmp = (E)values[j];
					values[j] = values[i];
					values[i] = tmp;
				}
			}
		}
		return this;
	}

	@Override
	public int size() 
	{
		return index;
	}

}
