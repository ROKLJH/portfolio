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
    }

}
