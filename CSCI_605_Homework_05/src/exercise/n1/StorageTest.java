package exercise.n1;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: StorageTest.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */


public class StorageTest {
    public static void main(String args[])	
    {
    	Storage<String, String> aStorageString = new StorageFixed<String, String>();
    	Storage<Integer, String> aStorageInteger = new StorageFixed<Integer, String>();
    	
    	Storage<String, String> bStorageString = new StorageDynamic<String, String>();
    	Storage<Integer, String> bStorageInteger = new StorageDynamic<Integer, String>();
    	
    	//aStorageString.add("Test String fixed");
    	//System.out.println(" a String : " + aStorageString.firstElement());
    	//aStorageInteger.add(1);
    	//System.out.println(" a Integer : " + aStorageInteger.firstElement());
    	
    	bStorageString.add("Test String dynamic 1 ");
    	System.out.println("Test firstElement String : " + bStorageString.firstElement());
    	bStorageString.add("Test add dynamic 2 ");
    	bStorageString.addElement("Test addElement(E) dynamic 3 ");
    	bStorageString.addElement("Test addElement(E, V) dynamic 4 ", " Test addElement(E, V) and 5");
    	System.out.println("Test get(index) dynamic 2 : " +bStorageString.get(2));
    	System.out.println("Test get(index) dynamic 3 : " +bStorageString.get(3));
    	System.out.println("Test get(index) dynamic 4 : " +bStorageString.get(4));
    	System.out.println("Test lastElement String 5 : " + bStorageString.lastElement());
    	System.out.println("Test capacity String : " +bStorageString.capacity());
    	
    	bStorageString.clear();

    	System.out.println("Test firstElement String : " + bStorageString.firstElement());
    	
    }
}
