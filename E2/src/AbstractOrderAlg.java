public abstract class AbstractOrderAlg implements IDiffAlg
{
    public int difference(String seq1, String seq2)
    {
        int count = 0;
        
        for (int i = 0; i < seq1.length(); i++)
	    {
	        char c = seq1.charAt(i);
	        
	        for (int j = i; j < seq1.length(); j++)
	        {
	            char c2 = seq2.charAt(j);
	            
	            if (c != c2)
	            {
	                count += getPenalty(c);
	                break;
	            }
	            else
	            {
	                break;
	            }
	        }
	    }
	    return count;
    }
    
    abstract protected int getPenalty(char ch1);
}