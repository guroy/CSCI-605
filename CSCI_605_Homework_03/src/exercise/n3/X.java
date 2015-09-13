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

		here:

			while ( true )  {

				while ( ( ( n != 3 ) || ( n != 5 ) ) && ( n < 4 ) )  {
					if ( ++n == 0 )
						System.out.println("a/  n is " + n );
					else if ( n++ == 1 )    {
						System.out.println("b/  n is " + n );
					} else if ( n++ == 2 )
						System.out.println("c/  n is " + n );
					else
						System.out.println("d/  n is " + n );

					System.out.println("    executing break here");

				}

				System.out.println( n % 2 == 0 ?
						( n == 4 ? "=" : "!" )
						: ( n % 3 != 0 ? "3" : "!3" ));
				break here;
			}
	}
}
