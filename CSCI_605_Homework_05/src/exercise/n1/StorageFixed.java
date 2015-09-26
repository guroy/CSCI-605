/**
 * 
 */
package exercise.n1;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: StorageFixed.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public class StorageFixed<E, V> implements Storage<E, V> {
	
	Object[] storage;
	int currentIndex;
	int capacity;
	static final int MAX_CAPACITY = 100;
	
	/**
	 * 
	 */
	public StorageFixed() {
		currentIndex = 0;
		capacity = MAX_CAPACITY;
		storage = new Object[capacity];
	}

	public boolean add(E e) {
		if (currentIndex < capacity - 1) {
			storage[currentIndex++] = e;
			return true;
		}
		return false;
	}

	public boolean add(int index, E element) {
		if (currentIndex < capacity - 1) {
			Object e = storage[index];
			storage[index] = element;
			storage[currentIndex++] = e;
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
			storage[currentIndex++] = elem;
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
