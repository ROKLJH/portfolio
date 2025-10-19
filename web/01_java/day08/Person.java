package day08;

import util.MyUtil;

public class Person {
	
	// Field
	// Instance 변수
	public int age;
	String name;
	
	// Class 변수(static)
	public static String place = "The Earth";
	public static int numOfMan = 0;
	
	// Constructor
	// 여러 타입으로 만들 수 있다.
	Person(){
		MyUtil.p("인간이 탄생하였도다");
		numOfMan++;
	}
	
	Person(String x){
		name = x;
		MyUtil.p("인간 " + name + "이(가) 탄생하였도다");
		numOfMan++;
	}
	
	Person(String x, int n){
		name = x;
		age = n;
		MyUtil.p("인간 " + name + "(" + age + ")이(가) 탄생하였도다");
		numOfMan++;
	}
	
	public void introduce() {
		System.out.println("저 " + name + "은(는) " + age + "살입니다. 성격 안좋습니다.");
	}

}
