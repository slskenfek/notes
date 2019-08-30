package com.spring.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//웹브라우저에서 요청시 MemberDAO객체를 생성한후 
//MemberDAO의  selectName()메소드와
//selectPwd()메소드를 호출한후 각 데이터를 웹브라우저에 알림창으로 출력하는 서블릿
@WebServlet("/mem2.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			              HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, 
            				HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		MemberDAO dao  = new MemberDAO();//DB관련 작업을 위한 객체 생성
		 
	  //String name = dao.selectName(); //MemberDAO의 selectName()메소드 호출
		int pwd = dao.selectPwd();  //MemberDAO의 selectPwd()메소드 호출
		
		
		PrintWriter out = response.getWriter();
		out.write("<script>");
	//	out.write("alert(' 이름:" + name + "')");
		out.write("alert(' 비밀번호:" + pwd + "')");
		out.write("</script>");
		
	}	
	
}












