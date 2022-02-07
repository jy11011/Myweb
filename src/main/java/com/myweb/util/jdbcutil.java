package com.myweb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class jdbcutil {
	
	//closs작업르 대신처리
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(conn!=null)conn.close();
			if(pstmt != null)pstmt.close();
			if(rs !=null)rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
