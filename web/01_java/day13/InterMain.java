package day13;

import util.MyUtil;

public class InterMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClass mc = new MyClass();
		MyUtil.p("mc.aaa = " + mc.aaa);
		// MyUtil.p("MyClass.aaa = " + MyClass.aaa);  // MyClass에서 새롭게 정의하는 경우 static 속성은 사라짐
		MyUtil.p("MyInterface.aaa = " + MyInterface.aaa);
		
		mc.dMethod();
		mc.dMethod2();
		
		// mc.sMethod();  // static 함수는 구현되지 않는다.
		MyInterface.sMethod();
		// static 함수로 더욱 인터페이스의 정의가 애매모호해짐
		
		// MyInterface mi = new MyInterface();
		MyInterface mi = new MyClass();
		mi.dMethod2();
		// mi.sMethod();
	}
	
}
