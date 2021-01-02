import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/*
 * 1.constructor給一個urlstring，用fetchContent()將url會搜到的內容return出來，
 *   再用countKeyword(keyword)給一個keyword並回傳keyword在content出現的次數                    
 * 2.field: urlStr、content_raw
 * 3.constructor: 給urlStr
 * 4.method: String fetchContent():將url的內容抓下來。
 *           double countKeyword(String keyword): 將keyword出現的次數return
 */
public class WordCounter {
	private String urlStr;
    public String content_raw;
    //---test--
    private TextProcess tProcess;
    //---------
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    /*
     * goal: 獲得網頁內容並return
     * output: 輸出urlStr抓下來的結果，用UTF-8編碼，存在retVal裡並輸出
     */
    public String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-agent", "Mozilla/5.0 Chrome/8.0 Safari/537.36");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line; //試試看不要換行會不換比較好
		}
		//------test-------
//		Document doc = Jsoup.parse(retVal); 
//		System.out.println(doc.text());
		//------test-------
//		return retVal.replaceAll("[A-Za-z0-9]+\\\\p{Punct}\\\\s*|\\t|\\r|\\n","");
		return retVal.replaceAll("[A-Za-z0-9]+","").replaceAll("\\p{Punct}","").replaceAll("\\s*|\t|\r|\n", "");
//		return retVal;
    }
    /*
     * goal: 獲得輸入keyword出現的次數，一次可以count一個keyword
     * intput: input 要被count的keyword
     * output: output keyword出現的次數
     */
    public double countKeyword(String keyword) throws IOException{
		if (content_raw == null){
		    content_raw = fetchContent();
		}
		//--test--
//		System.out.println(content_raw);
//		tProcess = new TextProcess(content_raw);
//		tProcess.Process();
		//--------
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content_raw = content_raw.toUpperCase();
		keyword = keyword.toUpperCase();
		int retVal = 0; 
		int pos;
		while(content_raw.indexOf(keyword)!=-1) {
			retVal++;
			pos=content_raw.indexOf(keyword);
			content_raw = content_raw.substring(pos+keyword.length(), content_raw.length());
		}
		return retVal;
    }
}