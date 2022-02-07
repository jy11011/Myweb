<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	//1.
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");

	//2. DAO에 update()메서드를 생성하고,업데이트 구문을 수행
	//성공실패 여부를 1or0으로 리턴
	//3.수정성공시 script태그를 이용해서 "회원정보가 수정되었습니다" 출력후 마이페이지로 이동
	//수정실패시에는 "회원정보 수정에 실패했습니다" 출력후 마이페이지로 이동
	
	
	UserDAO dao = UserDAO.getInstance();
	UserVO vo = new UserVO(id,pw,name,email,address,null);
	
	int result = dao.update(vo);
	
	if(result ==1){
		//세션정보수정
		session.setAttribute("uservo", vo );
%>		
	<script>
		alert("회원정보가 수정되었습니다.");
		location.href="mypage.jsp";
	</script>


<%	
	}else {
%>
		<script>
			alert("회원정보 수정에 실패했습니다");
			location.href="mypage.jsp";
		</script>

<%	
		}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>