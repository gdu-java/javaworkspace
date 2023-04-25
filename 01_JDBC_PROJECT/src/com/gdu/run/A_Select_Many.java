package com.gdu.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gdu.model.vo.Test;

public class A_Select_Many {

	public static void main(String[] args) {
		
		ArrayList<Test> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "select tno,tname,tdate from test";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Test t = new Test();
				
				t.setTestNo(rset.getInt("tno"));
				t.setTestName(rset.getString("tname"));
				t.setTestDate(rset.getDate("tdate"));
				list.add(t);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
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
		
		if(list.isEmpty()) {
			System.out.println("조회할 데이터가 없습니다.");
		}else {
			for(int i=0; i<list.size();i++) {
				System.out.println(list.get(i));
			}
		}
	}
}
