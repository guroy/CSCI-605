package exercise.n2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
* Create a StringZipInputStream class, similar to GZipInputStream
*
*
* @version   $Id: StringZipInputStream.java,v 1.0 2015/10/05 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import exercise.n1.StringZipOutputStream;
import sun.misc.IOUtils;

public class StringZipInputStream	
{

	InputStream input;
	char[] c;
	int i;
	// Creates a new input stream with a default buffer size.
	public StringZipInputStream(InputStream out)	
	{
		input = out;
		i = 0;
		c = null;
	}
	// Reads data into a string. the method will block until some input can be read; otherwise, no bytes are read and null is returned.
//	public String read() {
//	}
	// Closes this input stream and releases any system resources associated with the stream.
	public void close() throws IOException 
	{
		input.close();
	}
	
	//readInt
	private int readInt()
	{
		String length = "";
		
		for(; i <= i+32; i++)
		{
			length += c[i];
		}
		
		return Integer.parseInt(length,2);
	}
	
	//Read file
    private Node readFile() throws IOException 
    {  
        if (c[i] == '1') 
        {
        	String b = "";
        	for (i = i+1; i <= i + 8; i++)
        	{
        		b += c[i];
        	}
        	int bint = Integer.parseInt(b,2);
        	i++;
            return new Node((char)bint, -1, null, null);
        }
        else 
        {
        	char[] incpy = new char[c.length -1];
        	for (int j = 0; j < c.length - 1; j++)
        	{
        		incpy[j] = c[j+1];
        	}
        	c = incpy.clone();
        	i++;
            return new Node('*', -1, readFile(), readFile());
        }
    }
    
    //read a bit
    private boolean readBoolean()
    {
    	boolean res = false;
    	if(c[i] == '1')
    	{
    		res = true;
    	}
		i++;
		return res;
    }
    
    //convert the current stream to a string
    public String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
    
    public String read() throws IOException 
    {
    	String res = null;
    	String encoded = fromStream(input);

    	System.out.println(encoded);
    	c = encoded.toCharArray();
    	if(i > c.length)
    	{
    		//read and construct the tree
    		Node root = readFile();
    		//get the size  of the message

    		int length = readInt();

    		// decode using the Huffman tree
    		for (int i = 0; i < length; i++) 
    		{
    			Node x = root;
    			while (!x.isLeaf()) 
    			{
    				boolean bit = readBoolean();
    				if (bit) x = x.right;
    				else     x = x.left;
    			}
    			res = x.c + "";
    			System.out.println(res);
    		}
    	}
    	return res;
    }
} 
