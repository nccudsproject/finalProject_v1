<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
<div id='searchBar'>
	<div id='title'>
    	<p class="word">UNBOX<br>THE<br>UNKNOWS</p>
    </div>
	<form action='${requestUri}' name="getkey" method='get'>
		<div id='searchBarStyle'>
       <input id='keyWordInput' type='text' name='keyword' placeholder = 'keyword'/>
       <div id='buttongroup'>  
         <input class="button" id='youtubeButton'  type='submit' name="button" value='YT' />
         <input class="button" id='youtubeButton'  type='submit' name="button" value='YT_c' />
         <input class="button" id='searchButton'  type='submit' name="button" value='YT_in' />
         <input class="button" id='searchButton'  type='submit' name="button" value='G' />
         <input class="button" id='searchButton'  type='submit' name="button" value='G_c' />
         <input class="button" id='searchButton'  type='submit' name="button" value='G_in' />
       </div>
		</div>	
	</form>
</div>
<div id="output">
<%
if(!request.getAttribute("video").equals("YT")){
String[][] orderList = (String[][])  request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='http://www.google.com.tw<%= orderList[i][1] %>'><%= orderList[i][0] %></a>
	<br>
	<h style="font-size:10px ;">http://www.google.com.tw<%=orderList[i][1]%></h>
	<%-- <h4 style="font-size:10px ; color:red;">Score:<%= request.getAttribute("score") %></h4> --%>
	<br><br>
<%
}
}
else if(request.getAttribute("video").equals("YT")){
String[][] orderList = (String[][])  request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){
	String s="";
	Pattern p = Pattern.compile("3D(.*?)&");
    Matcher m = p.matcher(orderList[i][1]);
    if(m.find()) s=m.group(1);%>
	<a href="<%= orderList[i][1].substring(7).replace("%3Fv%3D", "?v=") %>"><%= orderList[i][0] %></a>
	<%-- <iframe width="640" height="360" src="<%= orderList[i][1].substring(7).replace("watch%3Fv%3D", "embed/") %>" frameborder="0" allowfullscreen></iframe>--%>
	<%-- <video src="<%= orderList[i][1].substring(7).replace("%3Fv%3D", "?v=") %>" controls><%= orderList[i][0] %></video>--%>
	<%--<img alt="" src="https://img.youtube.com/vi/<%= orderList[i][1].substring(7).replace("%3Fv%3D", "?v=") %>/hqdefault.jpg">--%>
	<br>
	<img alt="" src="https://img.youtube.com/vi/<%=s%>/hqdefault.jpg" height=150em width=190em>
	<br>
	<br>
<%	
}
}
%>
</div>
</body>
</html>

<style>
body,html {
  height: 100%;
  width: 100%;
  margin: 1em;
}
#title{
  z-index:1;
  padding: 0;
  margin-left:0;
/*   border: 2px solid white; */
}
.word{
  display: inline;
  z-index:1;
  color: black;
  font-size: 2em;
  font-weight: bold;
  font-family: "Lucida Console", "Courier New", monospace;
  line-height: 1.5em;
  letter-spacing: 0.3em;
}
.button{
	positive: relative;
  opacity: 1;
  background-color: white;
	outline: none;
	width: 15%;
	height: 3em;
  border: 2px solid pink; 
	border-radius: 4px;
  transition: all .2s ease;
}
.button:hover{
  opacity: 1;
  box-shadow: inset 0 0 0 3px #fa0;
}
#keyWordInput{
  border: 2px solid black;
  background-color: ;
  border-radius: 4px;
  outline: none;
/* 	positive: relative; */
	width: 80%;
	height: 2.5em;
	padding: 0.3em;
  margin: 1em;
/*   margin-right: 2em; */
	font-size: 20px;
}
#searchBarStyle{
  justify-content: center;
  align-item: center;
  text-align: center;
	width: 100%;
	padding: 0.2em;
/* 	border: 1px solid black; */
/* 	border-radius: 4px; */
}
input {
	border: none;
}
form {
	width: 70%;
	display: flex;
	justify-content: center;
}
#searchBar{
	background-color: orange;
	border-radius: 4px;
  z-index:1;
	width: 90%;
	display: flex;
	justify-content: center;
  align-items: center;
  margin:1em;
}
#buttongroup{
  display: inline-block;
  width: 100%;
/*   border: 1px solid black; */
}
#output{
	background-color: white;
	border: 3px solid black;
	width:90%;
	padding: 2em;
	border-radius:4px;
	margin:0;	
}
</style>