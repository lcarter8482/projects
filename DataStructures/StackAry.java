public class StackAry<T> implements IStack<T>
{
    private int count;
    private T[] ary;
    
    public StackAry()
    {
        count = 0;
        ary = (T[]) new Object[1];
    }
    
    @Override
    public void push(T i)
    {
        if (count + 1 > ary.length)
        {
            grow();
        }
        
        ary[count] = i;
        count++;
    }
    
    @Override
    public T pop()
    {
        if (count == 0)
        {
            throw new IllegalStateException("pop error: stack is empty!");
        }
        
        T temp = ary[count-1];
        ary[count-1] = null;
        count--;
        return temp;
    }
    
    @Override
    public T top()
    {
        if (count == 0)
        {
            throw new IllegalStateException("top error: stack is empty!");
        }
        
        return ary[count-1];
    }
    
    @Override
    public boolean isEmpty()
    {
        return getSize() == 0;
    }
    
    @Override
	public int getSize()
	{
		return count;
	}
	
	public void clear()
	{
	    ary = (T[]) new Object[ary.length];
	    count = 0;
	}
    
    private void grow()
    {
        T[] newAry = (T[]) new Object[ary.length + 1];

        for(int i = 0; i < ary.length; i++)
        {
            newAry[i] = ary[i];
        }
        
        ary = newAry;
    }
    
    @Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("top->");
		if (!isEmpty())
		{
			for (int i = count - 1; i >= 0; i--)
			{
			    sb.append(ary[i]);
				if (i != 0) 
				{
				    sb.append("->");
				}
			}
		}
		return sb.toString();
	}
}
