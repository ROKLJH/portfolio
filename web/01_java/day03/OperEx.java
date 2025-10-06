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

        // 7. 증감연산
        int x = 10, y = 10;
        x++; ++y;
        u.p("7. x, y = " + x + ", " + y);
        int x1 = x++;  // x를 x1에 넣고 ++
        int y1 = ++y;  // y를 ++하고 y1에 넣고
        u.p("7. x1, y1 = " + x1 + ", " + y1);

        // 이름을 나타내는 변수를 만들고 이름을 넣는다.
        // 나이를 나타내는 변수를 만들고 나이를 넣는다.
        // 내 이름은 ***이고, 내 나이는 ***입니다. 를 출력하시오.
        String myName = "Lebron";
        int myAge = 900;
        u.p("내 이름은 " + myName + "이고, 내 나이는 " + myAge + "입니다.");
    }

}
