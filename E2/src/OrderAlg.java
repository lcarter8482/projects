public class OrderAlg extends AbstractOrderAlg
{
    private static OrderAlg m_instance = null;
	private OrderAlg() {}
	public static OrderAlg getInstance()
	{
		if (m_instance == null)
			m_instance = new OrderAlg();
		return m_instance;
	}
	
	@Override
	protected int getPenalty(char ch1)
	{
	    return 1;
	}
}