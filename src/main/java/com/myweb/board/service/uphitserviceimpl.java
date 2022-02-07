package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class uphitserviceimpl implements boardservice{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		//쿠키검사
		Cookie[] cookies = request.getCookies();
		
		boolean flag = true;
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals(bno)) {
					flag = false;
					break;
				}
			}
			
		}
		if(flag) {
			BoardDAO dao = BoardDAO.getInstance();
			dao.uphit(bno);
		}
		//쿠키문법
		//쿠키생성
		//시간설정
		//쿠키전송
		
		Cookie cookie = new Cookie(bno, bno); //(이름,값)
		cookie.setMaxAge(20);
		response.addCookie(cookie);
		
		
		
	}
	
}
