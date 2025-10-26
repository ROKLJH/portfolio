package day12;

import util.MyUtil;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.cp("String Class");
		
		// 1. length()
		String str1 = "qioueghfwegfiegfoqegfuiowgfogqfoegfgegfwuofg";
		String str2 = "한글은 몇 자로 칩니까?";  // 한글도 1자로 인식
		MyUtil.p("[length()] eng : " + str1.length());
		MyUtil.p("[length()] kor : " + str2.length());
		
		// 2. charAt()
		String str3 = "이것도 한글이 궁금해요";
		MyUtil.p("[charAt()] : " + str3.charAt(5));
		
		// 3. equals, equalsIgnoreCase
		String str4 = new String("abcde");
		String str5 = new String("abcde");
		MyUtil.p("[equals()] str4 == str5 : " + (str4==str5));
		MyUtil.p("[equals()] str4.equals(str5) : " + str4.equals(str5));
		String str6 = new String("ABCDE");
		MyUtil.p("[equals()] str4.equals(str6) : " + str4.equals(str6));
		MyUtil.p("[equals()] str4.equalsIgnoreCase(str6) : " + str4.equalsIgnoreCase(str6));
		
		// 4. replace()
		String str7 = "Heaven helps those who help themselves";
		String str8 = "help";
		MyUtil.p("[replace()] : " + str7.replace(str8, "____"));
		String str9 = str7.replace("e","_");
		MyUtil.p("[replace()] : " + str9);
		
		// 5. substring()
		String str10 = str7.substring(0, 4);
		MyUtil.p("[substring(0,4)] : " + str10);
		
		// 두 번째 인자를 생략하는 경우
		String str11 = str7.substring(5);  // 인덱스5부터 끝까지
		MyUtil.p("[substring(5)] : " + str11);
		
		// 실습문제 : str12에 str7의 오른쪽 다섯 글자를 넣으시오.
		String str12 = str7.substring(str7.length()-5);
		MyUtil.p("[substring()] : " + str12);
		
		// 6. trim()
		MyUtil.p("[trim()] : " + "       qwouigfuoegfoueg     ".trim());
		
		// 7. split()
		String[] str13 = str7.split(" helps ");
		for(String s : str13) {
			MyUtil.p("[split()] : " + s);
		}
		
		// 단어별
		String[] str14 = str7.split(" ");
		for(String s : str14) MyUtil.p("[split()] : " + s);
		
		// 글자별
		String[] str15 = str7.split("");
		for(String s : str15) MyUtil.p("[split()] : " + s);
		
		// 구분자가 2개일 때 어떻게 할 수 있을까? e 또는 공백(space)
		String[] str16 = str7.split("[ e]");
		for(String s : str16) MyUtil.p("[split()] : " + s);
		
		// 8. matches(regular expression)
		String str17 = "298-182318-82321793";
		String regExp = "[0-9]{3,4}[-][0-9]{5,7}[-][0-9]{6,100}";
		boolean result = str17.matches(regExp);
		MyUtil.p("[matches()] : " + result);
		
		String fileName = "data.zip";
		String checkRule = "(.*?)\\.(exe|sh|zip|alz)$";
		result = fileName.matches(checkRule);
		MyUtil.p("[matches()] : " + (result? "당신지금뭐하는거야":"업로드가능"));
		
		String phoneNum = "011-123-2314";
		result = checkPhoneNumber(phoneNum);
		if(result)
			MyUtil.p("완벽한 전화번호 입력 완료");
		else
			MyUtil.p("너 지금 뭐 입력한거냐");
	}
	
	private static boolean checkPhoneNumber(String str) {
		// str이 전화번호 타입인지 검사하여 전화번호 타입이면 true return
		// 숫자3자리 + "-" + 숫자3-4자리 + "-" + 숫자4자리
		String checkRule = "[0-9]{3}[-][0-9]{3,4}[-][0-9]{4}";
		
		return str.matches(checkRule);
	}

}






