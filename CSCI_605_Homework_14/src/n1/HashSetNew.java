package n1;

import java.util.*;

public class HashSetNew<E> extends HashSet<E> {
	// prevent some 
	private static final long serialVersionUID = 1L;
	
	// the capacity of the table is always power of 2
	final static int MIN_CAPACITY = 16; // 2^4
	final static int MAX_CAPACITY = 1073741824; // 2^30
	// when the table is more than 75% filled we rehash the table
	final static float LOAD_FACTOR = 0.75f;
	// the table that contains the elements
	Object[] table;
	// the size of the table
	int size;
	// The next size value at which to resize 
	int threshold;
	
	public HashSetNew() {
		table = new Object[MIN_CAPACITY];
		threshold = (int) (MIN_CAPACITY * LOAD_FACTOR);
		size = 0;
	}

	public boolean add(E e) {
		// we need to be sure that the element does not already exist
		if (!contains(e)) {
			size++;
			return true;
		}
		return false;
	}
	
	public void clear() {
		
	}
	
	public boolean contains(Object o) {
		return true;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Iterator<E> iterator() {
		return null;
	}
	
	public boolean remove(Object o) {
		if (contains(o)) {
			size--;
			return true;
		}
		return false;
	}
	
	public int size() {
		return size;
	}
}
