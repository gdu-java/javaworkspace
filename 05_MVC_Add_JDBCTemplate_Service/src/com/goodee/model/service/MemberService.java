package com.goodee.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.goodee.common.JDBCTemplate;
import com.goodee.model.dao.MemberDao;
import com.goodee.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		// 1. JDBC 드라이버 등록
		// 2. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn,m);
		
		// 트랜잭션 처리
		if(result > 0) { //성공
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectList(){
		Connection conn = JDBCTemplate.getConnection();
		
		//MemberDao클래스의 selectList메서드 호출. 호출시 conn 객체 전달.
		//selectList의 조회결과가 ArrayList로 반환됨.
		//반환된 list를 MemberController의 selectList 메서드로 반환
		ArrayList<Member> list = new MemberDao().selectList(conn);

		JDBCTemplate.close(conn);
		return list;
	}
	
	public Member  selectByUserId(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().selectByUserId(conn,userId);
		JDBCTemplate.close(conn);
		return m;
	}
	
}
