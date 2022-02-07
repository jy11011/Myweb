package com.mywep.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.service.DeleteServiceImpl;
import com.myweb.board.service.UpdateServiceImpl;
import com.myweb.board.service.boardservice;
import com.myweb.board.service.getcontentserviceimpl;
import com.myweb.board.service.getlistserviceimpl;
import com.myweb.board.service.registserviceimpl;
import com.myweb.board.service.uphitserviceimpl;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//3.요청핸들링
		String uri = request.getRequestURI();
		String path = request.getContextPath(); //myweb
		
		String command = uri.substring(path.length());
		System.out.println(command);
		
		
		//MVC2방식에서는 페이지이동은 기본적으로 포워드 형식을사용한다
		//request는 다시 컨트롤러를 태울대 사용한다
		
		//부모인터페이스 선언
		boardservice service;
		
		if(command.equals("/board/list.board")) { //3.목록진입(데이터 조회)
			
			service = new getlistserviceimpl();
			service.execute(request, response);
			
			//포워드 이동
			request.getRequestDispatcher("board_list.jsp").forward(request, response);

			//response.sendRedirect("board_list.jsp"); //나가는경로
		
		}else if(command.equals("/board/writer.board")) { //1.글쓰기 화면
			
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
			
		}else if(command.equals("/board/regist.board")) { //2.글 등록
			//등록 영역
			service = new registserviceimpl();
			service.execute(request, response);
			
			
			response.sendRedirect("list.board"); //다시 컨트롤러를 태워 나간다
			
		}else if(command.equals("/board/content.board")) { //4.상세내용(화면에서 bno넘겨준다)
			
			//조회수 증가
			service = new uphitserviceimpl();
			service.execute(request, response);
			
			
			//상세보기
			service = new getcontentserviceimpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("board_content.jsp").forward(request, response);
		
		}else if(command.equals("/board/modify.board")) { //5.수정화면
			//상세보기
			service = new getcontentserviceimpl();
			service.execute(request, response);
			
			//포워드
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);
			
		}else if(command.equals("/board/update.board")) { //6.글수정
			/*
			 * 1. UpdateServiceImpl() 을 생성하고 execute()메서드 실행.
			 * 2. 서비스에서 bno, title, content를 받아서 DAO의 update() 메서드로 실행.
			 * 3. update()는 sql문으로 수정을 진행.
			 * 4. 컨트롤러에서는 페이지 이동을 content화면으로 이동.
			 * 
			 */
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("content.board?bno="+ request.getParameter("bno"));
			
			
		}else if(command.equals("/board/delete.board")) { //7.삭제
			
			/*
			 * 1. 화면에 삭제 버튼에서는 delete.board요청으로 필요한 값을 넘겨줍니다.
			 * 2. DeleteServiceImpl() 생성하고 dao의 delete()메서드로 실행
			 * 3. 삭제 진행후에 목록페이지로 이동.
			 */
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
			
		}
		
		
		
		
	}
	
	
}
