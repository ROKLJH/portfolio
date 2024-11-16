package day15;

public class MyExceptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// sendReceipt(400000000);
			// sendReceipt(300);
			sendReceipt(200000);
		}
		catch(Exception e) {
			e.printStackTrace();
			
			// 예외처리 통계DB에 입력 등 후속 조치 가능
			try {
				// 데이터베이스에 데이터 추가
				// 이 경우 중첩 예외처리가 일어날 수 있음
			}
			catch(Exception e2) { e2.printStackTrace(); }
		}
		finally {
			System.out.println("[finally] 오류나던지 말던지 나는 수행해라");
		}
		
		System.out.println("[회계 프로그램] 정상 종료");
	}

	public static void sendReceipt(int amt) throws MyException {
		if(amt > 100000000)
			throw new MyException("[B001] 과도한 청구금액 넌 백퍼 사기꾼");
		else if(amt < 1000)
			throw new MyException("[B002] 금액 " + amt + "원은 니 돈주고 사먹어");
		
		System.out.println("[sendReceipt()] " + amt + "원 정상처리");
	}
}

class MyException extends Exception {
	MyException(String msg){
		super(msg);
		System.out.println("[MyException] 경찰서 고발");
		System.out.println("[MyException] 메시지 로그 기록");
		System.out.println("[MyException] 모든 전원 오프");
		System.out.println("[MyException] 전원 계약 해지");
		System.out.println("[MyException] 관련자 모두 무급 출근");
		System.out.println("[MyException] 사장님에게 보고");
	}
}
