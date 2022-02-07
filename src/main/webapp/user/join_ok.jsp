<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//값을 받고, 화면에 대한처리
	
	request.setCharacterEncoding("utf-8");
	String id  = request.getParameter("id");
	String pw  = request.getParameter("pw");
	String name  = request.getParameter("name");
	String email  = request.getParameter("email");
	String address  = request.getParameter("address");
	
	//중복검사
	UserDAO dao = UserDAO.getInstance();
	int result = dao.idcheck(id);
	
	if(result ==1){
%>
	
	<script>
		alert("중복된 아이디 입니다"); //경고창
		history.go(-1); //뒤로가기
	</script>
	
<%
	}else{
			
		//insert
		UserVO vo = new UserVO(id,pw,name,email,address,null);
		int result2 =dao.insert(vo);
		
		if(result2 ==1){
%>
	<script>
		alert("회원가입을 축하합니다");
		location.href = "login.jsp"; //js에서의 화면이동
	</script>

<%			
		}else{
%>
	<script>
		alert("회원가입에 실패했습니다");
		history.go(-1);
	</script>

<%			
		}
	}
%>


