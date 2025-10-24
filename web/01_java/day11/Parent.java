package day11;

import util.MyUtil;

public class Parent {
	String name = "Parent";
	String familyName = "Leopard";
	
	Parent(){
		MyUtil.p("Parent Created");
	}
	
	void eat() {
		MyUtil.p("나 " + name + "은 저녁 식사를 합니다.");
		MyUtil.p("빵 먹기");
		MyUtil.p("밥 먹기");
		MyUtil.p("음료수 먹기");
		MyUtil.p("연타발에서 곱창 먹기");
	}
	
	
}
