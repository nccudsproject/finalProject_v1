import java.io.IOException;
import java.util.ArrayList;

public class QueryAnalysis {
	private GoogleQ_v2 originalQuery;
	private String query;
	private ArrayList<Keyword> keywords;
	public QueryAnalysis(String query) throws IOException
	{
		this.query=query;
		originalQuery=new GoogleQ_v2(query);
		originalQuery.query();
		keywords=new ArrayList<Keyword>();
		keywords.add(new Keyword("開箱",0,0.9));
		keywords.add(new Keyword("新",0,0.5));
		keywords.add(new Keyword("介紹",0,0.5));
		keywords.add(new Keyword("新品",0,0.7));
		keywords.add(new Keyword("寶",0,0.1));
		keywords.add(new Keyword("比較",0,0.3));
		keywords.add(new Keyword("測試",0,0.4));
		keywords.add(new Keyword("看法",0,0.4));
		keywords.add(new Keyword("感想",0,0.35));
		keywords.add(new Keyword("評測",0,0.4));
		keywords.add(new Keyword("揭",0,0.2));
		keywords.add(new Keyword("夯",0,0.5));
		keywords.add(new Keyword("實測",0,0.5));
		keywords.add(new Keyword("人氣",0,0.35));
		keywords.add(new Keyword("熱門",0,0.4));
		keywords.add(new Keyword("分享",0,0.25));
		keywords.add(new Keyword("unbox",0,0.9));
		keywords.add(new Keyword("new",0,0.5));
		keywords.add(new Keyword("introduction",0,0.5));
		keywords.add(new Keyword("comparison",0,0.3));
		keywords.add(new Keyword("test",0,0.4));
		keywords.add(new Keyword("perspective",0,0.4));
		keywords.add(new Keyword("thoughts",0,0.35));
		keywords.add(new Keyword("comment",0,0.4));
		keywords.add(new Keyword("uncover",0,0.2));
		keywords.add(new Keyword("hot",0,0.5));
		keywords.add(new Keyword("testing",0,0.5));
		keywords.add(new Keyword("famous",0,0.35));
		keywords.add(new Keyword("hit",0,0.4));
		keywords.add(new Keyword("share",0,0.25));
		keywords.add(new Keyword(query,0,1));//使用者輸入的keyword.
		getScore();
		
	}
	
	public QueryAnalysis(String query,ArrayList<Keyword> k) throws IOException
	{
		this.query=query;
		originalQuery=new GoogleQ_v2(query);
		originalQuery.query();
		keywords=k;
		keywords.add(new Keyword(query,0,1)); //使用者輸入的keyword.
		getScore();
	}
	
	public void getScore() throws IOException
	{
		for(UrlInf u:originalQuery.getUrls()) 
		{
			//GoogleQuery gg=new GoogleQuery("NCCU");
			//gg.query();
			/*UrlInf u1=new UrlInf("example","https://gogriffins.com.tw/");
			System.out.println(u1.getTitle());
			System.out.println(u1.getUrl());
			System.out.println(u1.getScore()+"--before adding");
			WordCounter w1=new WordCounter(u1.getUrl());
			u1.setScore(w1.countKeyword("NCCU"));
			System.out.println(u1.getScore()+"--after adding");*/
			
			System.out.println(u.getTitle());
			System.out.println(u.getUrl());
			System.out.println(u.getScore()+"--before adding");
			//System.out.println("------------");
			//if(u.getTitle()!=null)
			int a=u.getUrl().indexOf("http");
			int b=u.getUrl().indexOf("&sa");
			System.out.println(u.getUrl().substring(a,b));
			WordCounter w=new WordCounter(u.getUrl().substring(a,b));
			double totalScore=0;
			for(Keyword k:keywords)
			{
				try {
					
					k.setCount(w.countKeyword(k.getName()));
					totalScore+=k.getCount()*k.getWeight();					
					System.out.println(k.getName()+"--keyword's Name");
					System.out.println(k.getCount()+"--keyword's Count");
					System.out.println(k.getWeight()+"--keyword's Weight");
					System.out.println("----"+k.getCount()*k.getWeight()+"----");
					}
					catch (Exception e)
					{
						continue;
					}
			}
			u.setScore(totalScore);
			System.out.println(u.getScore()+"--after adding");
			System.out.println("=================================");
		}
		
	}
	
	public GoogleQ_v2 getGoogleQuery()
	{
		return originalQuery;
	}
	

}
