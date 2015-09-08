package exercise.n4;

import java.util.Vector;

/**
* Program that determines all possible subsets in n people attending a party.
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

public class Subsets 
{
	public static void main(String args[])
	{
		//get the first argument of args[]
		int argin = Integer.parseInt(args[0]);
		//Display on the console the powerset of the int argin
		displaySubsets(argin);
	}
	
	/**
	 * Fill a vector of int with all the value between 1 and argin
	 *
	 * @param	argin an int terminal argument
	 * @return	A vector containing all the int between 1 and argin
	 */
	private static Vector<Integer> fillVector(int argin)
	{
		// create the vector of int that will contain all the number from 0 to argin
		Vector<Integer> res = new Vector<Integer>();
		//Loop that fill the vector res
		for(int count=1; count<=argin; count++)
		{
			res.add(count);
		}
		return res;
	}
	
	/**
	 * display on the console the powerset of the int argin
	 *
	 * @param	argin an int terminal argument
	 */
	private static void displaySubsets(int argin)
	{
		//String that contains the binary number to represent
		String buf = "";
		//table that will contain all the binary number that composed buf
		char binary[];
		//Vector of all the numbers between 0 and argin
		Vector<Integer> number = fillVector(argin);
		//print on the console the beginning of the subsets
		System.out.print("{ ");
		
		for(int count = 0; count <= Math.pow(2,argin)-1; count++)
		{
			buf = Integer.toBinaryString(count);
			binary = buf.toCharArray();
			
			System.out.print("{");
			//check all the bits off the binary count
			for(int i = 0; i < binary.length; i++)
			{
				//if the bit equal 1 then we have to add to the subset 
				//the number of number at the index i in the current subset
				if(binary[i] == '1')
				{
					System.out.print(number.get(binary.length - 1 - i));
				}
			}
			if(count != Math.pow(2, argin)-1)
			{
				System.out.print("}, ");
			}
			else
			{
				System.out.print("}");
			}
		}	
		System.out.print("}");
	}
}
