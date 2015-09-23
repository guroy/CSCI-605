package exercise.n2;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: TestFastCompetition.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */


public class TestFastCompetition	{

	FastCompetition<String> aStringStorage;
	public TestFastCompetition()	{
	}

	private void failure(String reason)	{
		System.err.println("You should never see this."); 
		System.err.println("Your program did not pass the test");
		System.err.println("Reason: " + reason);
		System.exit(0);
	}
	private void print(String reason)	{
		System.err.println("Reason: " + reason);
	}
	private void addTest()	{
		for ( int index = 0; index < 10000; index ++ )	{
			if ( ! (aStringStorage.add("hello"+ index) ) )
				failure("add");
			if ( aStringStorage.size() != index+1 )
				failure("size");

		}
	}
	private void containTest()	{
		for ( int index = 0; index < 10000; index ++ )	{
			if ( ! (aStringStorage.contains("hello"+ index) ) )
				failure("contains");
		}
	}
	private void sortTest()	{
		aStringStorage.sort();
		
		for ( int index = 0; index < 10000 - 1; index ++ )	{
			String thisOne = aStringStorage.elementAt(index);
			String nextOne = aStringStorage.elementAt(index+1);
			if ( thisOne.compareTo(nextOne) > 0 )
				failure("sortTest");
		}
	}
	private void removeTest()	{
		for ( int index = 0; index < 10000; index ++ )	{
			if ( ! (aStringStorage.remove("hello" + index)  ) )
				failure("remove " + index);
			if ( aStringStorage.size() != 10000 - index - 1)
				failure("remove.size");
		}
		if ( ! (aStringStorage.contains("hello" + 1)  ) )
			print("contains - expected");
		if ( ! (aStringStorage.remove("hello" + 1)  ) )
			print("remove - expected");
		
	}
	private void stressTest( FastCompetition<String> aStringStorage)	{
		long startTime = System.currentTimeMillis();
	
		this.aStringStorage = aStringStorage;

		addTest();
		containTest();
		// sortTest();
		removeTest();

		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		
	}
	
	public static void main(String args[] )     {
		TestFastCompetition aTestFastCompetition = new TestFastCompetition();
		FastCompetition<String> aFastCompetition = new FastCompetition<String>(10000000);
		aTestFastCompetition.stressTest(aFastCompetition);
		
	}
}
