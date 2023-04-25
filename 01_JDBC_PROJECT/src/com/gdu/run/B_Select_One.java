package com.gdu.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.gdu.model.vo.Test;

public class B_Select_One {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rset = null;
		
		Test t = null;

		Scanner sc = new Scanner(System.in);
		System.out.print("조회하고자하는 번호 입력: ");
		int no = sc.nextInt();
		
		String sql = "select tno,tname,tdate from test where tno ="+ no;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				t = new Test(rset.getInt("tno"),rset.getString("tname"),rset.getDate("tdate") );
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
		
		if(t == null) {
			System.out.println("조회된 데이터가 없습니다.");
		}else {
			System.out.println(t);
		}
		
	}
}
