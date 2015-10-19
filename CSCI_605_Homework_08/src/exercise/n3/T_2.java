package exercise.n3;

/**
* What are all the possible outputs of T_1.java and T_2.java?
*
*
* @version   $Id: T_2.java,v 1.0 2015/10/15 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
*
*/

public class T_2 extends Thread    {
    int id = 1;
    static String  theValue  = "1";
    T_2(int id)       {
        this.id = id;
    }
    public void run () {
        if ( id == 1 )
                theValue = "3";
        else
                theValue = "2";
    }      
        
    public static void main (String args []) {
        new T_2(1).start();
        new T_2(2).start();
            
        System.out.println("theValue = " + theValue );
        System.out.println("theValue = " + theValue );
        
        // Possible cases : (with examples)
 		/*
 		 * theValue = 1		If T_2(1).run() is executed after the 
 		 * theValue = 2		println method and then T_2.run() is executed
 		 * 
 		 * theValue = 1		If both println are executed before both run method
 		 * theValue = 1
 		 * 
 		 * theValue = 3		If both run method are executed before 
 		 * theValue = 2		the println method and T_2(1).run is executed first
 		 * 
 		 * theValue = 2		only T_2(2).run is executed before the println
 		 * theValue = 2
 		 * 
 		 * theValue = 1		the println method of T_2(1) is executed 
 		 * theValue = 3		and then T_2(1).run() then T_2(1).println is executed
 		 */
    }       
}  
