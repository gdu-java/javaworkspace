package com.goodee.run;

import com.goodee.view.MainMenu;

public class Run {

	public static void main(String[] args) {

		/*
		 * MVC패턴  : 프로그램 디자인패턴 중 하나. model1(View,Controller를 한군데서 처리),
		 *                               model2(MVC 각 기능을 분리)가 있음.
		 * 
		 * M(Model) : 데이터 처리 담당.(데이터들을 담기 위한 클래스(vo), DB에 직접 접근해서 데이터를 처리하는 클래스(dao)
		 * V(View)  : 화면을 담당.(사용자가 보게되는 시각적인 요소, 출력 및 입력)
		 * C(Controller): 사용자의 요청 처리를 담당.  
		 * 
		 */
		
		new MainMenu().mainMenu();
		
		
	}

}
