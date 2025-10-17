package day08;

import java.util.Random;

import util.MyUtil;

public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyUtil u = new MyUtil();
		u.p("Array Test : Difficulty - GOD");
		
		// 열 개의 이름, 열 개의 임의의 점수(소수점 1자리)
		// 점수 중 최고점과 최저점을 찾은 후
		// 그에 해당하는 이름과 점수를 출력

		// 0. 타이틀 출력하기
		u.cp("최고점과 최저점 정보" );
		
		// 1. 10개의 이름 설정하기
		String[] names = {"A","B","C","D","E","F","G","H","I","J"};
		
		// 2. 10개의 임의의 점수(소수점 1자리) 생성하기
		float[] scores = new float[10];
		Random rd = new Random();
		
		// (1) 0 ~ 1사이의 소수를 뽑은 후 만드는 방법
		float score1 = rd.nextFloat();  // score1 : 0 ~ 1사이 소수
		score1 = score1 * 1000;  // score1 : 0부터 1000사이 소수
		score1 = (int)score1;  // score1 : 0부터 1000까지의 정수
		score1 = score1 / 10;
		System.out.println("score1 : " + score1);
		
		// (2) 0 ~ 1000 사이의 정수를 뽑은 후 만드는 방법
		float score2 = rd.nextInt(1001) / 10F;
		System.out.println("score2 : " + score2);
		
		for(int i=0; i<scores.length; i++) {
			scores[i] = rd.nextInt(1001) / 10F;
		}
		for(float score : scores) {
			u.p("score : " + score);
		}
		
		// 3. 최고점과 최저점을 담을 변수 생성하기
		int indexTop = 0, indexBottom = 0;
		float scoreTop = 0, scoreBottom = 100;
				
		// 4. 최고점과 최저점 찾기
		for(int i=0; i<scores.length; i++) {
			// 최고점인가 체크
			if(scores[i] > scoreTop) {
				scoreTop = scores[i];
				indexTop = i;
			}
			
			// 최저점인가 체크
			if(scores[i] < scoreBottom) {
				scoreBottom = scores[i];
				indexBottom = i;
			}
		}
		
		// 5. 출력하기
		u.p("");
		u.p("Results");
		u.p("Top Score : " + scoreTop + "(" + names[indexTop] + ")");
		u.p("Bottom Score : " + scoreBottom + "(" + names[indexBottom] + ")");	
	}

}
