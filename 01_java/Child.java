package day11;

import util.MyUtil;

public class Child extends Parent {
	String name = "Child";
	
	Child(){
		MyUtil.p("Child Created");
	}
	
	void print() {
		String name = "Super Child";
		MyUtil.p("name = " + name);
		MyUtil.p("this.name = " + this.name);
		MyUtil.p("familyName = " + familyName);
		MyUtil.p("super.name = " + super.name);	
	}
	
	@Override
	void eat() {
		super.eat();
		MyUtil.p("나 " + this.name + "은 저녁 식사를 합니다.");
		MyUtil.p("아이스크림 먹기");
		MyUtil.p("불량식품 아폴론 먹기");
	}
}
