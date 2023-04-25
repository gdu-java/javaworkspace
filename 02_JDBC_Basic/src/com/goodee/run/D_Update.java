package com.goodee.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class D_Update {

	public static void main(String[] args) {
		// 변경할 번호를 입력받고 이름을 변경하는 쿼리 수행.

		// 업데이트된 결과 받을 변수
		int result = 0;
		
		Connection conn = null;
		Statement stmt  = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("변경하고자 하는 번호 : ");
		
		//nextInt()는 엔터가 남아있음. 이대로 사용할 경우 다음 입력값을 받으려고 할때 엔터가 실행됨. 
		//따라서 입력하지않은 상태에서 다음으로 넘어가 됨. 
		//이 경우 nextLine()을 한번더 실행해서 엔터를 제거해주면 됨.
		int no = sc.nextInt();
		sc.nextLine();
		
		System.out.print("변경할 이름 : ");
		
		String cname = sc.nextLine();
		
		String sql = "UPDATE TEST "
				    +   "SET TNAME = '" + cname + "'"
				    + "WHERE TNO = " + no;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			
			//SELECT => executeQuery(),  INSERT,UPDATE,DELETE => executeUpdate()
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
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
			System.out.println("성공적으로 변경되었습니다.");
		}else {
			System.out.println("변경하는데 실패했습니다.");
		}
	}

}
