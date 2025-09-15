package ctrl;

import java.util.HashMap;

import model.MemberDAO;
import model.MemberVO;
import model.ProductDAO;
import model.ProductVO;
import model.WebCrawling;
import view.View;

public class Ctrl {
   MemberDAO mdao;
   ProductDAO pdao;
   View view;

   public Ctrl() {
      mdao = new MemberDAO();
      pdao = new ProductDAO();
      view = new View(); 

   }

   public void startApp() { //
      WebCrawling.sample(pdao);
      while(true) {
         int action = view.startMenuPrint(); // 시작 메뉴
         if (action == 1){ //로그인
            MemberVO mvo = view.login();
            mvo = mdao.login(mvo);
            if(mvo==null) {
               view.loginFalse();
               continue;
            }
            view.hello(mvo);

            if ( mvo.getId().equals("admin")) { 
               while(true) {

                  action = view.adminMenuPrint();// 관리자 메뉴

                  if (action == 1) { //상품 재고 관리
                     ProductVO pvo = new ProductVO();
                     view.selectAll(pdao.selectAll(null));
                     pvo.setNum(view.getInt("재고 추가할 상품번호"));
                     if(!pdao.checkProduct(pvo)) {
                        view.nullProduct();
                        continue;
                     }
                     pvo.setCnt(view.getInt("추가 수량"));
                     if(!pdao.updateCnt(pvo)) {
                        view.isFalse("재고 추가 ");
                        continue;
                     }
                     view.isTrue("재고 추가 ");
                     continue;


                  } else if(action == 2 ){ //상품목록 출력 
                     view.selectAll(pdao.selectAll(null));

                  }else if (action == 3) { // 매출 확인
                	 ProductVO pvo = pdao.salesPriceAll();
                     view.salesCheck(pvo);   


                  }else if (action == 0) { //로그아웃 
                     view.logout();
                     break;
                  }
               }
            }else {
               while(true) {
                  action = view.userMenuPrint();// 사용자 메뉴로 이동

                  HashMap<String,Integer> cdatas =new HashMap<String,Integer>();
                  if (action == 1) { //상품보기
                     view.selectAll(pdao.selectAll(null));
                     while(true) {

                        action = view.userBuyPrint();

                        if(action==1) { //검색
                           ProductVO pvo = new ProductVO();
                           action = view.searchMenu();
                           if(action ==1) {
                              pvo.setName(view.productSelect());

                              if(pdao.selectAll(pvo).size() == 0) {
                                 view.searchZero(); // 상품 없을 때
                                 break;
                              }
                              else {
                                 view.selectAll(pdao.selectAll(pvo));
                                 break;
                              }

                           }else if(action==2) {
                              pvo.setSalePrice(view.priceSelect(null));

                              if(pdao.selectAll(pvo).size() == 0) {
                                 view.searchZero(); // 상품 없을 때
                                 break;
                              }
                              else {
                                 view.selectAll(pdao.selectAll(pvo));
                                 break;
                              }
                           }




                        } else if (action ==2) {// 장바구니 
                        	  ProductVO pvo = new ProductVO();
                              pvo.setNum(view.buyProduct()); 
                              if(!pdao.checkProduct(pvo)) {
                                 view.nullProduct();
                                 continue;
                              }
                              pvo.setCnt(view.getCnt());
                              pvo=pdao.checkCart(pvo);
                              if(!pdao.checkCnt(pvo)) {
                                 view.zeroProduct();
                                 continue;
                              
                              }
                              cdatas=pdao.addCart(pvo);
                              
                              view.printCart(cdatas);
                              continue;
                              





                        }else if (action == 3) {// 구매
                           view.printCart(cdatas);
                           if(cdatas.isEmpty()) {                        	   
                           view.zeroCart();
                           continue;
                           }
                           view.getPrice(pdao.buyCart(cdatas));
                           view.isTrue("구매");
                           break;
                        }else if(action ==0) {
                        	break;
                        }


                     }

                  }else if (action == 0) { //로그아웃 
                	  pdao.deleteAllCart();
                     view.logout();
                     break;
                  }
               }
            }

         }else if ( action == 2 ) { // 회원가입
            MemberVO mvo = new MemberVO();
            while(true) {

               mvo.setId(view.getString("ID"));
               if(mdao.checkId(mvo)) {
                  break;
               }
               view.overlapId();
               continue;
            }
            while(true) {   
               mvo.setPw(view.getString("PW"));
               if(!view.inputPw(mvo)) {
                  view.overlapPw();
                  continue;
               }
               break;
            }
            mvo.setName(view.getString("NAME"));
            if(mdao.addMember(mvo)){
               view.isTrue("회원가입");

            }else {

               view.isFalse("회원가입");
            }




         }else if ( action == 3 ) { // 프로그램 종료
            view.powerOff();
            break;
         }

      }




   }



}