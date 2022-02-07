package com.myweb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.jdbcutil;

public class UserDAO {
	/*
	 * 1.싱글톤패턴을 이용한 클래스
	 * 스스로 객체를 생성하고 1개로 제한
	 */
	
	private static UserDAO dao = new UserDAO();
	
	//2.생성자에 private를 붙힌다.
	private UserDAO() {
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//커넥션풀 정보
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3.외부에서 객체생성을 요구할때 멤버변수 dao를 반환한다
	
	public static UserDAO getInstance() {
		
		return dao;
	}
	
	//데이터베이스 연결주소 멤버변수로 선언
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";
	
	DataSource ds; //연결정보 (풀정보) 저장
	
	//아이디 중복검사메서드
	
	public int idcheck(String id) {
		
		int result = 0;
		
		String sql = "select * from users where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			//conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcutil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//회원가입
	public int insert(UserVO vo) {
		int result = 0;
		
		String sql = "insert into users (id,pw,name,email,address)"
				+ "				  values(?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn=DriverManager.getConnection(url,uid,upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcutil.close(conn, pstmt, null);
		}
		
		
		
		return result;
	}
	
	
	
	public UserVO login(String id,String pw) {
		
		UserVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from users where id = ? and pw = ?";
		
		try {
			
			conn = DriverManager.getConnection(url,uid,upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String ids =rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				vo = new UserVO(ids,null,name,email,address,null);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcutil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	public int update(UserVO vo) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update users set pw= ? ,name =? ,email = ?,address = ? where id = ?";
		
		try {
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcutil.close(conn, pstmt, null);
		}
			
		return result;
	}
	
	public int delete(String id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from users where id = ?";
		
		try {
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcutil.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	
	
	
	
	
}
