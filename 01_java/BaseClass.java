package day14;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import util.MyUtil;

public class BaseClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.cp("Base Class Example");
		
		// 1. System
		long ctime = System.currentTimeMillis();
		System.out.println("현재 시간 : " + ctime);
		
		long point1, point2;
		long elapsedTime;
		
		point1 = System.currentTimeMillis();
		for(int i=0; i<1000000; i++) {
			;
		}
		point2 = System.currentTimeMillis();
		elapsedTime = point2 - point1;
		System.out.println("for문 수행시간(ms) : " + elapsedTime);
		
		// 2. StringBuffer
		StringBuffer sb = new StringBuffer();
		sb.append("Eriks Steakhouse");
		System.out.println(sb);
		sb.insert(6, "Famous ");
		System.out.println(sb);
		// sb.insert(100, "I am genius");  // 인덱스 범위(문자열의 길이)를 넘어가면 오류
		sb.delete(1, 10);
		System.out.println(sb);
		sb.delete(3, 1000);  // delete는 종료 인덱스를 큰 숫자를 넣어도 수행
		System.out.println(sb);
		
		// String, StringBuffer Performance Contest
		String app1 = "";
		StringBuffer app2 = new StringBuffer();
		
		final int SAMPLE_CNT = 10000;
		// 1. 샘플 크기를 변경할 때 편리하다. 한 번만 바꿔주면 되기 때문에
		// 2. 실수로 하나만 샘플 수가 변경되는 위험을 막아준다.
		long elapsedTime1, elapsedTime2;
		
		point1 = System.currentTimeMillis();
		for(int i=0; i<SAMPLE_CNT; i++) {
			app1 = app1 + i;  // 0 01 012 0123 ....
		}
		point2 = System.currentTimeMillis();
		elapsedTime1 = point2 - point1;
		
		point1 = System.currentTimeMillis();
		for(int i=0; i<SAMPLE_CNT; i++) {
			app2.append(i);
		}
		point2 = System.currentTimeMillis();
		elapsedTime2 = point2 - point1;
		
		MyUtil.cp("*** Performance Contest Result ***");
		MyUtil.p("String : " + elapsedTime1 + "(ms)");
		MyUtil.p("StringBuffer : " + elapsedTime2 + "(ms)");
		
		// 3. Math
		System.out.println("Math.getRound(3.14, 1) : " + getRound(3.152, 2));
		
		// 4. Date
		Date d = new Date();  // 현재 시간
		System.out.println("현재 시간 : " + d);
		
		// format이 심하게 맘에 들지 않을 경우 => SimpleDateFormat
		SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");
		System.out.println("현재 시간 : " + f1.format(d));
		
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("현재 시간 : " + f2.format(d));
		
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		System.out.println("현재 시간 : " + f3.format(d));
		
		// 5. Calendar
		Calendar c = Calendar.getInstance();
		
		// get 메서드로 내가 원하는 달력의 값을 얻어낼 수 있다.
		System.out.println("오늘의 요일은 : " + c.get(Calendar.DAY_OF_WEEK));
		System.out.println("연도 : " + c.get(Calendar.YEAR));
		System.out.println("월 : " + (c.get(Calendar.MONTH)+1));
		System.out.println("일 : " + c.get(Calendar.DAY_OF_MONTH));
		System.out.println("오늘이 이번 연도 몇 번째 날 : " + c.get(Calendar.DAY_OF_YEAR));
		System.out.println("오늘이 이번 연도 몇 번째 주 : " + c.get(Calendar.WEEK_OF_YEAR));
	}
	
	// a를 b자리에서 반올림(b >= 0)
	public static double getRound(double a, int b) {
		double result = 0.0;
		double var = a;
		
		// 10을 b의 회수만큼 곱한다.
		for(int i=0; i<b; i++) {
			var = var * 10;
		}
		
		// 소수점 이하를 버린다.
		var = Math.round(var);
		
		// 10을 b의 회수만큼 나눈다.
		for(int i=0; i<b; i++) {
			var = var / 10;
		}
		
		result = var;
		return result;
	}

}
