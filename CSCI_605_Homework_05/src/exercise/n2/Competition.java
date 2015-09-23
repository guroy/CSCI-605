package exercise.n2;
public interface Competition<E>	{
	// Appends the specified element to this storage.
	boolean	add(E e);
	// Returns true if this vector contains the specified element.
	boolean	contains(Object o);	
	// Removes the first occurrence of the specified element in this storage.
	// If the storage does not contain the element, it is unchanged.
	boolean	remove(Object o);	
	// Returns the component at the specified index.
	E elementAt(int index);	
	// Sorts the storage
	Competition<E>	sort();	
	// Returns the number of components in this storage.
	int size();
}
