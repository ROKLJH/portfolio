package com.korea.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Order")
public class OrderServlet extends HttpServlet {
	
	private final String RED = "\033[91m"; 
	private final String GREEN = "\033[92m"; 
	private final String END = "\033[0m";
	
	protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            	throws ServletException, IOException {
		System.out.println(RED + "[OrderServlet] Called" + END);
		
		response.setContentType("text/html; charset=UTF-8");
		
		// ramen, price, location을 입력으로 받는다면
		String food = request.getParameter("ramen");
		String loc = request.getParameter("location");
		int price = Integer.parseInt(request.getParameter("price"));
		
		System.out.println(food + ":" + loc + ":" + price);
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>손님께서 주문하신 내역</h1><hr>");
		out.println("<h2> 라면 : " + food);
		
		if(loc.equals("니네집")) {
			System.out.println("니네집");
			out.println("<h2>니네집인데 공짜지</h2>");
		}
		else if(loc.equals("융프라우")) {
			out.println("<h2>가격은 " + price*10 + "원입니다.</h2>");
		}
		else {
			out.println("<h2>안팔아</h2>");
		}
		
		out.println("</body></html>");
	}
}
