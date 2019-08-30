<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL core 태그 사용을 위한 지시자 선언 --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색한 회원정보 출력창</title>
</head>
<body>
    
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이름</b></td>
			<td><b>이메일</b></td>
			<td><b>가입일</b></td>
		</tr>
	<c:forEach var="member" items="${requestScope.membersList}">
		<tr align="center">
			<td><b>${member.id}</b></td>
			<td><b>${member.pwd}</b></td>
			<td><b>${member.name}</b></td>
			<td><b>${member.email}</b></td>
			<td><b>${member.joinDate}</b></td>
		</tr>		
	</c:forEach>
	</table>

<!-- 컨텍스트 패스 주소 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<a href="${contextPath}/member/memberForm.do">   
	<h1 style="text-align: center;">회원가입</h1>  
</a>


</body>
</html>









