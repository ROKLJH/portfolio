package day13;

import util.MyUtil;

public interface MyInterface {
	int aaa = 500;  // 인터페이스에서 변수를 정의하면 자동으로 static이 된다.
	
	void myMethod();
	
	default void dMethod() {  // default는 인터페이스가 메서드를 구현한다.
		MyUtil.p("[dMethod] Called");
	}
	
	default void dMethod2() {  // default는 인터페이스가 메서드를 구현한다.
		MyUtil.p("[dMethod2] Called");
	}
	
	static void sMethod() {  // static 함수는 구현되지 않는다.
		MyUtil.p("[sMethod] 누가 날 부르셨어요?");
	}
}
