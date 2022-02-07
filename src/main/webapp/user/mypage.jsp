<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp"%>
<%

	if(session.getAttribute("uservo") ==null){
		response.sendRedirect("login.jsp");
	}
	
	 UserVO vo =(UserVO)session.getAttribute("uservo");

%>

	<section>
		<div align = "center">
			 <h3><%=vo.getId() %>(<%=vo.getName() %>)님의 정보관리</h3>
			 
			 <a href = "update.jsp">[정보수정]</a>
			 <a href = "delete.jsp">[회원탈퇴]</a>
			 
			 
			 		
		</div>
	</section>




<%@include file ="../include/footer.jsp"%>