package day15;

public class ExceptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator c = new Calculator();
		c.setOperand(100, 27);
		c.divide1();
		
		c.setOperand(100, 0);
		// c.divide1();  // 0 나누기에 대한 대비가 안되어있어 프로그램 예외 종료
		c.divide2();
		
		try {
			c.divide3();
		}
		catch(Exception e) { e.printStackTrace(); }
		
		c.setOperand(100000, 0);
		try {
			c.divide4();
		}
		catch(Exception e) { e.printStackTrace(); }
		
		System.out.println("프로그램 완벽 종료");
	}
	
	void test() throws Exception {
		Calculator c = new Calculator();
		c.divide3();
	}

}

class Calculator {
	int left, right;
	
	public void setOperand(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	// 무대비
	public void divide1() {
		System.out.println("[divide()] " + left/right);
	}
	
	// 완벽한 예외 대비
	public void divide2() {
		try {
			System.out.println("[divide()] " + left/right);
		}
		catch(ArithmeticException e) {
			System.out.println("[divide()] 아리스메틱 오류 발생!");
			System.out.println("[divide()][B001] 오류 데이터 : " + left + ", " + right);
			System.out.println("[divide()] 잼민이는 집에 가");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	// 예고 또는 경고
	public void divide3() throws Exception {  // 나 울 가능성 있다, 예외 날 수 있다
		System.out.println("[divide()] " + left/right);
	}
	
	// 사용자(개발자, 나)가 예외를 강제로 발생시키는 메서드
	public void divide4() throws Exception {
		if(left >= 10000)  // left가 10000 이상인 경우 나누기를 하고 싶지 않다면?
			throw new Exception("10000 이상 입력하셨습니다 맞을래요?");
		
		System.out.println("[divide()] " + left/right);
	}
}








