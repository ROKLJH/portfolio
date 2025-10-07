package day03;

import util.MyUtil;

public class OperEx {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyUtil u = new MyUtil();
        u.p("Operation Example");

        // 1. 대입연산
        int a = 100;
        String b = "Summer";
        u.p("1. 대입연산 : " + a + ", " + b);

        // 2. 대입연산2
        a += 10;  // 더해서 넣는다.
        u.p("2. 대입연산2 : " + a);

        // 3. 산술연산
        a = 5 + 5 * 2;  // a = 15
        a = (5 + 5) * 2;  // a = 20
        u.p("3. 산술연산 : " + a);

        // 4. 산술연산2
        int NUM_OF_GROUP = 11;  // _portA : 내부(internal) 변수
        int job = 28673521 % NUM_OF_GROUP;
        u.p("4. 담당그룹은 " + job + "입니다.");

        // 5. 산술연산3
        float numf = 10 / 4;  // 정수와 정수의 연산결과는 정수
        u.p("5. numf = " + numf);
        float numf2 = 10F / 4F;
        u.p("5. numf2 = " + numf2);
        double numd = 10F / 4;  // 암시적 Cast
        float numf3 = (float)(10D / 4);  // 명시적 Cast

        // 6. 0으로 나누어보기
        //float num = 10 / 0;  // 1.Inf 2.Infinity 3.오류(Exception)
    }

}
