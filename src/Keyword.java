public class Keyword {
	public String name;
    public double count;
    public double weight;
    
    public Keyword(String name,double count,double weight){
		this.name = name;
		this.count =count;
		this.weight =weight;
    }
    
    @Override
    public String toString(){
    	return "["+name+","+count+","+weight+"]";
    }
    
    public void setCount(double a)
    {
    	count=a;
    }
    
    public double getCount()
    {
    	return count;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public double getWeight()
    {
    	return weight;
    }
}