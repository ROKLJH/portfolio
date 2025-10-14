package day05;

import util.MyUtil;

public class WhileEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.p("While Statement");
		
		int i = 0;
		while(i < 100) {
			MyUtil.p("Current i = " + i);
			i++;
		}
		
		MyUtil.p("After While i = " + i);
		
		// Infinity Loop
		i = 0;
		while(true) {
			MyUtil.p("This i is " + i);
			i++;
			if(i > 10000) {
				break;
			}
		}
		
		
	}

}
