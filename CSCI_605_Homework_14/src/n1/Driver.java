package n1;

import java.util.*;

public class Driver {
    final int MAX_KIND_OF_OBJECT_KIND = 2;
    final int MIN_KIND_OF_OBJECT_KIND = 0;
    final int MEAN_KIND_OF_OBJECT_KIND = MAX_KIND_OF_OBJECT_KIND;
    final static int MAX_ELEMENTS = 20000;
    Object allObjects[] = new Object[MAX_ELEMENTS];
    HashSet aHashSet;
    long milliSeconds = 0;
    int objectKind = 1;
    Object first, middle, last;
    static int identifier = 0;
    boolean passed;
   

    public Driver() {
    }

    public void init()	{
	milliSeconds = System.currentTimeMillis();
    }
    public void end()	{
	System.err.println("\nTime for all:	" + ( System.currentTimeMillis() - milliSeconds) );
    }

    public Object generateString()	{
	identifier++;
	return new String( new Date().toString() + "_" +  Math.random());
    }
    public Object generateObject()	{
	return new Object();
    }
    public Object generateMeanObject()	{
	return new MeanObject();
    }

    public Object objectGenerator(boolean X)	{
	return new MeanObject(MAX_ELEMENTS + 10);
    }
    public Object objectGenerator()	{

	if ( ( objectKind < MIN_KIND_OF_OBJECT_KIND ) 	||		// should not happen
	     ( objectKind > MAX_KIND_OF_OBJECT_KIND )	)
		objectKind = MIN_KIND_OF_OBJECT_KIND;
	if ( objectKind == MEAN_KIND_OF_OBJECT_KIND )
		return generateMeanObject();
	else if ( objectKind == 1 )
		return  generateObject();
	else 
		return  generateString();
	
    }


    public void initTest()	{
	passed = true;
	aHashSet.clear();								// we start with an emprt hashSet
	for ( int index = 0; index < MAX_ELEMENTS ; index ++ )	
			aHashSet.add( allObjects[index] = objectGenerator() );		// hashcode of MeanObject ...
    }

    public void addTest()	{
	initTest();

	if ( objectKind == MEAN_KIND_OF_OBJECT_KIND )	{
		passed = passed && ( aHashSet.size() == MAX_ELEMENTS );
		System.out.println("	addTest 1: " + ( passed ? "passed" : " failed "));
	}

	int soMany =  aHashSet.size();
	aHashSet.add("theSame");
	aHashSet.add("theSame");
	passed = passed && ( aHashSet.size() == soMany + 1);
	System.out.println("	addTest 2: " + ( passed ? "passed" : " failed "));
    }
    public void containsTest()	{
	initTest();

	for ( int index = 0; index < MAX_ELEMENTS ; index ++ )	{
			if ( ! aHashSet.contains( allObjects[index] ) )
				passed = false;
	}
	aHashSet.remove( allObjects[0] );
	if ( aHashSet.contains( allObjects[0] ) )
			passed = false;

	System.out.println("	containsTest: " + ( passed ? "passed" : " failed "));
    }
    public void isEmptyTest()	{
	aHashSet.clear();								// we start with an emprt hashSet
	boolean passed = true;
	if ( ! aHashSet.isEmpty() )
			passed = false;

	System.out.println("	isEmptyTest: " + ( passed ? "passed" : " failed "));
    }
    public void sizeTest()	{
	aHashSet.clear();								// we start with an emprt hashSet
	boolean passed = true;
	int soManyDifferentObjects = 0;
	for ( int index = 0; index < MAX_ELEMENTS ; index ++ )	{
			allObjects[index] = objectGenerator();
			if ( ! aHashSet.contains( allObjects[index] ) )
				soManyDifferentObjects++;
			aHashSet.add( allObjects[index]  );		// hashcode of MeanObject ...
	}
	passed = passed && ( aHashSet.size() == soManyDifferentObjects);
	System.out.println("	sizeTest: " + ( passed ? "passed" : " failed "));
    }
    public void removeTest()	{
	initTest();
	int soMany;

	for ( int index = 0; index < MAX_ELEMENTS ; index ++ )	{
		if ( aHashSet.contains( allObjects[index] ) )	{
			soMany = aHashSet.size();
			aHashSet.remove( allObjects[index] );
			passed &=  ( soMany - 1 == aHashSet.size() );
		}
	}
	System.out.println("	removeTest: " + ( passed ? "passed" : " failed "));

    }
    public void iteratorTest()	{
	initTest();
	Iterator aIterator = aHashSet.iterator();
	if ( aIterator == null )
		passed = false;
	else {
		try { 
			if ( aIterator.hasNext() )	{
				aHashSet.remove(aIterator.next() );
			} else {
				passed = false;
			}
		} catch ( Exception e ) {
			passed = false;
		}
	}
	System.out.println("	iteratorTest: " + ( passed ? "passed" : " failed "));
    }
   
   
   public void testOneKind()	{
		addTest();
		containsTest();
		isEmptyTest();
		sizeTest();
		removeTest();
		iteratorTest();
   }
   public void testIt(String whichOne)	{
	if ( whichOne == "orig" )	{
		System.out.println("HashSet");
		aHashSet = new HashSet();
	} else {
		System.out.println("HashSetNew");
		aHashSet = new HashSetNew();
	}

	allObjects = new Object[MAX_ELEMENTS];

	for ( int index = MIN_KIND_OF_OBJECT_KIND; index <= MAX_KIND_OF_OBJECT_KIND; index ++ )	{
		objectKind = index;	
		System.out.println("kind = " + objectKind );
		long milliSeconds = System.currentTimeMillis();
		testOneKind();
		System.out.println("	" + objectKind + ":time:		" + ( System.currentTimeMillis() - milliSeconds) );
	}
   }
   public void test(String whichOne)	{
	Driver aDriver = new Driver();
	aDriver.init();
	aDriver.testIt(whichOne);
	aDriver.end();
   }

   public static void main(String args[] )	{
	Driver aDriver = new Driver();
	aDriver.test("orig");
	aDriver.test("new");
	System.exit(0);
   }
}

