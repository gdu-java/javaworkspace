package com.goodee.view;


import java.util.ArrayList;
import java.util.Scanner;

import com.goodee.controller.MemberController;
import com.goodee.model.vo.Member;


public class MainMenu {

	// view : 사용자가 보게 될 시각적인 요소(화면). 입력(Scanner) 및 출력  => Console이 담당
	//        웹애플리케이션에서는 웹브라우저에서 입력, 출력을 한다.           

	//Scanner 객체 생성
	private Scanner sc = new Scanner(System.in);
	
	//MemberController 객체 생성 :  사용자의 요청을 받은 객체
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n==회원 관리 프로그램==");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 키워드 검색(회원아이디 또는 회원이름)");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 로그인");
			System.out.println("8. 인적사항 조회");
			System.out.println("0. 프로그램 종료");
			
			System.out.println(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: insertMember(); break;   // 현재 클래스에서 처리
			case 2: mc.selectList(); break;     // Controll 패키지에 있는 클래스에 처리
			case 3: mc.selectById(inputMemberId()); break;     // Controll 패키지에 있는 클래스에 처리
			case 4: mc.selectByName(inputMemberName()); break;   // Controll 패키지에 있는 클래스에 처리
			case 5: updateMember(); break;   // 현재 클래스에서 처리 
			case 6: mc.deleteMember(inputMemberId()); break;   // Controll 패키지에 있는 클래스에 처리
			case 7: loginMember(); break;  // 회원 아이디와 비밀번호를 입력받는 메서드 호출
			case 8: selectProfile(); break; // 회원 인적정보 조회를 위해 아이디와 패스워드를 입력받는 메서드 호출
			case 0: System.out.println("이용해 주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 잘못입력했습니다. 다시 입력해주세요.");
			}
		}
	}
	//1번 선택시 보이는 회원추가 창(서브화면)
	public void insertMember() {
		
		System.out.println("\n== 회원 추가 ==");
		
		//아이디 ~ 취미까지 입력받기
		System.out.println("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.println("비밀번호 : ");
		String userPwd = sc.nextLine();

		System.out.println("이름 : ");
		String userName = sc.nextLine();
		
		System.out.println("성별(M/F) : ");
		String gender = sc.nextLine();
		
		System.out.println("나이 : ");
		String age = sc.nextLine();
		
		System.out.println("이메일 : ");
		String email = sc.nextLine();

		System.out.println("전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();

		System.out.println("주소 : ");
		String address = sc.nextLine();
		
		System.out.println("취미(,로 연이어서 작성) : ");
		String hobby = sc.nextLine();
		
		mc.insertMember(userId,userPwd,userName,gender,age,email,phone,address,hobby);
	}
	
	public String inputMemberId() {
		System.out.println("\n회원 아이디 입력 : ");
		return sc.nextLine();
	}
	
	public String inputMemberName() {
		System.out.println("\n회원 이름(키워드) 입력 : ");
		return sc.nextLine();
	}
	
	public void updateMember() {
		System.out.println("\n== 회원 정보 변경 ==");
		
		//회원 아이디, 변경할 비밀번호, 변경할 이메일, 변경할 전화번호, 변경할 주소
		
		String userId = inputMemberId();
		
		System.out.println("변경할 암호 : ");
		String userPwd = sc.nextLine();
		
		System.out.println("변경할 이메일 : ");
		String email = sc.nextLine();

		System.out.println("변경할 전화번호 : ");
		String phone = sc.nextLine();

		System.out.println("변경할 주소 : ");
		String address = sc.nextLine();
	
		mc.updateMember(userId,userPwd,email,phone,address);
	}
	
	public void loginMember() {
		System.out.println("\n=====로그인=====");
		String userId = inputMemberId();
		
		System.out.println("회원 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		
		mc.loginMember(userId,userPwd);
	}

	public void selectProfile() {
		System.out.println("\n=====인적사항=====");
		
		String userId = inputMemberId();
		
		System.out.println("회원 비밀번호 입력 :");
		String userPwd = sc.nextLine();
		
		mc.selectProfile(userId,userPwd);
	}
	
	//----------------------응답화면-------------------------------------
	// 서비스 요청 처리후 성공했을 때 사용자가 보게될 화면
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	// 서비스 요청 처리 실패했을때 사용자가 보게될 화면
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	// 조회 서비스 요청 처리 후 조회결과가 없을 경우 사용자가 보게 될 화면
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	// 조회 서비스 요청 처리 후 조회결과가 여러개일 경우 사용자가 보게될 화면
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.\n");
		
		for(Member m: list) {
			System.out.println(m);
		}
	}
	
	public void displayMember(Member m) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		System.out.println(m);
	}
	
	public void displayProfile(Member m) {
		System.out.println("\n=====회원 정보=====");
		
		System.out.println("이  름 : " + m.getUserName() );
		System.out.println("이메일  : " + (m.getEmail() == null ? "없음" : m.getEmail()));
		System.out.println("전화번호 : " + m.getPhone());
		System.out.println("주  소 : " + (m.getAddress() == null ? "없음" : m.getAddress()) );
		
		if(m.getHobby() != null) {
			String[] hobby = m.getHobby().split(",");
			
			System.out.print("취  미 : ");
			for(String s : hobby) {
				System.out.print(s + " ");
			}
		}else {
			System.out.println("취  미 : 없음");
		}
	}
}
