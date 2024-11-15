package day13;

import util.MyUtil;

public class CalApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.cp(MyUtil.BOLD + "Super Calculator De Jesus");
		
		// CalculatorDummy c = new CalculatorDummy();
		Calculator c = new Calculator();
		
		c.setOperand(10, 20, 31);
		MyUtil.p("합계는 " + c.sum());
		MyUtil.p("평균은 " + c.avg());
	}

}

class CalculatorDummy implements CalculatorI {
	public void setOperand(int aaa, int bbb, int ccc) {
		
	}
	
	public int sum() {
		return 100;
	}
	
	public double avg() {
		return 30.0;
	}
}
