package day13;

import util.MyUtil;

public class MyClass implements MyInterface {
	public int aaa = 1000;
	
	public void myMethod() {
		
	}
	
	public void dMethod2() {  // 인터페이스의 default 메서드를 새롭게 정의하면, 기존 default는 사라진다.
		MyUtil.p("[dMethod2] 난 베끼지 않는다 새롭게 정의할 뿐");
	}
}
