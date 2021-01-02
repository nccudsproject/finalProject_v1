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
/*
 * 1.模仿google搜尋
 * 2.field: serchKeyword(使用者輸入的keyword)
 *          urlString(call Google使用的連結，會將keyword放入)
 *          content(模仿google search後的結果)
 *          u(當使用者輸入url時使用)
 *          urlList(存放抓下來的title+url)
 * 3.constructor: a.input keyword，用input的keyword生成一個urlstring並將他放入u，初始化urlList
 *                b.先不管
 * 4.method:String fetchContent()、
 *          String getContent、
 *          void query():將call google後抓下來的url+title放入ArrayList<UrlInf> urlList。、
 *          ArrayList<UrlInf> getUrls()
 */
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
//		this.urlString = "https://www.google.com.tw/search?q="+searchKeyword+"&tbm=vid&oe=utf8&num=100";
		//先測試20個，
		this.urlString ="https://www.google.com.tw/search?sxsrf=ALeKk00RuwmrezLvpG4Yb2VrWYKNjhEVzw%3A1609342671041&source=hp&ei=zp7sX4P5POmFr7wPytm74A0&q="+searchKeyword+"&num=10&gs_lcp=CgZwc3ktYWIQDDIECAAQHlD7BVj7BWC7DGgAcAB4AIABWogBWpIBATGYAQCgAQKgAQGqAQdnd3Mtd2l6&sclient=psy-ab&ved=0ahUKEwiDmOjRhPbtAhXpwosBHcrsDtwQ4dUDCAK";
//		全部:"http://www.google.com.tw/search?q="+searchKeyword+"&oe=utf8&num=30";
//		全部:"https://www.google.com.tw/search?sxsrf=ALeKk00RuwmrezLvpG4Yb2VrWYKNjhEVzw%3A1609342671041&source=hp&ei=zp7sX4P5POmFr7wPytm74A0&q="+searchKeyword+"&num=100&gs_lcp=CgZwc3ktYWIQDDIECAAQHlD7BVj7BWC7DGgAcAB4AIABWogBWpIBATGYAQCgAQKgAQGqAQdnd3Mtd2l6&sclient=psy-ab&ved=0ahUKEwiDmOjRhPbtAhXpwosBHcrsDtwQ4dUDCAK"
//		影片:"https://www.google.com.tw/search?q="+searchKeyword+"&tbm=vid";
		u=new URL(urlString);
		urlList=new ArrayList<UrlInf>(); 
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
		conn.setRequestProperty("User-agent", "Mozilla/5.0 Chrome/8.0 Safari/537.36");
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
	/*
	 * goal: 將call google後抓下來的url+title放入ArrayList<UrlInf> urlList。
	 * 
	 */
	public void query() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		Document doc = Jsoup.parse(content); 
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT"); 
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href"); 
				String title = li.select("a").get(0).select(".vvjwJb").text(); 
				if(!(li.select("a").get(0).select(".vvjwJb").text().isEmpty())) {
					urlList.add(new UrlInf(title, citeUrl));
					System.out.println(urlList.get(0).getScore());
				} 
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}		
		}
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