<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Script Example</title>
</head>
<body>
	<h1>Scripting Tag - Current Time</h1>
	<h2>Java가 기본으로 제공하는 클래스 이용하기</h2>
	<hr>
	<%
		java.util.Date date = new java.util.Date();
	%>
	<p>Today : <%=date%></p>
	<p>Today : <%=date.toString() %></p>
	<p>Today : <%=new java.util.Date() %></p>
	<!-- SimpleDateFormat을 이용하여 원하는 형태로 Date 표시가 가능 -->
</body>
</html>