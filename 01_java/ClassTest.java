package day09;

import util.MyUtil;

public class ClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		MyUtil.p(MyUtil.NEGATIVE + MyUtil.MAGENTA + "클래스 테스트" + MyUtil.END);
		
		Car car1 = new Car();
		MyUtil.p(car1.toString());
		
		Car car2 = new Car("Black", "MyTrip", "Luxury");
		MyUtil.p(car2.toString());
	}

}

class Car {
	String color;
	String name;
	String type;
	
	Car(){
		color = "미정";
		name = "비밀";
		type = "완전기본형";
		MyUtil.p("Car() Called");
	}
	
	Car(String color, String name, String type){
		this.color = color;
		this.name = name;
		this.type = type;
		MyUtil.p("Car(...) Called");
	}
	
	public String toString() {
		return "제 색상은 " + color + ", 이름은 " + name + ", 타입은 " + type;
	}
	
	// Code Block
	// 생성자(Constructor)가 여러 개가 있는데, 공통적으로 할 일이 있을 경우
	static {
		MyUtil.p("static {  } Called");
	}
	
	{
		MyUtil.p("{  } Called");
	}
}






