import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;



public class GoogleQ_v2 
{
	public String searchKeyword;
	public String urlString;
	public String content;
	public URL u;
	public ArrayList<UrlInf> urlList;
	
	public GoogleQ_v2(String searchKeyword) throws MalformedURLException
	{
		this.searchKeyword = searchKeyword;
//		this.urlString = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20"; //模仿輸入的搜尋結果，num=要的結果數量
		this.urlString ="https://www.google.com.tw/search?sxsrf=ALeKk00RuwmrezLvpG4Yb2VrWYKNjhEVzw%3A1609342671041&source=hp&ei=zp7sX4P5POmFr7wPytm74A0&q="+searchKeyword+"&num=100&gs_lcp=CgZwc3ktYWIQDDIECAAQHlD7BVj7BWC7DGgAcAB4AIABWogBWpIBATGYAQCgAQKgAQGqAQdnd3Mtd2l6&sclient=psy-ab&ved=0ahUKEwiDmOjRhPbtAhXpwosBHcrsDtwQ4dUDCAK";
//		"http://www.google.com.tw/search?q="+searchKeyword+"&oe=utf8&num=30";
//		"https://www.google.com.tw/search?q="+searchKeyword+"&tbm=vid";影片搜尋 
		u=new URL(urlString);
		urlList=new ArrayList<UrlInf>(); //?????
	}
	
	public GoogleQ_v2(URL u)
	{
		this.u=u;
		urlList=new ArrayList<UrlInf>();
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";
		//URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Mozilla/5.0");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"UTF-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		
		while((line=bufReader.readLine())!=null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public void query() throws IOException  //HashMap<String, String>
	{
		if(content==null)
		{
			content= fetchContent();
		}
//		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content); //轉成網頁結構
		System.out.println(doc.text());
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT"); //.+class name, #+id name, or 直接tag name
		// System.out.println(lis.size());
			
		for(Element li : lis)
		{
			try 
			{
//				System.out.println(li);
//				System.out.println("\n\n\n");
				String citeUrl = li.select("a").get(0).attr("href"); 
				String title = li.select("a").get(0).select(".vvjwJb").text(); //.Text()會選到文字，tag裡面的內容不會被選到
				if(li.select("a").get(0).select(".vvjwJb").text().isEmpty()) {
					title = "no Title";
				} //.Text()會選到文字，tag裡面的內容不會被選到
				//System.out.println(title + ","+citeUrl);
//				retVal.put(title, citeUrl); //hash放東西用put
				urlList.add(new UrlInf(title, citeUrl));
				
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}		
		}
//		return retVal;
	}
	
	public String getContent() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		return content;
	}
	
	public ArrayList<UrlInf> getUrls() throws IOException
	{
		
		return urlList;
	}
}