package exercise.n1;

import java.util.Vector;

/**
* Add ’^’ and ’(’ ’)’ to the calculator from HW 1.2
*
*
* @version   $Id: Calculator.java,v 1.0 2015/09/01 $
* @author    Gurvan Lecuyer
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
		for ( String s: args ) {
			aLine.add( handleTerminalAsteriskIssue( s ) );
        }
		
		System.out.println( aLine );
		System.out.println( compute( ) );
	}

	
	/**
	 * Convert terminal '\*' form to understandable '*' multiplication sign
	 *
	 * @param	arg a terminal argument
	 * @return	the argument having changed '\*' by '*'
	 */
	static String handleTerminalAsteriskIssue( String arg ) { // Asterisk et périls
		// when typing "java Calculator arg1 arg2...", the terminal replace "*" with the list
		// of files in the current directory as it was told to do. That's why we use escape form '\*'.
		arg = arg.equals("\\*") ? "*" :  arg;
		return arg;
	}
	
	
	/**
	 * get the precedence of each standard operator
	 *
	 * @param	operator a given operator
	 * @return	the the operator's precedence
	 */
	static int precedence( String operator, boolean inverseOrder ) {
		int res = 0;
		
		switch ( operator ) {
		case "+":
			res = ( inverseOrder ) ? 5 : 1;
			break;
		case "-":
			res = ( inverseOrder ) ? 4 : 2;
			break;
		case "%":
			res = 3;
			break;
		case "*":
			res = ( inverseOrder ) ? 2 : 4;
			break;
		case "/":
			res = ( inverseOrder ) ? 1 : 5;
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

		// compute operators one by one depending on precedence
		for ( curPrec = 5; curPrec >= 1; curPrec -= 1 ) { 
			elt = 1;
			while ( elt < size - 1 ) {
				if ( precedence( aLine.get( elt ), false ) ==  curPrec ) {
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