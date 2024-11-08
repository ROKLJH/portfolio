package day05;

import java.util.Random;

import util.MyUtil;

public class IfEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.p(MyUtil.RED + "If Example" + MyUtil.END);
		
		Random rd = new Random();
		int a = rd.nextInt(151);
		
		if(a > 100) {
			MyUtil.p(MyUtil.RED + "a는 매우 큽니다 : " + a + MyUtil.END);
		}
		else if(a > 50) {
			MyUtil.p(MyUtil.GREEN + "a는 큽니다 : " + a + MyUtil.END);
		}
		else {
			MyUtil.p("a는 결코 크지 않습니다 : " + a);
		}
		
		// nested
		// 영어와 수학 점수가 모두 60점 이상이면 Pass, 아니면 Fail
		int eng = rd.nextInt(101);
		int math = rd.nextInt(101);
		
		// 과제 : 위 로직을 구현하시오.
		// 영어 : **점, 수학 : **점, Pass or Fail
		
		// 방법1
		if(eng >= 60) {
			if(math >= 60) {
				MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Pass");
			}
			else {
				MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Fail");
			}
		}
		else {
			MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Fail");
		}
		
		// 방법2 : 공통부분 분리
		String PF = "";
		if(eng >= 60)
			if(math >= 60)
				PF = "Pass";
			else
				PF = "Fail";
		else
			PF = "Fail";
		
		MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, " + PF);
		
		// 방법3 : 관계연산자 사용
		if(eng >= 60 && math >= 60)  // 영어 60점 이상 그리고 수학 60점 이상
			MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Pass");
		else
			MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Fail");
		
		if(eng < 60 || math < 60)
			MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Fail");
		else
			MyUtil.p("영어 : " + eng + "점, 수학 : " + math + "점, Pass");
		
	}

}
