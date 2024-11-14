package day11;

import java.util.Random;

import util.MyUtil;

public class DepartEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Department[] d = new Department[5];  // 백화점 5곳
		
		d[0] = new Department("강남점");
		d[1] = new Department("본점");
		d[2] = new Department("USA점");
		d[3] = new Department("일본점");
		d[4] = new Department("속리산점");
		
		d[0].setOpenInd(true);
		Department.setPranOpenInd(true);
		
		Random rd = new Random();
		for(int i=0; i<d.length; i++) {
			if(rd.nextInt(2) == 1)
				d[i].setOpenInd(true);
			else
				d[i].setOpenInd(false);
		}
		
		MyUtil.p("지점 오픈 상태 체크 결과");
		for(int i=0; i<d.length; i++) {
			MyUtil.p("[" + d[i].name + "] " + (d[i].getOpenStatus()? "열었어":"닫았어"));
		}
		
		// 1억 이하의 임의의 금액을 얻은 후 1000단위 이하를 절사하여 amt에 추가
		for(int i=0; i<10; i++) {
			for(int j=0; j<d.length; j++) {
				int todayAmt = rd.nextInt(100000001) / 1000 * 1000;
				d[j].addAmt(todayAmt);
			}
		}
		
		for(Department dd : d) {
			MyUtil.p(dd.name + " : " + dd.getAmt());
		}
		
		// 가장 매출이 높은 지점은?
		String topName = null;
		int topAmt = 0;
		
		for(Department dd : d) {
			if(dd.getAmt() > topAmt) {
				topName = dd.name;
				topAmt = dd.getAmt();
			}
		}
		
		MyUtil.cp("최대 매출 지점");
		MyUtil.p(MyUtil.RED + MyUtil.BOLD + topName + " : " + topAmt + MyUtil.END);
	}

}

class Department {
	// 1. name : 백화점 지점 이름, 생성할 때 지정 가능
	// 2. pranName : 브랜드 이름, 수정 불가, 최초에 "내백화점"으로 세팅
	// 3. amt : 지점의 매출액, 초기값은 0, 직접 수정 불가
	// 4. openInd : 현재 지점의 오픈 가능 여부(true or false)
	// 5. pranOpenInd : 전체(브랜드) 백화점의 오픈 가능 여부
	// 6. getOpenStatus() : 현재 지점이 오픈 가능한지 여부를 돌려주는 메서드
	// 7. addAmt(int amt) : 매출액을 더하는 메서드
	
	// Step0. 클래스 설명 넣기
	// 백화점 지점 단위를 객체로 갖는 클래스
	
	// Step1. 필드 정의 
	String name;
	static final String pranName = "내백화점";  // 모든 지점 공통
	private int amt = 0;
	private boolean openInd;
	private static boolean pranOpenInd;
	
	// Step2. Constructor
	// 지점 이름을 지정하면 지점 이름 세팅, 안하면 "미정" 세팅
	Department(){
		// name = "미정";
		this("미정");
	}
	
	Department(String name){
		this.name = name;
	}
	
	// Step3. getOpenStatus 구현
	public boolean getOpenStatus() {
//		1번 방식
//		if(openInd == true) {
//			if(pranOpenInd == true) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}

//	    2번 방식
//		if(openInd == true && pranOpenInd == true) {
//			return true;
//		}
		
//		if(openInd && pranOpenInd) {
//			return true;
//		}
//		return false;
		
		return openInd && pranOpenInd;
	}
	
	// Step4. Getter/Setter
	public void setOpenInd(boolean status) {
		openInd = status;
	}
	
	public static void setPranOpenInd(boolean status) {
		pranOpenInd = status;
	}
	
	public int getAmt() {
		return amt;
	}
	
	// Step5. User Function
	// 매출액을 더하는 메서드
	public void addAmt(int amt) {
//		this.amt = this.amt + amt;
		this.amt += amt;
	}
}







