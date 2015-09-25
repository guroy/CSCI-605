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

	Object[] storage = null;
	int capacity;
	int currentIndex;
	static final int MAX_CAPACITY = 100, CAPACITY = 10;
	
	/**
	 * 
	 */
	public StorageDynamic() {
		currentIndex = 0;
		capacity = Math.min(CAPACITY, MAX_CAPACITY);
		storage = new Object[this.capacity];
	}

	public boolean add(E e) {
		if (currentIndex < capacity) {
			storage[capacity++] = e;
			return true;
		}
		return false;
	}

	public boolean add(int index, E element) {
		if (currentIndex < capacity) {
			E e = (E) storage[index];
			storage[index] = element;
			storage[capacity++] = e;
			return true;
		}
		return false;
	}

	public void addElement(E obj) {
		add(obj);
		
	}

	public void addElement(E obj, V elem) {
		add(obj);
		if (currentIndex < capacity) {
			storage[capacity++] = elem;
		}
		
		
	}

	public int capacity() {
		return capacity;
	}

	public void clear() {
		storage = new Object[capacity];
		
	}

	public E firstElement() {
		return (E)storage[0];
	}

	public E get(int index) {
		return (E)storage[index];
	}

	public E lastElement() {
		return (E)storage[currentIndex - 1];
	}


}
