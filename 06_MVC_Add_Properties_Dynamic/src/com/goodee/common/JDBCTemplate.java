package com.goodee.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/*
	 * 기존 방식 : JDBC DRIVER 구문, 접속할 URL, 접속할 사용자아이디/패스워드 등을 자바코드내에 
	 *          명시적으로 작성 => 정적 코딩
	 *  > 문제점 : DBMS가 변경됐거나, 접속할 DB의 URL, 사용자아이디/패스워드가 변경될 경우 
	 *           => 자바코드를 직접 수정해야함.
	 *           => 수정된 자바코드를 반영시키고자 한다면 프로그램을 재구동해야함.
	 *           => 유지보수가 불편함.
	 *           
	 *  > 해결방안 : DB관련된 정보들을 별도로 외부파일(.properties)로 만들어서 관리
	 *  		  외부파일로부터 읽어들여서 반영시키면 됨. => 동적코딩   
	 *            => 수정할 필요가 있을때 외부파일의 내용만 수정하면됨. 즉, 프로그램을 재구동할 필요가 없음.              
	 */
	
	
	// 모든 메서드를 static 으로 생성
	// 1. Connection 객체 생성한 후 Connection 객체를 반환해주는 메서드 	
	public static Connection getConnection() {
		
		Connection conn = null;
		//Properties클래스는 '키=값'형식으로 저장된 파일의 내용을 읽어들일 수 있음.  
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/db.properties"));
			
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
					                           prop.getProperty("username"),
					                           prop.getProperty("password"));
			System.out.println(prop.getProperty("test"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
