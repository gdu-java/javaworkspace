package com.goodee.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class C_Insert {

	public static void main(String[] args) {
		// INSERT문 => 반환값이 처리된 행 수(int) = > 트랜잭션 처리(commit 또는 rollback)해야함.
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt  = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("추가하고자 하는 이름 : ");
		
		String name = sc.nextLine();
		
		String sql = "INSERT INTO TEST VALUES (SEQ_TESTNO.NEXTVAL, '" + name + "' ,SYSDATE)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			
			//SELECT => executeQuery(),  INSERT,UPDATE,DELETE => executeUpdate()
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {  //성공
				conn.commit();
			}else {           //실패
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
		
		if(result > 0) {
			System.out.println("성공적으로 추가되었습니다.");
		}else {
			System.out.println("추가하는데 실패했습니다.");
		}
	}

}
