public class CountAlg implements IDiffAlg
{
    private static CountAlg m_instance = null;
	private CountAlg() {}
	public static CountAlg getInstance()
	{
		if (m_instance == null)
			m_instance = new CountAlg();
		return m_instance;
	}
	
	@Override
	public int difference(String seq1, String seq2)
	{
	    int countA = 0;
	    int countG = 0;
	    int countC = 0;
	    int countT = 0;
	    int count2A = 0;
	    int count2G = 0;
	    int count2C = 0;
	    int count2T = 0;
        
        for (int i = 0; i < seq1.length(); i++)
	    {
	        char c = seq1.charAt(i);
	        
	        switch (c) 
	        {
            case 'A': countA++;
                break;
            case 'G': countG++;
                break;
            case 'C': countC++;
                break;
            case 'T': countT++;
                break;
            default:
            break;
	        }
	    }
	       
	   for (int j = 0; j < seq2.length(); j++)
	   {
	       char c2 = seq2.charAt(j);
	       
	       switch (c2) 
	        {
            case 'A': count2A++;
                break;
            case 'G': count2G++;
                break;
            case 'C': count2C++;
                break;
            case 'T': count2T++;
                break;
            default:
            break;
	        }
	   }
	   int totalA = Math.abs(countA - count2A);
	   int totalG = Math.abs(countG - count2G);
	   int totalC = Math.abs(countC - count2C);
	   int totalT = Math.abs(countT - count2T);

	   int absTotal = totalA + totalG + totalC + totalT;
	    
	   return absTotal;
	}
}