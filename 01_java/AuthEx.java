package day09;

import util.MyUtil;

public class AuthEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClass c = new MyClass();
		MyUtil.cp("Auth Example");
		MyUtil.p("c.var1 = " + c.var1);
		c.var1 = 100;  // 변수를 외부에서 바꿀 수 있다? => 위험성 존재
		MyUtil.p("c.var1 = " + c.var1);
		
		// MyUtil.p("c.var2 = " + c.var2);  // var2 is not visible(private 변수는 못 봄)
		MyUtil.p("c.var2 = " + c.getVar2());
		c.setVar2(5000);
		MyUtil.p("c.var2 = " + c.getVar2());
		
	}

}

class MyClass {
	public int var1 = 3;
	private int var2 = 10;
	
	// @Getter
	public int getVar2() {
		return var2;
	}
	
	// @Setter
	public void setVar2(int var) {
		var2 = var;
	}
	
	// 변수는 대부분 private
	// => 변수마다 set/get 메서드가 존재할 가능성이 높음
	// 변수가 100개다..... => set/get 메서드를 내가 100개씩 개발??
	
	// getter/setter는 추후 Spring에서 lombok 라이브러리를 통해
	// 자동생성 시킬 수 있음
}