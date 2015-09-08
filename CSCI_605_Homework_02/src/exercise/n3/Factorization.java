package exercise.n3;

import java.util.Collections;
import java.util.Vector;

/**
* Program that gets the factorisation of a given number
*
*
* @version   $Id: Factorization.java,v 1.0 2015/09/01 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

public class Factorization 
{
	public static void main(String args[])
	{
		Vector<Integer> res = new Vector<Integer>();
		//get the first argument of args[]
		int argin = Integer.parseInt(args[0]);
		res = factorization(argin);
		//display the factorization only if the int argin isn't a prime number
		if(!isPrimeNumber(argin))
			displayFact(argin, res);
	}
	
	/**
	 * Create the vector that contains the different prime numbers that compose the factorization of argin
	 *
	 * @param	argin an int terminal argument
	 * @return	A vector containing all the prime numbers that compose the factorization of argin
	 */
	private static Vector<Integer> factorization(int argin)
	{
		//creation of the vector that will contains all the prime number that composed the number
		Vector<Integer> fact = new Vector<Integer>();
		
		int buf = argin;
		if(isPrimeNumber(buf))
		{
			fact.add(buf);
			return fact;
		}
		else
		{
			for(int count = 2; count <= buf/2; count++)
			{
				//check is buf is divisible by count
				if(buf%count == 0)
				{
					if(Math.sqrt(buf) == count)
					{
						fact.add(count);
						fact.add(count);
					}
					//check if count is a prime number
					else if(isPrimeNumber(count))
					 {
						 //count is a prime number so we had it to the vector that will be display
						 fact.add(count);
						 fact.addAll(factorization(buf/count));
						 break;
					 }
					 //count isn't prime number so we applied the algorithm to factorize it with prime number
					 else
					 {
						fact.addAll(factorization(count));
					 }
				}//end if is divisible
			}//end for
		}//end else
		return fact;
	 }//end function
	
	/**
	 * Fill a vector of int with all the value between 1 and argin
	 *
	 * @param	argin an int terminal argument
	 * @param	res a vector of int that is displayed on the console
	 */
	private static void displayFact(int argin, Vector<Integer> res)
	{
		System.out.print(argin +" = ");
		//Sort the vector res in ascending order
		Collections.sort(res);
		for(int i = 0 ; i < res.size(); i++ )
		{
			System.out.print(res.get(i));
			//test if the loop reach the last element in order to not print a * operator
			if(i != res.size()-1)
				System.out.print(" * ");
		}
	}
	
	
	/**
	 * isPrimeNumber return if the int a is prime
	 * @param a, the integer that has to be check
	 * @return true if the integer a is a prime number, false otherwise
	 */
	 private static boolean isPrimeNumber(int a)
	 {
		 boolean res = true;
		 //if a is inferior to 1 it is not a prime number
		 if(a > 2)
		 {
			 //check if a is divisible by a number include between 2 and a/2 (no used to go after a/2)
			 for(int i=2; i<=a/2; i++)
			 {
				 if(a%i == 0)
				 {
					 // a is divisible by a number so it is not a prime number
					 res = false;
					 break;
				 }
			 }
		 }
		 else if(a == 2)
			 res = true;
		 else
			 res =  false;
		 return res;
	 }
}
