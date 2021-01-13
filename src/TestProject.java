

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
//		String btnType = request.getParameter("button");
//		System.out.println(btnType);

		if(request.getParameter("button").equals("YT")) {
			System.out.println("youtube search");
			QueryAnalysis analysis = new QueryAnalysis(new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+"),0)); //已經query()好將title+urlstring放入Google_v2的urlList裡面
//		GoogleQ_v2 google = new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+")); //空格用+補起來google才可以search
			ArrayList<UrlInf> query = analysis.getGoogleQuery().getUrls();
			String[][] s = new String[query.size()][2];
			request.setAttribute("query", s);
			request.setAttribute("video", "YT");
			int num = 0;	
			for(UrlInf url:query) {
				String key = url.getTitle();
				String value = url.getUrl();
				System.err.println(key);
				s[num][0] = key;
				s[num][1] = value;
				num++;
			}			
		}
		else if(request.getParameter("button").equals("YT_c")) {
			System.out.println("youtube search customize");
			QueryAnalysis_wordcounter analysis = new QueryAnalysis_wordcounter(new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+"), 0)); //已經query()好將title+urlstring放入Google_v2的urlList裡面
//		GoogleQ_v2 google = new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+")); //空格用+補起來google才可以search
			ArrayList<UrlInf> query = analysis.getGoogleQuery().getUrls();
			String[][] s = new String[query.size()][2];
			request.setAttribute("video", "YT");
			request.setAttribute("query", s);
			int num = 0;	
			for(UrlInf url:query) {
				String key = url.getTitle();
				String value = url.getUrl();
				System.err.println(key);
				s[num][0] = key;
				s[num][1] = value;
				num++;
			}			
		}
		else if(request.getParameter("button").equals("YT_in")) {
			System.out.println("youtube search customize");
			QueryAnalysis analysis = new QueryAnalysis(new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+"), 2)); //已經query()好將title+urlstring放入Google_v2的urlList裡面
//		GoogleQ_v2 google = new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+")); //空格用+補起來google才可以search
			ArrayList<UrlInf> query = analysis.getGoogleQuery().getUrls();
			String[][] s = new String[query.size()][2];
			request.setAttribute("query", s);
			request.setAttribute("video", "YT");
			int num = 0;	
			for(UrlInf url:query) {
				String key = url.getTitle();
				String value = url.getUrl();
				System.err.println(key);
				s[num][0] = key;
				s[num][1] = value;
				num++;
			}			
		}
		else if(request.getParameter("button").equals("G")) {
			System.out.println("youtube search");
			QueryAnalysis analysis = new QueryAnalysis(new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+"),1)); //已經query()好將title+urlstring放入Google_v2的urlList裡面
//		GoogleQ_v2 google = new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+")); //空格用+補起來google才可以search
			ArrayList<UrlInf> query = analysis.getGoogleQuery().getUrls();
			String[][] s = new String[query.size()][2];
			request.setAttribute("query", s);
			request.setAttribute("video", "G");
			int num = 0;	
			for(UrlInf url:query) {
				String key = url.getTitle();
				String value = url.getUrl();
				System.err.println(key);
				s[num][0] = key;
				s[num][1] = value;
				num++;
			}			
		}
		else if(request.getParameter("button").equals("G_c")) {
			System.out.println("google search");
			QueryAnalysis_wordcounter analysis = new QueryAnalysis_wordcounter(new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+"), 1)); 
			ArrayList<UrlInf> query = analysis.getGoogleQuery().getUrls();
			String[][] s = new String[query.size()][2];
			request.setAttribute("query", s);
			request.setAttribute("video", "G");
			int num = 0;	
			for(UrlInf url:query) {
				String key = url.getTitle();
				String value = url.getUrl();
				System.err.println(key);
				s[num][0] = key;
				s[num][1] = value;
				num++;
			}		
		}
		else if(request.getParameter("button").equals("G_in")) {
			System.out.println("youtube search customize");
			QueryAnalysis analysis = new QueryAnalysis(new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+"), 3)); //已經query()好將title+urlstring放入Google_v2的urlList裡面
//		GoogleQ_v2 google = new GoogleQ_v2(request.getParameter("keyword").replace(" ", "+")); //空格用+補起來google才可以search
			ArrayList<UrlInf> query = analysis.getGoogleQuery().getUrls();
			String[][] s = new String[query.size()][2];
			request.setAttribute("query", s);
			request.setAttribute("video", "G");
			int num = 0;	
			for(UrlInf url:query) {
				String key = url.getTitle();
				String value = url.getUrl();
				System.err.println(key);
				s[num][0] = key;
				s[num][1] = value;
				num++;
			}			
		}
		request.getRequestDispatcher("googleitem.jsp").forward(request, response); 
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
