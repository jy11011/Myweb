package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class registserviceimpl implements boardservice {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//controller역활분담
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//dao생성
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(writer, title, content);
	}
	
}
