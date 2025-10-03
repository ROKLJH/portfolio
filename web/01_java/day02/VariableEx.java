package day02;

public class VariableEx {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Variable Example");
        a("Variable Example II");

        // 1. int, long, float, double
        int var1 = 30;  // 정수를 정수에
        long var2 = 100;  // 정수를 정수보다 큰 곳에

        // float var3 = 3.1;  // 소수를 입력하면 기본 타입을 double로 인식 -> 오류
        float var3 = 3.1F;  // F를 주면 float로 인식
        double var4 = 3.1;

        float var5 = (float)3.1;  // explicit cast
        int var6 = (int)var4;

        a("var6 = " + var6);

        float var7 = 3.128734529756294214F;
        double var8 = 3.128734529756294214;

        a("var7 = " + var7);
        a("var8 = " + var8);

        // 2. char - 저장은 숫자타입으로 저장, 읽을 때는 문자로 읽힘
        char ch1 = 'a';  // ASCII(American Standard Code for International Interchange)
        char ch2 = 97;
        char ch3 = 0x61;  // 0x 는 16진수임을 의미
        a("ch1, 2, 3 = " + ch1 + ch2 + ch3);

        // 3. String
        String myName = "BTS";
        a("My name is " + myName);

        // 4. boolean : true 또는 false
        boolean b1 = true;
        boolean b2 = 3 > 5;
        a("b1, b2 = " + b1 + " " + b2);
    }

    public static void a(String str) {
        System.out.println(str);
        System.out.println(str);
        System.out.println(str);
        System.out.println(str);
        System.out.println(str);
    }
}
