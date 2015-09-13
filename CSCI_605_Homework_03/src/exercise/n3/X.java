package exercise.n3;

/**
 * Homework 3 exercise 3, comment code
 *
 *
 * @version   $Id: X.java,v 1.0 2015/09/13 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

class X {

	public static void main( String args[] ) {

		int n = 0;

		here: // label

			while ( true )  {
				// This loop is computed 2 times
				// First n equals 0
				// Then n equals 2
				while ( ( ( n != 3 ) || ( n != 5 ) ) && ( n < 4 ) )  { 	// as ( ( n != 3 ) || ( n != 5 ) ) is always true, we could replace
																		// this condition only by ( n < 4 )
					// ++n increments n before testing the value
					if ( ++n == 0 ) // we thereby test this equality 1 == 0 which is false
									// Second time we test 3 == 0 which is false
						System.out.println("a/  n is " + n );
					// n++ tests the value before incrementing
					else if ( n++ == 1 )    {	// First, n == 1, we test the equality 1 == 1, then increments n (n=2)
												// Second time we test the equality 3 == 1, then increments n (n=4)
						System.out.println("b/  n is " + n ); // displays 2 as n has been incremented
					} else if ( n++ == 2 )	// First, n == 2, that condition is true, but as it is tested in a else, it is note computed
											// Second time, n == 4, that condition is false but n is incremented, (n=5)
						System.out.println("c/  n is " + n );
					else
						System.out.println("d/  n is " + n ); // Second time, displays 5

					System.out.println("    executing break here");

				} // first n < 4 so the loop is executed again

				// We wish to display the result of the condition given as a parameter of the println method
				System.out.println( n % 2 == 0 ?	// n == 5 so n % 2 != 0
						( n == 4 ? "=" : "!" )
						: ( n % 3 != 0 ? "3" : "!3" )); // thus it compute that equality, 5 % 3 != 0 so it displays 3
				break here; // go to a part of the code with the same hierarchy as the here: label,
							// which means outside the two nested while loops. The program ends.
			}
	}
}
