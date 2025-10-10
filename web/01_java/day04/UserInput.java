package day04;

import java.util.Scanner;

public class UserInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. 실행 시 입력하기
		System.out.println("작업일자 : " + args[0]);
		// 이름(작업자)와 작업유형 출력하기
		System.out.println("작업자 : " + args[1] + " 작업유형 : " + args[2]);
		//System.out.println("이것도 주냐 : " + args[3]);  // 없는 인덱스 사용 시 오류(예외)
		
		// 2. Scanner 사용하기
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하십시오 : ");
		String name = sc.next();  // 문자열로 입력받기
		System.out.println("앗 당신이 그 유명한 " + name + "님?");
		
		System.out.print("문자 1을 입력하십시오 : ");
		String a1 = sc.next();
		System.out.print("숫자 1을 입력하십시오 : ");
		int a2 = sc.nextInt();
		System.out.println("a1 : " + (a1 + 1));
		System.out.println("a2 : " + (a2 + 1));
		
	}

}
