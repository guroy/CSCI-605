package exercise.n2;

public class Node 
{
	int frequency;
	Node left, right;
	char c;
	
	Node( char ch, int freq, Node l, Node r)
	{
		c = ch;
		frequency = freq;
		left = l;
		right = r;
	}
	
	boolean isLeaf()
	{
		return left == null && right == null;
	}
	
	int compareTo(Node comp)
	{
		return frequency - comp.frequency;
	}
}
