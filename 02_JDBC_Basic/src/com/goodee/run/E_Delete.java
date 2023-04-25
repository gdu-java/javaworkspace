package com.goodee.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class E_Delete {

	public static void main(String[] args) {
		// CRUD(CREATE(INSERT), READ(SELECT), UPDATE, DELETE)
		
		// 사용자가 입력한 번호에 해당하는 데이터 삭제
		int result = 0;
		
		Connection conn = null;
		Statement stmt  = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제하고자 하는 번호 : ");
		
		//nextInt()는 엔터가 남아있음. 이대로 사용할 경우 다음 입력값을 받으려고 할때 엔터가 실행됨. 
		//따라서 입력하지않은 상태에서 다음으로 넘어가 됨. 
		//이 경우 nextLine()을 한번더 실행해서 엔터를 제거해주면 됨.
		int no = sc.nextInt();

		String sql = "DELETE FROM TEST "
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
			System.out.println("성공적으로 삭제되었습니다.");
		}else {
			System.out.println("삭제하는데 실패했습니다.");
		}
	}

}
