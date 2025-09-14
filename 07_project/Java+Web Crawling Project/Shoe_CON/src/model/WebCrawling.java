package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawling {
	public static void sample(ProductDAO pdao) { // pdao: App에서 사용하는 model

		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();
		// 중복 검사를 위한 배열리스트

		/*
		 * sample data Crawling을 위한 목표 사이트
		 * final String url = "https://www.camper.com/ko_KR/men/shoes";
		 */
		
		final String url = "https://www.camper.com/ko_KR/men/shoes";
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * [필요한 데이터]
		 * 신발 이름
		 * 신발 할인 전 가격
		 * 신발 할인 후 판매 가격
		 */
		
		/*
		 * 제품 이름과 할인 전 가격은 겹치는 정보가 많아
		 * getElementsByClass로 클래스명을 직접 지목해서 데이터를 가져옴
		 */

		// 제품 이름 크롤링
		Elements eles = doc.getElementsByClass("style_name__2sV4b"); // class 이름으로 찾기
		Iterator<Element> itrN = eles.iterator();
		// 제품 원래 가격 크롤링
		Elements eles2 = doc.getElementsByClass("style_price__1FG3L");
		Iterator<Element> itrRP = eles2.iterator();
		// 제품 판매(할인) 가격 크롤링
		Elements eles3 = doc.select("div > p > s");	// html 태그로 찾기
		Iterator<Element> itrSP = eles3.iterator();
		
		/*
		 * 페이지 내 제품 정보가 한 틀에 같이 들어있음 (== 각 정보의 개수가 동일함)
		 * ex) 제품 = [이름], [할인 전 가격], [할인 후 가격]
		 * 
		 * 그러므로 크롤링을 했을 때 이름, 가격 등이
		 * 페이지 정보와 일치하게 가져옴 (직접 실행 후 확인 완료)
		 * 고로 while문을 각각 실행시키지 않고
		 * while(itrN.hashNext()) 하나만 만들어서
		 * 그 안에서 이름, 할인 전 가격, 할인 후 가격 정보를 저장하게 만듦
		 */
		
		while (itrN.hasNext()) {					// 다음 값이 있다면 while
			
			/*
			 * 크롤링을 하면 제품이 30개 가량 저장되는데 과한 정보량이라고 생각되어
			 * sample data를 10개만 저장하기로 함
			 * 비교를 위해 만들어주었던 pdatas를 이용하여
			 * if(pdatas.size()==10){ break; }
			 * 를 통해 10개를 저장해주었으면 while문이 끝나도록 만듦
			 */
			
			if(pdatas.size()==10) {
				break;		// 제품 10개를 저장했으면 크롤링 끝
			}
			
			/*
			 * 페이지 내 같은 제품이지만 색상이 다른 경우 다른 틀(제품 정보)에 있어서
			 * 크롤링 시에 같은 제품을 중복해서 가져오는 것을 확인
			 * (컴퓨터는 PK 번호로 데이터를 분류하기 때문)
			 * 
			 * 우리는 색상을 분류하지 않으므로 같은 제품을 배제하기로 함
			 * 이를 구분해주기 위해 flag 변수인
			 * boolean isOverlap = false; 정의
			 * 정상적으로 크롤링 된 정보와 앞으로 크롤링 되는 정보를 비교해주기 위한
			 * pdatas.add(pvo); 를 작성
			 */

			boolean isOverlap 	= false;				// 중복 검사를 위한 flag
			ProductVO pvo = new ProductVO();
			String name = itrN.next().text();		// 제품 이름
			pvo.setName(name);
			
			/*
			 * while문을 돌면서
			 * 만약, 가져와서 저장해준(pvo) 이름이
			 * 여태 저장한 정보(pdatas)의 이름과 동일한 것이 있다면
			 * isOverlap을 true로 바꾸어주고
			 * if문을 사용하여
			 * 저장시키지 않고 다음 제품을 크롤링하도록 continue;를 사용
			 */
			
			for(int i=0; i<pdatas.size(); i++) {	// 중복 제품 검사
				if(pdatas.get(i).getName().contains(pvo.getName())) {
					isOverlap = true;
					break;
				}
			}
			if(isOverlap) {	// 중복 제품이 있다면
				continue;	// 저장하지 않고 다음 제품 크롤링
			}
			
			/*
			 * 크롤링해서 가져온 가격 데이터는 dao에서 연산 시에 필요하기 때문에
			 * String 타입이 아닌 int 타입으로 변환이 필요함
			 * 그러나 가격 정보를 해당 페이지에서 크롤링해서 가져오면
			 * 원화(\) 표시와 반점(,)이 함께 text로 가져오게 됨
			 * 이를 int타입으로 형변환 시켜주기 위해 두 가지 메서드를 생성해줌
			 * [메서드는 밑에 있음]
			 */
				
			String realPrice = itrRP.next().text();	// 할인 전 가격(원래 가격)
			String salePrice = itrSP.next().text(); // 판매 가격
			
			int realIntPrice = toInt(removeNotNumeric(realPrice));
			int saleIntPrice = toInt(removeNotNumeric(salePrice));
			// 숫자만 남기고 모두 제거 후 Int로 형변환

			pvo.setSalePrice(realIntPrice);
			pvo.setRealPrice(saleIntPrice);

			pdatas.add(pvo);	// 중복 검사를 위해 객체 저장
			pdao.insert(pvo);
		}
	}

	/*
	 * removeNotNumeric(String str)
	 * : replaceAll 메서드를 사용했고 정규식 \W로
	 * 알파벳 + 숫자 + _ 가 아닌 문자를 "" 로 바꾸어줌 (==해당 문자를 없앰)
	 */
	
	private static String removeNotNumeric(String str) {
		return str.replaceAll("\\W", "");
		//         replaceAll(정규식 or 기존문자, 대체문자), 기존문자를 대체문자로 바꿔줌
		//        \W는 문자+숫자가 아닌 것 매치
		//         즉, \W를 사용해서 숫자나 문자가 아닌 것들을 ""로 지워줌
		//        return str.replaceAll("\\₩", ""); 보다 효과적
	}

	/*
	 * toInt(String str)
	 * : Integer.parseInt를 사용하여 String타입의 문자열을
	 * int타입으로 변환해줌
	 */
	
	private static int toInt(String str) {	// String 값을
		return Integer.parseInt(str);		// int로 바꿔주는 메소드
	}







}