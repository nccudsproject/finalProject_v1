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
		<form action='${requestUri}' method='get'>
			<div id='searchBarStyle'>
			<input id='keyWordInput' type='text' name='keyword' placeholder = 'keyword'/>
			<input id='submitButton' type='submit' value='submit' />
			</div>
		</form>
	</div>
</body>

<style>
body,html {
  height: 100%;
  width: 100%;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
#submitButton{
	positive: relative;
	outline: none;
	width: 17%;
	height: 3em;
	border-radius: 4px;
}
#keyWordInput{
	positive: relative;
	width: 80%;
	height: 1.5em;
	padding: 0.3em;
	font-size: 20px;
}
#searchBarStyle{
	width: 50%;
	padding: 0.3em;
	border: 2px solid black;
	border-radius: 4px;
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
	width: 100%;
	display: flex;
	justify-content: center;
  	align-items: center;
}
</style>
</html>