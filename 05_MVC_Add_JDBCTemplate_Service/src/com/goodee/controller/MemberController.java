package com.goodee.controller;

import java.util.ArrayList;

import com.goodee.model.dao.MemberDao;
import com.goodee.model.service.MemberService;
import com.goodee.model.vo.Member;
import com.goodee.view.MainMenu;

public class MemberController {

/*
 * Controller : view를 통해서 사용자가 요청한 기능에 대해 처리를 담당.
 *              해당 메소드로 전달된 데이터를 [가공처리한 후] DAO 메소드 호출 전달.
 *              반환된 데이터를 전달할 응답화면 결정(view 호출) 
 */

	/*
	 * 1. 회원 추가 요청을 처리하는 메서드
	 */
	public void insertMember(String userId,String userPwd,String userName,
			                 String gender,String age,String email,
			                 String phone, String address,String hobby) {
		
		Member m = new Member(userId,userPwd,userName,gender,Integer.parseInt(age)
				                ,email,phone,address,hobby);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("성공적으로 회원 추가되었습니다.");
		}else {          //실패
			new MainMenu().displayFail("회원 추가에 실패했습니다.");
		}
	}
	/*
	 * 2. 회원전체를 조회요청을 처리하는 메서드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
		
		if(list.isEmpty()) { // 리스트가 비어있을 경우 => 조회된 결과 없음.
			new MainMenu().displayNoData("조회 결과 데이터가 없습니다.");
		}else {
			new MainMenu().displayMemberList(list);
		}
		
	}
	
	// 3. 회원 아이디로 검색 요청을 처리하는 메서드
	// @param : userId : 사용자가 입력해서 검색하고자 하는 회원 아이디값
	public void selectById(String userId) {
		Member m = new MemberService().selectByUserId(userId);
		
		if(m == null) {
			new MainMenu().displayNoData(userId + "에 대한 조회결과가 없습니다.");
		}else {
			new MainMenu().displayMember(m);
		}
	}
	
	// 키워드로 회원 아이디 또는 회원이름을 검색하고자 하는 요청을 처리하는 메서드
	public void selectByName(String keyword) {
		ArrayList<Member> list = new MemberService().selectByUserKeyword(keyword);
		
		
		if(list.isEmpty()) {
			new MainMenu().displayNoData(keyword+"라는 키워드에 해당하는 회원이 없습니다.");
		}else {
			new MainMenu().displayMemberList(list);
		}
	}
	
	// 회원 정보 변경 요청을 처리할 메서드 
	// @param : userId, userPwd, email, phone, address
	public void updateMember(String userId,String userPwd,String email,String phone,String address) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result = new MemberService().updateMember(m);
		
		if(result > 0 ) { //성공
			new MainMenu().displaySuccess("성공적으로 변경되었습니다.");
		}else { //실패
			new MainMenu().displayFail("회원정보 변경에 실패했습니다.");
		}
	}
	
	//회원 탈퇴 요청을 처리하는 메서드
	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("회원정보를 성공적으로 삭제했습니다.");
		}else {
			new MainMenu().displayFail("회원정보를 삭제하는데 실패했습니다.");
		}
	}
	
	public void loginMember(String userId, String userPwd) {
		Member m = new MemberService().loginMember(userId,userPwd);
		
		if(m == null) {
			new MainMenu().displayFail("로그인 실패");
		}else {
			new MainMenu().displaySuccess(m.getUserName()+"님, 환영합니다.");
		}
	}
}
