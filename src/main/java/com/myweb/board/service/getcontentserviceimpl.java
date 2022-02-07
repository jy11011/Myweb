package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class getcontentserviceimpl implements boardservice {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//param을 받는다(bno)
		String bno = request.getParameter("bno");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getcontent(bno);
		
		//request에 vo를 담는다
		request.setAttribute("vo", vo);
		
	}
	
}
