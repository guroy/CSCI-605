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

import java.util.*;


public class StorageFixed implements Storage<E, V> {
	
	private static final int MAXIMUM_CAPACITY = 100;
	
	private int currentIndex;

	private Object[] dataE = null,
					 dataV = null;
	
	private int capacity;

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(int index, E element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addElement(E obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addElement(E obj, V elem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E firstElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E lastElement() {
		// TODO Auto-generated method stub
		return null;
	}
}