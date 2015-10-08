package exercise.n1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
* Create a StringZipOutputStream class, similar to GZipOutputStream
*
*
* @version   $Id: StringZipOutputStream.java,v 1.0 2015/10/05 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

import java.io.OutputStream;
import java.util.*;

public class StringZipOutputStream	
{
	OutputStream output;
	Vector<Node> letters;
	
	
	// Creates a new output stream with a default buffer size.
	public StringZipOutputStream(OutputStream out)	
	{
		output = out;
		letters = new Vector<Node>();
	}

	
	// Writes aString compressed output stream. This method will block until all data is written.
	public void write(String aString) throws IOException
	{
		/*READ THE TEXT AND CREATE THE EQUIVALENT TREE AND CODE*/
		//complete the vector of letters with nodes that record the frequency of each letter
		buildNodes(aString);
		// Now we want to build our tree
		buildTree();
        // build code table, 256 is the number of letter in the ASCII code
        String[] code = new String[256];
        buildCode(code,letters.get(0),"");
        
        /*WRITE THE RESULTING CODE AND ENCODED TEXT IN A BINARY FILE*/
		//write the tree in the output stream
        writeCode(letters.get(0));
		// write the encoded message
		char[] aChar = aString.toCharArray();
		//write the length of the message on 4 bytes
		output.write(aChar.length);
		System.out.println(aChar.length);
		//write the message in the file
		writeMessage(code, aChar);
	}
	
	//buildNodes fill the different nodes corresponding to the text
	private void buildNodes(String aString)
	{
		char buf;
		//complete the vector of letters with frequencies
		for(int i = 0; i < aString.length(); i++)
		{
			buf = aString.charAt(i);
			if(letters.isEmpty())
			{
				letters.add(new Node(buf, 1, null, null));
			}
			else
			{
				int index = contains(buf);
				if(index != -1)
				{
					letters.get(index).frequency++;
				}
				else
				{
					letters.add(new Node(buf, 1, null, null));
				}
			}
		}
	}
	
	
	//Contains to check if a c of value is present in the table of letters
	int contains(char c)
	{
		int res = -1;
		int count = 0;
		while(res == -1 && count < letters.size())
		{
			if(letters.elementAt(count).c == c)
				res = count;
			else
				count++;
		}
		
		return res;
	}
	
	//Sort the table of nodes in croissant order
	void sortNodes()
	{
		Node bufI = letters.get(0);
		for(int i = 0; i < letters.size() - 1; i++)
		{
			for(int j = i + 1; j < letters.size(); j++)
			{
				if(letters.get(j).compareTo(letters.get(i)) < 0)
				{
					bufI = letters.get(j);
					letters.set(j, letters.get(i));
					letters.set(i, bufI);
				}
			}
		}
	}
	
	//buildTree, build the tree and store it in the index 0 of letters
	private void buildTree()
	{
		Node root;
		while(letters.size() > 1)
		{
			sortNodes();
			root = new Node('*', letters.get(0).frequency + letters.get(1).frequency, letters.get(0),letters.get(1));
			letters.remove(0);
			letters.remove(0);
			letters.add(root);
		}
	}
	
	//writeMessage write the encoded message in the output stream
	private void writeMessage(String[] code, char[] aChar) throws IOException
	{
		for (int i = 0; i < aChar.length; i++) 
		{
            String binary = code[aChar[i]];
            System.out.println(binary);
            for (int j = 0; j < binary.length(); j++) 
            {
                if (binary.charAt(j) == '0') 
                {
                    output.write(0);
                }
                else if (binary.charAt(j) == '1') 
                {
                    output.write(1);
                }
                else throw new IllegalStateException("Illegal state");
            }
        }
	}

    // make a lookup table from symbols and their encodings
    private void buildCode(String[] code, Node n, String path) 
    {
    	//If n isn't a leaf, we need to go deeper
        if (!n.isLeaf()) 
        {
            buildCode(code, n.left,  path + '0');
            buildCode(code, n.right, path + '1');
        }
        else // if it's a leaf, we fill the case in the code table that correspond to this letter with the equivalent code that represent it 
        {
            code[n.c] = path;
        }
    }
    
    private void writeCode(Node n) throws IOException
    {
        if (n.isLeaf()) 
        {
        	output.write(1);
        	System.out.print(1);
        	output.write((int)n.c);
        	System.out.print((int)n.c);
        }
        else
        {
	        output.write(0);
	        System.out.print(0);
	        writeCode(n.left);
	        writeCode(n.right);
        }
    }
	
	// Writes remaining data to the output stream and closes the underlying stream.
	public void close() throws IOException 
	{
		output.close();
	}
	
	public static void main(String args[]) throws IOException
	{
		String aWord;
		String inputFileName = "abra.txt";
		String outputFileName = "abra.compress";
		BufferedReader input = new BufferedReader(new FileReader(inputFileName));
		StringZipOutputStream aStringZipOutputStream = new StringZipOutputStream( new FileOutputStream(outputFileName));

		while (  ( aWord = input.readLine() )  != null ) {
				System.out.println("write:	" + aWord);
				aStringZipOutputStream.write(aWord);
		}
		aStringZipOutputStream.close();
		input.close();
	}
} 
