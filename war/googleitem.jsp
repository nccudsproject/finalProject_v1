<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
<%
String[][] orderList = (String[][])  request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='http://www.google.com.tw<%= orderList[i][1] %>'><%= orderList[i][0] %></a>
	<br>
	<h style="font-size:10px ;">http://www.google.com.tw<%=orderList[i][1]%></h>
	<%-- <h4 style="font-size:10px ; color:red;">Score:<%= request.getAttribute("score") %></h4> --%>
	<br><br>
<%
}
%>
</body>
</html>