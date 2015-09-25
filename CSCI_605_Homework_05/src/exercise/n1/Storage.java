package exercise.n1;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: Storage.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */


public interface Storage<E, V> {
	// Appends the specified element to the end of this storage.
	// returns true, if the element could be added, else false 
	boolean	add(E e);

	// Inserts the specified element at the specified position in this Storage.
	// returns true, if the element could be added at position index, else false 
	public boolean add(int index, E element);

	// Adds the specified component to the end of this vector, increasing its size by one.
	public void	addElement(E obj);

	// Adds the specified component to the end of this vector, increasing its size by one.
	public void	addElement(E obj, V elem);

	// Returns the current capacity of this vector.
	public int	capacity();

	// Removes all of the elements from this storage.
	public void	clear();

	// Returns a clone of this vector.
	// public Object	clone();

	// Returns the first component (the item at index 0) of this vector.
	public E	firstElement();

	// Returns the element at the specified position in this storage.
	public E	get(int index);

	// Returns the last component of the vector.
	public E	lastElement();
}
