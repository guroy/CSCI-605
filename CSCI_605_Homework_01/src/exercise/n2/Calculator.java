package exercise.n2;

import java.util.Vector;

/**
* A simple calculator
*
*
* @version   $Id: Calculator.java,v 1.0 2015/08/25 $
* @author    Guillaume Roy
*
* Revisions:
*
* 	Initial revision
*
*/

public class Calculator {
	static Vector<String> aLine = new Vector<String>(); // the hardcoded expression
	
	public static void main ( String args [] ) { // main program		
		aLine.add("2"); aLine.add("+"); aLine.add("3"); aLine.add("*"); aLine.add("4");
		aLine.add("*"); aLine.add("3"); aLine.add("*"); aLine.add("4");
		System.out.println( compute( ) );
	}

	
	/**
	 * get the precedence of each standard operator
	 *
	 * @param	operator a given operator
	 * @return	the the operator's precedence
	 */
	static int precedence( String operator ) {
		int res = 0;
		
		switch ( operator ) {
		case "+":
			res = 1;
			break;
		case "-":
			res = 2;
			break;
		case "%":
			res = 3;
			break;
		case "*":
			res = 4;
			break;
		case "/":
			res = 5;
			break;
		default:
			break;	
		}
		
		return res;
	}
	
	
	/**
	 * get the result of an operation
	 *
	 * @return	the result
	 */
	static int compute( ) {
		int elt, // an iterator
			size = aLine.size(),
			curPrec; // the current precedence
		String tmpRes; // store a temporary result

		for ( curPrec = 5; curPrec >= 1; curPrec -= 1 ) {
			elt = 1;
			while ( elt < size - 1 ) {
				if ( precedence( aLine.get( elt ) ) ==  curPrec ) {
					tmpRes = computeSingleOperation( aLine.get( elt - 1 ), aLine.get( elt ), aLine.get( elt + 1 ) );
					// let's replace a simple operation ( num ope num ) by its result ( res )
					aLine.set( elt, tmpRes );
					aLine.remove( elt - 1 );
					aLine.remove( elt );
					// refresh size value
					size = aLine.size();
				} else {
					// look for next operator
					elt += 2;
				}
			}
		}
			
		return Integer.parseInt( aLine.get( 0 ) );
	}
	
	
	/**
	 * get the result of a single operation
	 *
	 * @param	num1 the value of the number at the left
	 * @param	operator the operator (+,-,%,*,/)
	 * @param	num2 the value of the number at the right
	 * @return	the result as a String
	 */
	static String computeSingleOperation( String num1, String operator, String num2 ) {
		int res = 0,
			int1 = Integer.parseInt( num1 ),
			int2 = Integer.parseInt( num2 );
		
		switch ( operator ) {
		case "+":
			res = int1 + int2;
			break;
		case "-":
			res = int1 - int2;
			break;
		case "%":
			res = int1 % int2;
			break;
		case "*":
			res = int1 * int2;
			break;
		case "/":
			res = int1 / int2;
			break;
		default:
			break;
		}
		
		return String.valueOf( res );
	}
}
