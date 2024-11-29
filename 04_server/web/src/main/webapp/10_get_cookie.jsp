<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display & Modify Cookie</title>
</head>
<%
	String id = "";
	String cookie_check = request.getHeader("Cookie");
	System.out.println("Cookie Check Result : " + cookie_check);
	
	if(cookie_check != null){
		System.out.println("[GetCookie] Cookie(s) found");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			System.out.println("[GetCookie] " + cookie.getName() + ":" + cookie.getValue());
			if(cookie.getName().equals("id2")){
				id = cookie.getValue();
				break;
			}
		}	
	}
%>
<body>
	<h1>Get Cookie</h1>
	<hr>
	<h1>User ID : <%=id%></h1>
	<a href="10_delete_cookie.jsp">Delete Cookie</a>
</body>
</html>