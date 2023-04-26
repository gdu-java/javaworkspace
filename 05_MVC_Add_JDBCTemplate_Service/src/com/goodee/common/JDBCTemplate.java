package com.goodee.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	// 모든 메서드를 static 으로 생성
	
	// 1. Connection 객체 생성한 후 Connection 객체를 반환해주는 메서드 	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 데이터베이스에 연결된 Connection 객체를 반환한다. 이때 conn.close()를 사용하면 안됨.
		return conn;
	}
	
	//2. commit을 처리해 주는 메서드(Connection객체 전달받아야함.)
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//3. rollback을 처리해주는 메서드(Connection객체 전달받아야함.)
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//4. JDBC용 객체들 전달받아서 반납처리해주는 메서드
	//1) Statement관련 객체를 전달받아서 반납시켜주는 메서드
	//   Statement와 PreparedStatement는 둘 다 Statement로 받을 수 있음.
	public static void close(Statement stmt) { 
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//2) ResultSet객체 전달받아서 반납해주는 메서드 :  메서드 오버로딩
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//3) Connection객체 전달받아서 반납해주는 메서드 : 메서드 오버로딩
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
}
