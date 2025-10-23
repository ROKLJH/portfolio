package day11;

import java.util.Scanner;

import util.MyUtil;

public class ClassMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassC c = new ClassC();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("어떤 폰 가지고 계세요?(1.일반폰, 2.스마트폰) : ");
		int sel = sc.nextInt();
		
		Phone myPhone;
		if(sel == 1) {
			myPhone = new Phone();
		}
		else {
			myPhone = new SmartPhone();
		}
		
		myPhone.sendText("qoufghewofoeigfoqgfoegfoegfouiegfoqgoegfoefgoefgowuegfoefgoefgoegfoewgfoiquwegfoegfoiwegfoegfowegoiqgfioqgefoigeofge");
		myPhone.call();
		SmartPhone sp = (SmartPhone)myPhone;
		sp.installApp();
		sp.webSerfing();
	}

}

class Phone {
	public void sendText(String str) {
		if(str.length() > 80) {
			MyUtil.p("문자열이 너무 깁니다..");
		}
		else {
			MyUtil.p(str);
		}
	}
	
	public void call() {
		MyUtil.p("Calling....");
	}
}

class SmartPhone extends Phone {
	public void installApp() {
		MyUtil.p("Installing App....");
	}
	
	public void webSerfing() {
		MyUtil.p("Web Serfing....");
	}
	
	@Override
	public void sendText(String str) {
		MyUtil.p(str);
	}
	
}