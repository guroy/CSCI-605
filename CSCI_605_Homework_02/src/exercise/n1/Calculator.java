package exercise.n1;

import java.util.Arrays;
import java.util.Vector;

/**
* Add ’^’ and ’(’ ’)’ to the calculator from HW 1.2
* compute the arguments given as an expression
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
	
	Vector<String> myArguments;
	
	public Calculator(Vector<String> myArguments) {
		super();
		this.myArguments = myArguments;
	}
	

	static Vector<String> operators = initOperators( );

	
	public static void main ( String args [] ) { // main program
		//get the list of arguments from terminal
		Vector<String> myArgs = new Vector<String>(Arrays.asList( args ) );
		//create expression with such list
		Calculator myExpression = new Calculator( myArgs ) ;
		myExpression.handleTerminalIssues( );
		
		System.out.println( myExpression.myArguments );
		
		System.out.println( myExpression.computeExpression( ) );
	}

	
	/**
	 * get the result of a complexe expression with multiple parenthesis depth
	 *
	 * @return	the result
	 */
	int computeExpression( ) {
		int size = this.myArguments.size(),
			indexBeginning = 0,
			indexEnd,
			i = 0;
		
		while ( i < size ) {
			// if there are no parenthesis, compute simple expression
			if ( !this.myArguments.contains( "(" ) && !this.myArguments.contains( ")" ) ) {
				return Integer.parseInt( computeSingleParenthesisExpression( this.myArguments ) );
			}
			
			// If a parenthesis is opened, store its index
			if ( this.myArguments.get( i ).equals( "(" ) ) {
				indexBeginning = i++;
			} else if ( this.myArguments.get( i ).equals( ")" ) ) {
				indexEnd = i;
				// locate and compute single parenthesis in the expression
				this.myArguments.set( indexBeginning, computeSingleParenthesisExpression( copyFromTo( indexBeginning, indexEnd ) ) );
				this.removeFromTo( indexBeginning, indexEnd );
				i = 0; // try again with parenthesis w/ lower depth
			} else {
				i++;
			}
		}
		
		return Integer.parseInt( this.myArguments.get( 0 ) );
	}
	
	
	/**
	 * get the result of an operation of form ( X op Y ... op Z )
	 *
	 * @return	the result
	 */
	 static String computeSingleParenthesisExpression( Vector<String> expression ) {
		Vector<String> aLine = new Vector<String>( expression );
		
		int elt, // an iterator
			size = aLine.size( ),
			curPrec, // the current precedence
			nbOperators = operators.size();
		String tmpRes; // store a temporary result
		
		// compute operators one by one depending on precedence
		for ( curPrec = 0; curPrec < nbOperators; curPrec += 1 ) {
			if ( isRightDistributive( operators.get( curPrec ) ) ) {
				elt = size - 2;
				while ( elt > 0 ) {
					if ( precedence( aLine.get( elt ), false ) ==  curPrec ) {
						tmpRes = computeSingleOperation( aLine.get( elt - 1 ), aLine.get( elt ), aLine.get( elt + 1 ) );
						// let's replace a simple operation ( num ope num ) by its result ( res )
						aLine.set( elt - 1, tmpRes );
						aLine.remove( elt + 1 );
						aLine.remove( elt );
						// refresh size value
						size = aLine.size();
					}
					// look for next operator
					elt -= 2;
				}
			} else {
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
		}
			
		return aLine.get( 0 );
	}
	
	 
	 /**
	 * remove parts of a vector
	 *
	 * @param	from the index to begin the removal
	 * @param	to the index to end the removal
	 */
	 void removeFromTo( int from, int to) {
		 for ( int i = from ; i < to; i += 1 ) {
			 this.myArguments.removeElementAt( from + 1);
		 }
	 }
	 
	 
	 /**
	 * create parts of a vector
	 *
	 * @param	from the index to begin the creation
	 * @param	to the index to end the creation
	 * @return	the created vector
	 */
	 Vector<String> copyFromTo( int from, int to) {
		 Vector<String> myCopy = new Vector<String>();
		 
		 for ( int i = from + 1; i < to; i += 1 ) {
			 myCopy.add( this.myArguments.get( i ) );
		 }
		 
		 return myCopy;
	 }
	 
	
	/**
	 * Convert terminal '\*' , '\(' and '\)' form to understandable '*' , '(' and ')'
	 *
	 */
	void handleTerminalIssues( ) {
		// when typing "java Calculator arg1 arg2...", the terminal replace "*" with the list
		// of files in the current directory as it was told to do. That's why we use escape form '\*'.
		int size = this.myArguments.size();
		
		for ( int i = 0; i < size; i += 1 ) {
			if ( this.myArguments.get( i ).equals("\\*") ) {
				this.myArguments.set( i, "*" );
			}
		}
	}
	
	
	/**
	 * tell if the chosen operator is right distributive
	 *
	 * @param	operator
	 * @return	true if the operator is right distributive
	 */
	static boolean isRightDistributive( String operator ) {
		return operator == "^";
	}
	
	
	/**
	 * initialise each operator implemented in the Calculator
	 * 
	 * @return a vector containing each operator
	 */
	static Vector<String> initOperators(  ) {
		Vector<String> init = new Vector<String>( );
		
		// priority('^') > priority('+')
		init.add( "^" );
		init.add( "/" );
		init.add( "*" );
		init.add( "%" );
		init.add( "-" );
		init.add( "+" );
		
		return init;
	}
	
	
	/**
	 * get the precedence of each standard operator
	 *
	 * @param	operator a given operator
	 * @param	inverseOrder a boolean
	 * @return	the the operator's precedence
	 */
	static int precedence( String operator, boolean inverseOrder ) {
		int index = operators.indexOf( operator ),
			invertedIndex = operators.size( ) - index,
			res = ( inverseOrder ) ? invertedIndex : index; // change order 
		
		return res;
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
		case "^":
			res = (int) Math.pow( int1, int2 );
			break;
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