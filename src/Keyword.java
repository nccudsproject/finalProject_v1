/*
 * 1.keyword的資料型態
 * 2.field: name、count、weight
 * 3.constructor:要給name、count、weight
 * 4.method: String toString(): return "["+name+","+count+","+weight+"]"、
 *           void setCount(double a)、
 *           double getCount()、
 *           String getName()、
 *           double getWeight()。
 */

public class Keyword {
	public String name;
    public double count;
    public double weight;
    /*
     * constructor
     */
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