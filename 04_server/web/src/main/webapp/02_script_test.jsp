<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h1>Scripting Tag</h1>
	<hr>
	
	<%
		int count = 5;
		out.println(myMethod(0));
		out.println(count);
	%>
	<%!
		public int myMethod(int count){
			return ++count;
		}
	%>
	<%
		out.println(count);
	%>
	
</body>
</html>