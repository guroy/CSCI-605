package exercise.n2;

/**
* Create a StringZipInputStream class, similar to GZipInputStream
*
*
* @version   $Id: MyCompressTest.java,v 1.0 2015/10/05 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
// import java.util.zip.StringZipInputStream;
// import java.util.zip.StringZipOutputStream;

public class MyCompressTest	{
    final  int MAX = 1024;
    String inputFileName 	= "words.txt";
    String outputFileName 	= "words.compress";
    String uncompressed 	= "words.uncompress";

    void compress()	{	
	try {
		String aWord;

		BufferedReader input = new BufferedReader(new FileReader(inputFileName));
//		StringZipOutputStream aStringZipOutputStream = new StringZipOutputStream( new FileOutputStream(outputFileName));

//		while (  ( aWord = input.readLine() )  != null ) {
//				System.out.println("write:	" + aWord);
//				aStringZipOutputStream.write(aWord);
//		}
//		aStringZipOutputStream.close();
		input.close();
		
	} catch ( Exception e )	{
		e.printStackTrace();
		System.exit(1);
	}
    }
    void unCompress()	{	
	try {
		String aWord;
		byte[] buffer = new byte[MAX];

		BufferedWriter uncompress = new BufferedWriter(new FileWriter(uncompressed));
		StringZipInputStream aStringZipInputStream = new StringZipInputStream( new FileInputStream(inputFileName));
		String theWord;

//		while (  ( theWord = aStringZipInputStream.read() ) != null ) {
//			uncompress.write(theWord, 0, theWord.length());
//		}
		aStringZipInputStream.close();
		uncompress.close();
		
	} catch ( Exception e )	{
		e.printStackTrace();
		System.exit(1);
	}
    }
    public static void main( String args[] ) {
	MyCompressTest aMyCompressTest = new MyCompressTest();
	aMyCompressTest.compress();
    }
}
