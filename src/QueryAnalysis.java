import java.io.IOException;
import java.util.ArrayList;

/*
 * 1.
 * 2.field: GoogleQ_v2 originalQuery;
 *          String query;
 *          ArrayList<Keyword> keywords;
 * 3.constructor: 1.QueryAnalysis(String user_inputKW): 
 *                  a.(1)初始化一個GoogleQ_v2(使用者輸入的keyword)，並用GoogleQ_v2的query()將call google後
 *                     抓下來的url+title放入originalQuery的ArrayList<UrlInf> urlList
 *                    (2)初始化keywords: 並放入所有的keyword包含使用者的輸入
 *                    (3)呼叫getScore()將urlList裡面所有的url計算分數
 *                  b.input:使用者輸入的keyword(權重最重:1)
 *                  
 *                2.QueryAnalysis(String user_inputKW,ArrayList<Keyword> k):先不管
 * 4. method: void getScore()
 *            GoogleQ_v2 getGoogleQuery()
 */
public class QueryAnalysis {
	private GoogleQ_v2 originalQuery;
	private String user_inputKW;
	private ArrayList<Keyword> keywords;
	
	public QueryAnalysis(String user_inputKW) throws IOException
	{
		this.user_inputKW=user_inputKW;
		originalQuery=new GoogleQ_v2(user_inputKW.replace(" ", "+"));
		originalQuery.query();
		keywords=new ArrayList<Keyword>();
		//因為有點卡先拿一個做測試就好
		keywords.add(new Keyword("開箱",0,0.9));
//		keywords.add(new Keyword("新",0,0.5));
//		keywords.add(new Keyword("介紹",0,0.5));
//		keywords.add(new Keyword("新品",0,0.7));
//		keywords.add(new Keyword("寶",0,0.1));
//		keywords.add(new Keyword("比較",0,0.3));
//		keywords.add(new Keyword("測試",0,0.4));
//		keywords.add(new Keyword("看法",0,0.4));
//		keywords.add(new Keyword("感想",0,0.35));
//		keywords.add(new Keyword("評測",0,0.4));
//		keywords.add(new Keyword("揭",0,0.2));
//		keywords.add(new Keyword("夯",0,0.5));
//		keywords.add(new Keyword("實測",0,0.5));
//		keywords.add(new Keyword("人氣",0,0.35));
//		keywords.add(new Keyword("熱門",0,0.4));
//		keywords.add(new Keyword("分享",0,0.25));
		/*keywords.add(new Keyword("unbox",0,0.9));
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
		keywords.add(new Keyword("share",0,0.25));*/
//		keywords.add(new Keyword(user_inputKW,0,1));//�ϥΪ̿�J��keyword.
		getScore();  //先不算分數
		urlSort(0, originalQuery.getUrls().size() - 1);
	}
	
	public QueryAnalysis(String user_inputKW,ArrayList<Keyword> k) throws IOException
	{
		this.user_inputKW=user_inputKW;
		originalQuery=new GoogleQ_v2(user_inputKW);
		originalQuery.query();
		keywords=k;
		keywords.add(new Keyword(user_inputKW,0,1)); //�ϥΪ̿�J��keyword.
		getScore();
	}
	
	public void getScore() throws IOException
	{
		for(UrlInf u:originalQuery.getUrls()) 
		{	
			System.out.println(u.getTitle());
			System.out.println(u.getUrl());
			//----------先不要by Hsu(測試完)------------------
//			int a=u.getUrl().indexOf("http");
//			int b=u.getUrl().indexOf("&sa"); 
//			System.out.println("Url After sub: "+u.getUrl().substring(a,b));
			//-----------------------------------
//			System.out.println(u.getUrl().substring(a,b));
			WordCounter w=new WordCounter("https://www.google.com.tw"+u.getUrl()); 
			double totalScore=0;
			for(Keyword k:keywords)
			{
				try {
					k.setCount(w.countKeyword(k.getName())); //countKeyword最耗時間
					double count=k.getCount();
					double weight = k.getWeight();
					//------計算細節顯示by Hsu-----
					System.out.println("key_name"+k.getName());
//					System.out.println("key_count"+count);
//					System.out.println("key_weight"+weight);
//					System.out.println("----網頁內容------");
//					System.out.println(w.fetchContent());
					//--------------------------
					totalScore+=count*weight;					
					}
					catch (Exception e)
					{
						continue;
					}
			}
			//------設定urlList裡UrlInf的score
			u.setScore(totalScore);
			System.out.println(u.getScore()+"--after adding");
			System.out.println("=================================");
		}	
	}
	
	public GoogleQ_v2 getGoogleQuery()
	{
		return originalQuery;
	}
	
	public void urlSort(int leftbound, int rightbound) throws IOException {
		if (originalQuery.getUrls().size() == 0) {
			System.out.println("InvalidOperation");
			return;
		}
		if (leftbound >= rightbound) {
			return;
		}
		int i = leftbound;
		int j = rightbound;
		double key = originalQuery.getUrls().get(leftbound).getScore();
		while (i != j) {
			while (originalQuery.getUrls().get(j).getScore() < key && i < j) {
				j -= 1;
			}
			while (originalQuery.getUrls().get(i).getScore() >= key && i < j) {
				i += 1;
			}
			if (i < j) {
				swap(i, j);
			}
		}
		swap(leftbound, i);
		urlSort(leftbound, i - 1);
		urlSort(i + 1, rightbound);

	}
	
	private void swap(int aIndex, int bIndex) throws IOException{
		UrlInf temp = originalQuery.getUrls().get(aIndex);
		originalQuery.getUrls().set(aIndex, originalQuery.getUrls().get(bIndex));
		originalQuery.getUrls().set(bIndex, temp);
	}
	
}
