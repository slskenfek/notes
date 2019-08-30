package com.spring.ex03;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

//서블릿에서는 브라우저의 요청에 대해 MemberDAO클래스의 메소드를 호출한 후 그결과를 브라우저로 출력합니다.
@WebServlet("/mem3.do")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		String action = request.getParameter("action");
		String nextPage = "";

		if (action== null || action.equals("listMembers")) {
			List membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
			
		//검색조건이 selectMemberById이면 전송된 값을 getParameter()로 가져온 후 SQL문의 조건식에서
		//id의 조건값으로 전달 합니다.
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test02/memberInfo.jsp";
		//검색조건이 	selectMemberByPwd이면 전송된 값을 getParameter()로 가져온후  SQL문의 조건식에서 
	    //pwd의 조건값으로 전달합니다.
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
		}
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);  
		dispatch.forward(request, response);


	}
}
