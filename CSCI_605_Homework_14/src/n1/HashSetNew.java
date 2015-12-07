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
		if (!contains(e) && table.length * 2 < MAX_CAPACITY) {
			table[size++] = e;
			if (size > threshold) {
				Object[] table_tmp = new Object[table.length * 2];
				for (int i = 0; i < size; i++) {
					table_tmp[i] = table[i];
				}
				// reallocate the table
				table = table_tmp;
				threshold = (int) (table.length * LOAD_FACTOR);
			}
			return true;
		}
		return false;
	}
	
	public void clear() {
		table = new Object [table.length];
		size = 0;
	}
	
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (table[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Iterator<E> iterator() {
		return null;
	}
	
	public boolean remove(Object o) {
		if (contains(o)) {
			for (int i = 0; i < size; i++) {
				if (table[i] == o) {
					table[i] = table[size--];
					return true;
				}
			}
		}
		return false;
	}
	
	public int size() {
		return size;
	}
}
