package n1;

public class MeanObject {
    static int INIT_HASH_CODE = 1;
    static int currentHashCode  = INIT_HASH_CODE;
    static int MAX = Driver.MAX_ELEMENTS + 3;
    int hashCode = INIT_HASH_CODE;

    public MeanObject() {
/*
	if ( currentHashCode > Driver.MAX_ELEMENTS )
		currentHashCode = INIT_HASH_CODE;
*/
	hashCode = currentHashCode++;
    }
    public MeanObject(int id) {
	hashCode = id;
    }

   public boolean  equals(Object o)	{
	if ( o instanceof MeanObject )	{
		MeanObject m = (MeanObject)o;
		return hashCode == m.hashCode;
	} else
		return false;
   }
   public String toString()	{
	return "hashCode: " + hashCode;
   }
   public int hashCode()	{
	return hashCode;

   }
}

