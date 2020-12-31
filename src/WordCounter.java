import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;




public class WordCounter {
	private String urlStr;
    private String content_raw;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
//		URLEncoder.encode(url.toString(), "Big5");
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
//			System.out.println(line);
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
    
    public double countKeyword(String keyword) throws IOException{
		if (content_raw == null){
		    content_raw = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content_raw = content_raw.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0; 
		// 1. calculates appearances of keyword 用indexof
		//int pos = content.indexOf
		//當pos ==-1表示沒找到
		//		int pos = content.indexOf(keyword);
		//		if(pos!=-1) {
		//			do sth
		//			pos = pos+... 
		//		}
//		String content = new String(content_raw.getBytes(),"utf-8");
//		System.out.println(new String(content_raw,"utf-8"));
//		System.out.println(content_raw);
		int pos;
		while(content_raw.indexOf(keyword)!=-1) {
			retVal++;
			pos=content_raw.indexOf(keyword);
			content_raw = content_raw.substring(pos+keyword.length(), content_raw.length());
		}
		
		return retVal;
    }
}