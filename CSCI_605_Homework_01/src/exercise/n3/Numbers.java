package exercise.n3;

import java.util.Vector;

/**
* SHeldon's favourite number
*
*
* @version   $Id: Numbers.java,v 1.0 2015/08/26 $
* @author    Guillaume Roy
*
* Revisions:
*
* 	Initial revision
*
*/

public class Numbers {
	private final static int NB_MAX = 10000;
	static Vector<Integer> primeNumberList = new Vector<Integer>( );
	
	public static void main ( String args [] ) { // main program
		setPrimeNumberList( ); // generation
		System.out.println( computeSolution( ) );
	}
	
	
	/**
	 * generate the solutions to the following problem:
	 * Write a program that looks for all numbers m, and n, which meets all of the following properties:
	 * n is the k.st prime number (73 is the 21. prime number) 
	 * m is mirror of k.st prime number (37 is the 12. prime number) 
	 * bN is a palindrome
	 * 
	 * @return all the values that answer to the problem
	 * 
	*/
	static Vector<Integer> computeSolution( ) {	
		Vector<Integer> solution = new Vector<Integer>( );
		
		for ( int n: primeNumberList ) {
			int m = getMirror( n );
			
			if ( primeNumberList.contains( m ) ) {
				if ( isMirror ( getPrimeNumberRange( m ), getPrimeNumberRange( n ) ) &&
					 isAPalindrome( n ) ) {
					solution.add( n );
				}
			}
		}
		
		return solution;
	}
	
	
	/**
	 * get the rank of a prime number
	 * 
	 * @param	n a number
	 * @return	the rank of n in the prime number list
	 *
	 */
	static int getPrimeNumberRange( int n ) {
		if ( primeNumberList.contains( n ) ) {
			return primeNumberList.indexOf( n ) + 1; // don't forget: starts with rank 1
		} else {
			return 0; // non possible case
		}
	}
	
	
	/**
	 * generate the list of the prime numbers < 10000
	 *
	 */
	static void setPrimeNumberList( ) {
		// let's add 2 and then do not bother about even numbers anymore
		primeNumberList.add(2);
		
		for ( int elt = 3; elt < NB_MAX; elt += 2 ) {		
			if ( isNotDivisible( elt, primeNumberList ) ) {
				primeNumberList.add( elt );
			}
		}
	}
	
	
	/**
	 * test if a number is divisible or not by any element of an ensemble
	 *
	 * @param	value the tested number
	 * @param	listOfInt an ensemble
	 * @return	true if value is not the multiple of any element of the ensemble
	 */
	static boolean isNotDivisible( int value, Vector<Integer> listOfInt ) {
		boolean res = true;
		
		for ( int i: listOfInt ) {
			if ( value % i == 0 ) {
				res = false;
				break;
			}
		}
		
		return res;
	}
	
	
	/**
	 * test if 2 numbers are mirrors
	 *
	 * @param	m an integer 
	 * @param	n an integer 
	 * @return	true if m is n's mirror
	 */
	static boolean isMirror( int m, int n ) {
		return m == getMirror( n ) && m != n; // a single digit is its own mirror... but it's not interesting in that problem
	}
	
	
	/**
	 * reverse the digits of a number
	 *
	 * @param	n an integer 
	 * @return	the reverse number
	 */
	static int getMirror( int n ) {
		return Integer.parseInt( getMirrorStr( Integer.toString( n ) ) );
	}
	
	
	/**
	 * reverse the digits of a number
	 *
	 * @param	strN a number in String form
	 * @return	the reverse number in String form
	 */
	static String getMirrorStr( String strN ) {
		// StringBuffer object contains a reverse() method
		StringBuffer buff = new StringBuffer( strN );
		String strNReverse = buff.reverse( ).toString( );
		
		return strNReverse;
	}
	
	
	/**
	 * test if a number's binary form is a palindrome
	 *
	 * @param	n the number in decimal form
	 * @return	true if n's binary form is a palindrome
	 */
	static boolean isAPalindrome( int n ) {
		String bN = Integer.toBinaryString( n );
		
		return bN.equals( getMirrorStr( bN ) );
	}
}