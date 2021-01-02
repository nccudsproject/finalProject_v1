/*
 * 1.連結的資料型態，有title、url、score，
 * 2.constructor要給title 跟url
 * 3.score要用setScore給
 * 4.method: String getTitle()、
 *           String getUrl()、
 *           void setScore(double s)、
 *           double getScore()。           
 */
public class UrlInf {
	private String titleString;
	private String urlString;
	private double score;
	/*
	 * constructor
	 */
	public UrlInf(String title,String url) {
		// TODO Auto-generated constructor stub
		titleString=title;
		urlString=url;
		score=0;
	}
	
	public String getTitle()
	{
		return titleString;
	}
	
	public String getUrl()
	{
		return urlString;
	}
	public void setScore(double s)
	{
		score=s;
	}
	public double getScore()
	{
		return score;
	}
}
