package view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import model.MemberVO;
import model.ProductVO;

public class View {
   private Scanner sc;
   private int mAction;
   private int uAction;
   private int aAction;
   private int bAction;
   private int sAction;

   public View() {
      sc = new Scanner(System.in); // View를 처음 실행될때 스캐너 생성되도록
      mAction = 3;
      uAction = 1;
      aAction = 3;
      bAction = 3;
      sAction = 2; 
   }

   public int startMenuPrint() {
      int action;

      System.out.println("      .-'\\\r\n"
            + "   .-'  `/\\\r\n"
            + ".-'      `/\\\r\n"
            + "\\         `/\\\r\n"
            + " \\         `/\\\r\n"
            + "  \\    _-   `/\\       _.--.\r\n"
            + "   \\    _-   `/`-..--\\     )\r\n"
            + "    \\    _-   `,','  /    ,')\r\n"
            + "     `-_   -   ` -- ~   ,','\r\n"
            + "      `-              ,','\r\n"
            + "       \\,--.    ____==-~\r\n"
            + "        \\   \\_-~\\\r\n"
            + "         `_-~_.-'\r\n"
            + " _      _ \\-~");
      System.out.println("   _____    __\r\n"
            + "  / ___/   / /_   ____   ___\r\n"
            + "  \\__ \\   / __ \\ / __ \\ / _ \\\r\n"
            + " ___/ /  / / / // /_/ //  __/\r\n"
            + "/____/  /_/ /_/ \\____/ \\___/");
      System.out.println("   _____   __\r\n"
            + "  / ___/  / /_  ____    _____  ___\r\n"
            + "  \\__ \\  / __/ / __ \\  / ___/ / _ \\\r\n"
            + " ___/ / / /_  / /_/ / / /    /  __/\r\n"
            + "/____/  \\__/  \\____/ /_/     \\___/");


      while (true) {
         try {
            System.out.println("--------------------------------\n");
            System.out.println("      << Main Home >>\n");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 프로그램 종료");
            System.out.print("▶ ");
            action = sc.nextInt();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수로 다시 입력해주세요!\n");
            continue;
         }
         if (1 <= action && action <= mAction) {
            break;
         }
         System.out.println("범위를 확인하고 다시 입력해주세요.\n");

      }
      return action;

   }

   public int userMenuPrint() {
      int action;
      while (true) {
         try {
            System.out.println("--------------------------------\n");
            System.out.println("      << User Page >> \n");
            System.out.println("1. 상품보기");
            System.out.println("0. 로그아웃");
            System.out.print("▶ ");
            action = sc.nextInt();
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수로 다시 입력해주세요!\n");
            continue;
         }
         if (0 <= action && action <= uAction) {
            break;
         }
         System.out.println("범위를 확인하고 다시 입력해주세요.\n");

      }
      return action;

   }

   public int userBuyPrint() {
      int action;
      while (true) {
         try {
            System.out.println("--------------------------------\n");
            System.out.println("      << Purchase Page >>\n");
            System.out.println("1. 검색");
            System.out.println("2. 장바구니 담기");
            System.out.println("3. 구매");
            System.out.println("0. 돌아가기");
            System.out.print("▶ ");
            action = sc.nextInt();
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수로 다시 입력해주세요!\n");
            continue;
         }
         if (0 <= action && action <= bAction) {
            break;
         }
         System.out.println("범위를 확인하고 다시 입력해주세요.\n");

      }
      return action;

   }

   public int adminMenuPrint() {
      int action;
      while (true) {
         try {
            System.out.println("--------------------------------\n");
            System.out.println();
            System.out.println("      [ Admin Page ]\n");
            System.out.println("1. 상품관리");
            System.out.println("2. 상품목록출력");
            System.out.println("3. 매출확인");
            System.out.println("0. 로그아웃");
            System.out.print("▶ ");
            action = sc.nextInt();
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수로 다시 입력해주세요!\n");
            continue;
         }
         if (0 <= action && action <= aAction) {
            break;
         }
         System.out.println("범위를 확인하고 다시 입력해주세요.\n");

      }
      return action;

   }

   public void overlapId() {
      System.out.println("이미 존재하는 아이디입니다.\n");
   }

   public MemberVO login() { // 로그인
      System.out.println();

      System.out.print("ID : ");
      String id = sc.next();
      System.out.print("PW : ");
      String pw = sc.next();
      MemberVO vo = new MemberVO();
      vo.setId(id);
      vo.setPw(pw);
      return vo;

   }

   public void hello(MemberVO mvo) {
      System.out.println("\n"+mvo.getName() + "님 안녕하세요 :)\n");
   }

   public void logout() { // 로그아웃
      System.out.println("로그아웃 되었습니다.\n");
   }
   //   public void getId() { // 아이디입력
   //      
   //   }
   //   public void getIPw() { // 비밀번호입력
   //      
   //   }
   //   public void getName() { // 이름입력
   //      
   //   }

   public String getString(String msg) { // 문자열입력
      System.out.print(msg + " :");
      String s = sc.next();

      return s;

   }

   public int getInt(String msg) { // 정수입력

      System.out.print(msg + " :");
      int i = 0;
      try {
         i = sc.nextInt();
      } catch (Exception e) {
         System.out.println("정수로 입력해주세요.\n");
      }

      return i;

   }
   public int getCnt() {
      while(true) {

         System.out.print("구매 수량 : ");
         int i = 0;
         try {
            i = sc.nextInt();
         } catch (Exception e) {
            System.out.println("정수로 입력해주세요.\n");
         }
         if(i<=0) {
            System.out.println("수량을 1이상으로 입력해주세요.");
            continue;
         }

         return i;
      }


   }

   public void isTrue(String msg) { // 성공
      System.out.println(msg + "성공\n");

   }

   public void isFalse(String msg) { // 실패
      System.out.println(msg + "실패\n");

   }

   public void loginFalse() {
      System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.\n");
   }

   public boolean inputPw(MemberVO mvo) {
      System.out.print("PW CHECK : ");
      String pw2 = sc.next();
      System.out.println();
      if (mvo.getPw().equals(pw2)) {
         return true;
      }
      return false;
   }

   public void overlapPw() {
      System.out.println("비밀번호가 일치하지 않습니다.\n");
   }

   public void nullProduct() {
      System.out.println("존재하지 않는 상품번호입니다.\n");
   }

   public ProductVO inputCart() { // 장바구니에 담기 (상품번호)
      System.out.println("      <<장바구니 추가>>\n");
      System.out.print("Serial No : ");
      int num = 0;
      try {
         num = sc.nextInt();
         System.out.println();
      } catch (Exception e) {
         System.out.println("정수로 입력해주세요.\n");
         sc.nextLine();
      }
      ProductVO vo = new ProductVO();
      vo.setNum(num);
      return vo;
      // vo의 num이 있는 상품이라면 true반환(model)
   }

   public ProductVO inputCart2(ProductVO pvo) { // 장바구니 담기(장바구니에 몇 개 담을 지 (수량))

      while (true) {

         System.out.print("수량 : ");
         int cnt = 0;
         try {
            cnt = sc.nextInt();
            System.out.println();
         } catch (Exception e) {
            System.out.println("정수로 입력해주세요.\n");
            continue;
         }
         ProductVO vo = new ProductVO();
         if (cnt >= vo.getCnt()) {

            vo.setCnt(cnt);
            return vo;
         }
         continue;
      }
   }

   public int buyProduct() { // 상품 구매
      while (true) {
         System.out.println("--------------------------------\n");
         System.out.println("      << 장바구니 담기 >>\n");
         System.out.print("Serial No:  ");
         int num = 0;
         try {
            num = sc.nextInt();
            System.out.println();
         } catch (Exception e) {
            System.out.println("정수로 입력해주세요.\n");
            sc.nextLine();
            continue;

         }
         return num;
      }
   }

   public void printCart(Map<String,Integer> cdatas) {

      System.out.println("      [ Cart List ]");
      //      for (ProductVO p : pdatas) {
      //         System.out.println(p);
      //      }

      System.out.println("--------------------------------");//32칸
      for (Entry<String, Integer> entrySet : cdatas.entrySet()) {   

         System.out.println(" 제품명 : " +entrySet.getKey());
         System.out.println("  수 량 :" + entrySet.getValue());
         System.out.println();
      }



   }

   public void lessProduct() {
      System.out.println("수량이 부족합니다.\n");
   }

   public void selectAll(ArrayList<ProductVO> pdatas) { // 전체상품출력
      System.out.println("      << Product List >>\n");
      //      for (ProductVO p : pdatas) {
      //         System.out.println(p);
      //      }

      for(int i=0; i<pdatas.size(); i++) {
         System.out.println("--------------------------------");//32칸

         int blank = ((32-pdatas.get(i).getName().toString().length())/2)
               -(pdatas.get(i).getName().toString().length())/2;            
         if(blank > 0) {
            for(int j=0; j < blank; j++) {
               System.out.print(" ");
            }
         }
         else {
            System.out.print("    ");
         }

         System.out.println("< "+pdatas.get(i).getName()+" >\n");   

         System.out.println("Serial No.          " + pdatas.get(i).getNum()+"");
         System.out.println("[원  가]             " + pdatas.get(i).getRealPrice()+ "￦");         
         System.out.println("[할인가]             " + pdatas.get(i).getSalePrice()+"￦");
         System.out.print("[재  고]            " + pdatas.get(i).getCnt());

         if( pdatas.get(i).getCnt() <= 1) {
            System.out.println(" [소량]" + "\n");
         } else {
            System.out.println();
         }

      }


      System.out.println("--------------------------------");
   }


   public int priceSelect(ArrayList<ProductVO> pdatas) { // 가격 검색
      while (true) {
         System.out.println("찾으시는 상품의 가격대 입력 : ");
         int price = 0;
         try {
            price = sc.nextInt();
         } catch (Exception e) {
            System.out.println("정수로 입력해주세요.\n");
            sc.nextLine();
            continue;
         }
         if (price <= 0) {
            System.out.println("1원 이상 입력해주세요.\n");
            continue;
         }
         return price;
      }
   }

   public String productSelect() { // 상품 이름 검색
      System.out.println("찾으시는 상품의 이름(키워드) 입력: ");

      String name = sc.nextLine().toUpperCase();
      return name;
   }



   public int selectOne() { // 선택상품삭제 //오버로딩
      System.out.print("삭제할 상품번호 입력: ");
      int num = 0;
      try {
         num = sc.nextInt();
         System.out.println();
      } catch (Exception e) {
         System.out.println("정수로 입력해주세요.\n");

      }
      return num;

   }

   public void zeroCart() {
      System.out.println("장바구니가 비어있습니다. 메뉴로 돌아갑니다.");
   }

   public int searchMenu() { // 검색
      int action;
      while (true) {
         try {
            System.out.println();
            System.out.println("      [ Search ]\n");
            System.out.println("1. 이름 검색");
            System.out.println("2. 가격 검색");
            System.out.println("0. 뒤로 가기");
            System.out.print("▶ ");
            action = sc.nextInt();
            System.out.println();
            sc.nextLine();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수로 다시 입력해주세요!\n");
            continue;
         }
         if (0 <= action && action <= sAction) {

            break;

         }
         System.out.println("범위를 확인하고 다시 입력해주세요.\n");
      }

      return action;
   }

   public int getPrice(int price) {
      System.out.println(" 총 액: " + price);
      return price;
   }

   public void searchZero() {// 제품이 없을때
      System.out.println("찾으시는 상품이 없습니다.\n");
   }

   public void zeroProduct() {
      System.out.println("재고 없음");
   }


   public void salesCheck( ProductVO pvo) { // 매출확인
      System.out.println("      << Check Sales >>\n");
      System.out.println("매출액: " + pvo.getSalePrice() +"￦\n");
   }

   public void powerOff() { // 프로그램 종료
      System.out.println("프로그램을 종료합니다.\n");
      System.out.println("      .-'\\\r\n"
            + "   .-'  `/\\\r\n"
            + ".-'      `/\\\r\n"
            + "\\         `/\\\r\n"
            + " \\         `/\\\r\n"
            + "  \\    _-   `/\\       _.--.\r\n"
            + "   \\    _-   `/`-..--\\     )\r\n"
            + "    \\    _-   `,','  /    ,')\r\n"
            + "     `-_   -   ` -- ~   ,','\r\n"
            + "      `-              ,','\r\n"
            + "       \\,--.    ____==-~\r\n"
            + "        \\   \\_-~\\\r\n"
            + "         `_-~_.-'\r\n"
            + " _      _ \\-~\n");
   }

}