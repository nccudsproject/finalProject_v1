
public class UrlInf {
	private String titleString;
	private String urlString;
	private double score;
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
