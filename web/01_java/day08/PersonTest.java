package day08;

public class PersonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person();
		System.out.println("현재까지의 인간 수 : " + p1.numOfMan);
		System.out.println("이름 : " + p1.name + " 나이 : " + p1.age);
		p1.name = "Steven King";
		p1.age = 60;
		System.out.println("이름 : " + p1.name + " 나이 : " + p1.age);
		
		Person p2 = new Person();
		System.out.println("현재까지의 인간 수 : " + p2.numOfMan);
		
		System.out.println("현재까지의 인간 수 : " + Person.numOfMan);
		
		Person p3 = new Person("맥도날드");
		System.out.println("현재까지의 인간 수 : " + Person.numOfMan);
		
		Person p4 = new Person("르브론", 40);
		
		p1.introduce();
		p2.introduce();
		p3.introduce();
		p4.introduce();

	}

}
