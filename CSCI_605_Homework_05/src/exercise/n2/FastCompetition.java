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
	int[] occurences;
	int index;
	int maximumIndex;
	int nbElementsStored;
	
	
	public FastCompetition() {
		values = new Object[100000];
		occurences = new int[100000];
		index = 0;
		maximumIndex = 0;
		nbElementsStored = 0;
	}

	@Override
	public boolean add(E e) {
		if (nbElementsStored < 999999) {
			if (!contains(e)) {
				if (maximumIndex < 99999) {
					values[index] = e;
					occurences[index++] = 1;
					nbElementsStored++;
				} else {
					return false;
				}
			} else {
				occurences[index]++;
				nbElementsStored++;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		boolean result = false;
		for(int i = 0; i < maximumIndex; i++) {
			if (o.equals(values[i])) {
				index = i;
				result = occurences[i] > 0;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean remove(Object o) {
		if (contains(o)) {
			occurences[index]--;
			return true;
		}
		return false;
	}

	@Override
	public E elementAt(int index) {
		if (0 < index) {
			if (index < 100000) {
				return (E)values[index];
			}
		}
		return null;
	}

	@Override
	public Competition<E> sort() {
		return null;
	}

	@Override
	public int size() {
		return nbElementsStored;
	}

}
