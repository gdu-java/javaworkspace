package com.goodee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.goodee.model.vo.Member;

public class MemberDao {

/*
 * DAO(Data Access Object)
 * : DB에 직접 접근해서 사용자의 요청에 맞는 SQL문을 실행한 후 결과를 받음.(JDBC이용)
 *   결과를 Controller로 반환함.
 * 	
 */
	
	public int insertMember(Member m) {
		
		int result = 0;
		
		Connection conn = null;
		Statement  stmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL, '"+ m.getUserId()  + "', "
															  + "'"+ m.getUserPwd() + "', "
															  + "'"+ m.getUserName() + "', "
															  + "'"+ m.getGender() + "', "
															  	   + m.getAge() + ", "
															  + "'"+ m.getEmail() + "', "
															  + "'"+ m.getPhone() + "', "
															  + "'"+ m.getAddress() + "', "
															  + "'"+ m.getHobby() + "', SYSDATE)";
						
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {  // 성공
				conn.commit();
			}else {           // 실패
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 전체 회원을 조회하는 메서드
	public ArrayList<Member> selectList() {
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM MEMBER";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setUserNo(rset.getInt("user_no"));  
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	// 회원 아이디로 검색 요청시 조회하는 메서드
	public Member selectByUserId(String userId) {
		Member m = null;
		
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USER_ID = '" + userId + "'";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setUserNo(rset.getInt("user_no"));  
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return m;
	}	
	
	public ArrayList<Member> selectByUserName(String userName) {
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USER_NAME like '%" + userName + "%'";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setUserNo(rset.getInt("user_no"));  
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
				
		return list;
	}
	
	// Controller에서 요청하는 회원정보 변경을 수행하는 메서드
	public int updateMember(Member m) {
		int result = 0;

		Connection conn = null;
		Statement  stmt = null;
		
		String sql = "UPDATE MEMBER "
				+       "SET USER_PWD = '" + m.getUserPwd() +"',"
				+       "    EMAIL = '" + m.getEmail() +"',"
				+       "    PHONE = '" + m.getPhone() +"',"
				+       "    ADDRESS = '" + m.getAddress() +"'"
				+     "WHERE USER_ID = '" + m.getUserId() +"'";
		
		System.out.println(sql);
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {  // 성공
				conn.commit();
			}else {           // 실패
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
