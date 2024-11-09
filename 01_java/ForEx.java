package day06;

import util.MyUtil;

public class ForEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.p(MyUtil.BOLD + MyUtil.RED + "For Example" + MyUtil.END);
		
		// 1. For
		
		for(int i=0; i<100; i++) {
			MyUtil.p("this i is " + i);
		}
		
		//MyUtil.p("this i is " + i);
		
		int k = -10000;
		for(k=0; k<10; k++) {
			MyUtil.p("[1]This k is " + k);
		}
		MyUtil.p("[1]Final k is " + k);  // (1) 10(*****)  (2) -10000  (3) 11
		
		
		// 2. do - while
		
		boolean isTeacher = true;
		do {
			MyUtil.p("야 이 바보야");
		} while(isTeacher != true);
		// 출력이 안될 것으로 예상했으나 do는 먼저 실행부터 한다.
		// 사용 시 유의하시오(쓰지 마시오)
		
		
		// 실습과제
		// 제어문을 사용하여 1부터 100까지를 출력하되,
		// 3의 배수만 제외하고 출력하시오.
		// 3의 배수 : 3으로 나누어서 나머지가 0인 수(i % 3 == 0)
		for(int i=1; i<=100; i++) {
			if(i % 3 != 0)
				System.out.print(i + " ");
		}
		
		// continue 사용
		for(int i=1; i<=100; i++) {
			if(i % 3 == 0) continue;  // continue를 만나면 반복문의 처음으로 돌아간다.
			System.out.print(i + " ");
		}
		
	}

}
