package com.spring.ex04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

@WebServlet("/mem4.do")
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
			nextPage = "test03/listMembers.jsp";
			
		//검색조건이 selectMemberById이면 전송된 값을 getParameter()로 가져온 후 SQL문의 조건식에서
		//id의 조건값으로 전달 합니다.
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test03/memberInfo.jsp";
		//검색조건이 	selectMemberByPwd이면 전송된 값을 getParameter()로 가져온후  SQL문의 조건식에서 
	    //pwd의 조건값으로 전달합니다.
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
		
		
		//웹브라우저에서 전송된 action값이 insertMember면 
		//함께 전송된 입력한 회원정보를 가져와서 ...
		//MemberVO에 설정 합니다.
		//그런 다음 MemberDAO의 insertMember()메소드를 호출하면서 인자로 전달합니다.
		} else if(action.equals("insertMember")){
			
			//우리가 입력한 회원정보를 request영역에서 얻기
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//입력한 회원정보를  MemberVO에 저장
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			//회원가입창에서 전송된 회원정보를 MemberVO에 설정한후
			//MemberDAO의 insertMember()메소드로 전달합니다.
			dao.insertMember(memberVO);
			
			nextPage = "/mem4.do?action=listMembers";
		
			
		}else if(action.equals("insertMember2")){
			
			//우리가 입력한 회원정보를 request영역에서 얻기
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//입력한 회원정보를 HashMap에  key/value쌍으로 저장한 후
			//MemberDAO의 insertMember2()메소드의 인자로 전달함			
			Map memberMap = new HashMap();
			memberMap.put("id", id);
			memberMap.put("pwd", pwd);
			memberMap.put("name", name);
			memberMap.put("email", email);
			
			dao.insertMember2(memberMap);
			
			nextPage = "/mem4.do?action=listMembers";			
			
			
		}else if(action.equals("updateMember")){//수정요청이 들어오면
			
			//우리가 입력한  수정할 회원정보를 request영역에서 얻기
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//입력한 수정할 회원정보를  MemberVO에 저장
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			dao.updateMember(memberVO);
			
			nextPage = "/mem4.do?action=listMembers";	
		
		
		}else if(action.equals("deleteMember")){//회원정보 삭제 요청이 들어오면
			//삭제할 회원의 ID얻기
			String id = request.getParameter("id");
			//회원목록창에서 전달된 ID를 MemberDAO의 deleteMember()메소드를 호출하면서
			//SQL문으로 만들기 위해 삭제할 ID를 전달함
			dao.deleteMember(id);
			//회원정보 삭제후 ~ 요청할 페이지 주소 저장
			nextPage = "/mem4.do?action=listMembers";
			
			
		//searchMember.jsp페이지에서  이름과 이메일을 입력하고 
		//검색버튼을 눌러서 요청이 들어왔을때..
		}else if(action.equals("searchMember")){
			
			String name = request.getParameter("name");
			String email  = request.getParameter("email");
			
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			List membersList = dao.searchMember(memberVO);
			
			request.setAttribute("membersList", membersList);
			
			nextPage = "test03/listMembers.jsp";
			
			
			
		}else if(action.equals("foreachSelect")){
			
			//ArrayList에  검색할 이름을 저장한후 
			//SQL문을 만들기 위해 ArrayList를 전달 합니다.
			List<String> nameList = new ArrayList();
			
			nameList.add("바보");
			nameList.add("김개똥");
			nameList.add("슈퍼맨");
			
			List membersList = dao.foreachSelect(nameList);
			
			request.setAttribute("membersList", membersList);
			
			nextPage = "test03/listMembers.jsp";
			
			
		}else if(action.equals("foreachInsert")){
			//박길동, 이길동, 김길동 회원정보를 DB에 한번에 INSERT요청이 들어왔을때..
			
			List<MemberVO> memList = new ArrayList();
			
			memList.add(new MemberVO("m1","1234","박길동","m1@test.com"));
			memList.add(new MemberVO("m2","1234","이길동","m2@test.com"));
			memList.add(new MemberVO("m3","1234","김길동","m3@test.com"));
			
			int result = dao.foreachInsert(memList);
			
			nextPage = "/mem4.do?action=listMembers";
			
		}
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);  
		dispatch.forward(request, response);


	}
}






