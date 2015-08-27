package exercise.n1;

/**
* Displays the user's OS
*
*
* @version   $Id: OSName.java,v 1.0 2015/08/25 $
* @author    Guillaume Roy
*
* Revisions:
*
* 	Initial revision
*
*/

class OsName {
	public static void main (String args []) { // main program
		System.out.println("OS: " + System.getProperty("os.name"));
	}
}