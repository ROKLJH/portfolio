package day07;

import util.MyUtil;

public class ArrayEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.p("Array Example");
		
		String[] str = new String[5];
		str[0] = "Eliot";
		str[3] = "Abdula";
		MyUtil.p("str.length? " + str.length);
		
		String[] stdName1 = new String[5];
		String[] stdName2 = {"A","B","C","D","Federer"};
		
		// String의 재사용성
		String aaa = new String("James");
		String bbb = "David";
		String ccc = new String("James");
		String ddd = "David";
		
		MyUtil.p("aaa == ccc? " + (aaa==ccc));
		MyUtil.p("bbb == ddd? " + (bbb==ddd));
		MyUtil.p("aaa == ccc? " + aaa.equals(ccc));
		
		int score = 100;
		Integer score1 = new Integer(100);
		
		int[] score2 = {10, 20, 10, 30, 20};
		
		// 학생의 이름과 점수들을 출력해보세요.
		// 학생이름1 : **점
		// 학생이름2 : **점
		// ...
		for(int i=0; i<score2.length; i++) {
			MyUtil.p(stdName2[i] + " : " + score2[i]);
		}
		
		// 2-dimensional Array
		String[][] classStd = {{"A","B"},{"C","D"}};
		// classStd[0][0] ~ [1][1]
		// A학생의 위치는 classStd[0][0]
		// C학생의 위치는 classStd[1][0]
		// String[] class0 = classStd[0];  // 곰곰히 생각해볼 것!!
		
		// 학년, 반, 학생 => String[][][]
		// => 한 번 써보면 안쓰게 됨 => 겸손
		
		// 모든 학생 이름 출력하기
		MyUtil.p("");
		MyUtil.p("Display All Student's Name");
		MyUtil.p(MyUtil.GREEN + "General" + MyUtil.END);
		for(int i=0; i<stdName2.length; i++) {
			MyUtil.p(stdName2[i]);
		}
		
		MyUtil.p("");
		MyUtil.p("Display All Student's Name");
		MyUtil.p(MyUtil.RED + "Enhanced For" + MyUtil.END);
		// stdName2에서 하나를 꺼내서 name에 넣고 한 바퀴 수행
		for(String name : stdName2) {  // stdName2는 String의 집합
			MyUtil.p(name);
			// 이름이 "C" 이면 그만둠
			if(name.equals("C")) {
				break;
			}
		}
		
		// Enhanced For를 활용하여 점수를 출력하시오.
		for(int scr : score2) {
			MyUtil.p("" + scr);
		}
		
		// Enhanced For를 활용한 Difficulty GOD 문제
		// String[][] classStd = {{"A","B"},{"C","D"}};
		// Enhanced For를 활용하여 모든 학생을 출력하시오.
		for(String[] cls : classStd) {  // classStd[0], classStd[1]
			for(String std : cls) {
				MyUtil.p(std);
			}
		}
		
//		Welcome welcome = new Welcome();
//		String[] zzz = null;
//		welcome.main(zzz);
		System.out.println("AAA");
	}

}
