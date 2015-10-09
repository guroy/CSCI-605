package exercise.n2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
    
    public String streamToBytesToString(InputStream in) throws IOException
    {
    	int length = 46;
    	byte[] bytes = new byte[length];
    	int offset = 0;
    	int numRead = 0;
    	while (offset < bytes.length && 
    			(numRead = in.read(bytes, offset, bytes.length - offset)) >= 0) 
    	{
    		offset += numRead;
    	}

    	if (offset < bytes.length) {
    		throw new IOException("Could not completely read file " );
    	}
    	String res = "";
    	for(byte b : bytes)
    	{
    		res += toBinaryString(b);
    	}
    	System.out.println(res);
        return res;
    }
    public static String toBinaryString(byte n) 
    {
    	StringBuilder sb = new StringBuilder("00000000");
    	for (int bit = 0; bit < 8; bit++) 
    	{
    		if (((n >> bit) & 1) > 0) 
    		{
    			sb.setCharAt(7 - bit, '1');
    		}
    	}
    	return sb.toString();
    }
    
    public String read() throws IOException 
    {
    	String res = null;
    	String encoded = streamToBytesToString(input);
    	c = encoded.toCharArray();
    	if(i < c.length)
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
