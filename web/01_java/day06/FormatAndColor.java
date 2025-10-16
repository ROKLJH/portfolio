package day06;

import java.util.Random;

import util.MyUtil;

public class FormatAndColor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. color
		
		// AA라는 글자를 8색으로 나타내시오. "\033[" + 숫자 + "m"
		// for문을 사용하시오. AAAAAAAAAAAAAAAA
		// 일반 8색을 구현하시오.
		for(int i=30; i<38; i++) {
			System.out.print("\033[" + i + "mAA" + MyUtil.END);
		}
		MyUtil.p("");
		for(int i=90; i<98; i++) {
			System.out.print("\033[" + i + "mAA" + MyUtil.END);
		}
		MyUtil.p("");
		for(int i=40; i<48; i++) {
			System.out.print("\033[" + i + "m  " + MyUtil.END);
		}
		MyUtil.p("");
		for(int i=100; i<108; i++) {
			System.out.print("\033[" + i + "m  " + MyUtil.END);
		}
		MyUtil.p("");
		
		// 확장 256색을 구현하시오.(바탕색, 글자는 공백1글자로)
		for(int i=0; i<256; i++) {
			System.out.print("\033[48;5;" + i + "m " + MyUtil.END);
		}
		MyUtil.p("");
		
		// True Color
		// Red, Green, Blue을 각각 Random을 사용하여 0부터 255까지 중 하나를 선택
		// 해당 작업을 256번 수행하여 출력한다.
		// Red, Green, Blue에 같은 Random값을 넣어서 256번 반복
		int r, g, b;
		Random rd = new Random();
		for(int i=0; i<256; i++) {
			r = rd.nextInt(256);
			g = rd.nextInt(256);
			b = rd.nextInt(256);
			String colorString = r + ";" + g + ";" + b;
			System.out.print("\033[48;2;" + colorString + "m " + MyUtil.END);
		}
		MyUtil.p("");
		for(int i=0; i<256; i++) {
			r = rd.nextInt(256);
			g = r;
			b = r;
			String colorString = r + ";" + g + ";" + b;
			System.out.print("\033[48;2;" + colorString + "m " + MyUtil.END);
		}
		
	}

}
