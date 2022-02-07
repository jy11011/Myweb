package com.mywep.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserVO;


@WebFilter({"/board/writer.board","/board/regist.board"})
public class BoardFilter01 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//세션정보를 얻어서 확인한다
		//부모를 자식으로 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//
		HttpSession session = req.getSession();
		UserVO vo =(UserVO)session.getAttribute("uservo");
		
		if(vo == null) { //로그인이 만료된 회원
			
			//res.sendRedirect("/Myweb/user/login.jsp");
			
			//out객체
			res.setContentType("text/html; charset=UTF-8;");
			
			PrintWriter out =res.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스 입니다')");
			out.println("location.href = '/Myweb/user/login.jsp';");
			out.println("</script>");
			
			return; //컨트롤러를 실행하지 않는다
			
		}
		
		
		chain.doFilter(request, response); //다음필터 or 컨트롤러의 연결
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
