package com.spring.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//웹브라우저에서 요청시 MemberDAO객체를 생성한후 selectAllMemberList()메소드를 호출하는 서블릿입니다.
@WebServlet("/mem.do")
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
		
		List membersList = dao.selectAllMemberList(); //조회한 정보를  List에 저장합니다.
		
		//조회한 정보를  request영역에 바인딩 합니다.
		request.setAttribute("membersList", membersList);
		
		//조회한 정보를 바인딩하고 JSP페이지로 포워딩 합니다.
		RequestDispatcher dispatch = 
				request.getRequestDispatcher("test01/listMembers.jsp");
		
		dispatch.forward(request, response);
		
	}	
	
}












