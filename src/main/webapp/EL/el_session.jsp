<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//세션생성
	UserVO vo = new UserVO();
	vo.setId("aaa123");
	vo.setName("홍길동");
	
	session.setAttribute("vo", vo);
	session.setAttribute("auth", "y");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href = "el_session_ok.jsp">el확인하기</a>
	
	
</body>
</html>