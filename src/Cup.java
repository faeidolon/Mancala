public class Cup 
{
	public Integer myStones;
	public Cup myOpposite;
	
	public Cup()
	{
		myStones = 4;
	}
	
	public Cup(Integer numStones)
	{
		myStones = numStones;
	}
	
	public Cup(Integer numStones, Cup opposite)
	{
		myStones = numStones;
		myOpposite = opposite;
	}
	
	public void setStones(Integer numStones)
	{
		myStones = numStones;
	}
	
	public void setOpposite(Cup opposite)
	{
		myOpposite = opposite;
	}
	
	public Integer getStones()
	{
		return myStones;
	}
	
	public Cup getOpposite()
	{
		return myOpposite;
	}
}
