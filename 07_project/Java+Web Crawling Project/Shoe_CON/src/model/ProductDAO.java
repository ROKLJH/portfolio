package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ProductDAO {
	int PK;
	int cnt;
	int cartSize;
	ArrayList<ProductVO> pdatas;
	HashMap<String, Integer> cdatas;
	private ArrayList<ProductVO> cantBuyDatas;
	private ArrayList<Integer> salesPriceDatas;
	private ArrayList<String> salesNameDatas;


	public ArrayList<ProductVO> getCantBuyDatas() {
		return cantBuyDatas;
	}

	public void setCantBuyDatas(ArrayList<ProductVO> cantBuyDatas) {
		this.cantBuyDatas = cantBuyDatas;
	}

	public ArrayList<String> getSalesNameDatas() {
		return salesNameDatas;
	}

	public void setSalesNameDatas(ArrayList<String> salesNameDatas) {
		this.salesNameDatas = salesNameDatas;
	}

	public ArrayList<Integer> getSalesPriceDatas() {
		return salesPriceDatas;
	}

	public void setSalesPriceDatas(ArrayList<Integer> salesPriceDatas) {
		this.salesPriceDatas = salesPriceDatas;
	}


	public ProductDAO() {
		PK = 1001;
		cnt = 0;
		pdatas = new ArrayList<ProductVO>();		// 판매 물건 배열
		cantBuyDatas = new ArrayList<ProductVO>();	// 구매하지 못한 물건
		cdatas = new HashMap<String, Integer>();	// 장바구니
		salesPriceDatas = new ArrayList<Integer>();	// 판매한 제품 가격 
		salesNameDatas = new ArrayList<String>();	// 판매한 제품 이름
	}

	public boolean checkProduct(ProductVO pvo) {   // 제품 확인 로직
		for(int i=0;i<pdatas.size();i++) {
			if(pdatas.get(i).getNum()==pvo.getNum()) {
				System.out.println("   로그: 제품 있음");
				pvo.setName(pdatas.get(i).getName());
				return true;
			}
		}
		System.out.println("   로그: 제품 없음");
		return false;
	}
	public ProductVO checkCart(ProductVO pvo){
		if(cdatas.containsKey(pvo.getName())) {
			int i=(cdatas.get(pvo.getName())+pvo.getCnt());
			pvo.setCnt(i);
		}
		return pvo;
	}

	public boolean checkCnt(ProductVO pvo) {   // 재고 충분한지 확인 로직
		for(int i=0;i<pdatas.size();i++) {
			if(pdatas.get(i).getNum() == pvo.getNum()) {            
				if(pdatas.get(i).getCnt()>=pvo.getCnt()) {
					System.out.println("   로그: 재고 있음");
					return true;
				}
				else if(pdatas.get(i).getCnt()==0) {
					System.out.println(" 로그 : 재고 없음");
					return false;

				}
				System.out.println("   로그: 재고 부족");
				pvo.setCnt(pdatas.get(i).getCnt());
				return true;
			}

		}
		return false;
	}

	public boolean insert(ProductVO pvo) {	// 제품 추가 로직
		if(pvo.getName()==null) {
			System.out.println("	로그: 추가 실패");
			return false;
		}
		ProductVO data=new ProductVO();
		data.setNum(PK++);
		data.setName(pvo.getName());
		data.setSalePrice(pvo.getSalePrice());
		data.setRealPrice(pvo.getRealPrice());
		data.setCnt(cnt=new Random().nextInt(5) + 1);	// 재고 랜덤 1~5
		pdatas.add(data);
		System.out.println("	로그: 추가 성공");
		return true;
	}

	public ArrayList<ProductVO> selectAll(ProductVO pvo){	// selectAll
		if(pvo==null) {			// 입력 받은게 없다면
			return pdatas;		// 전체 출력
		}

		ArrayList<ProductVO> datas=new ArrayList<ProductVO>();

		if(pvo.getName()!=null) { //문자검색이면
			for(int i=0; i<pdatas.size(); i++) {	// 이름 검색 (문자 포함 검색)
				
				if(pdatas.get(i).getName().toUpperCase().contains(pvo.getName())) {
					datas.add(pdatas.get(i));
				}
			}
			return datas;
		} else {									// ex) 몇 만원 이하
			for(int i=0;i<pdatas.size();i++) {		// 가격 검색 (가격 이하 검색)
				if(pdatas.get(i).getSalePrice()<=pvo.getSalePrice()) {
					datas.add(pdatas.get(i));
				}
			}
			return datas;
		}
	}

	public ProductVO selectOne(ProductVO pvo) {		// selectOne
		for(int i=0;i<pdatas.size();i++) {
			if(pdatas.get(i).getNum()==pvo.getNum()) {
				return pdatas.get(i);
			}
		}
		return null;
	}

	public boolean updateCnt(ProductVO pvo) {		// 제품 재고 변경
		for(int i=0;i<pdatas.size();i++) {
			if(pdatas.get(i).getNum()==pvo.getNum()) {
				if(pdatas.get(i).getCnt()+pvo.getCnt()<0){
					System.out.println(" 	로그: 재고가 음수");
					return false;
				}
//				System.out.println(pdatas.get(i).getCnt());
//				System.out.println(pvo.getCnt());
				int cnt=pdatas.get(i).getCnt()+pvo.getCnt();
				System.out.println(cnt);
				pdatas.get(i).setCnt(cnt);
				System.out.println("  로그: 정보변경 성공");
				return true;
			}
		}
		System.out.println("  로그: 정보변경 실패");
		return false;
	}

	public HashMap<String, Integer> addCart(ProductVO pvo) {   // 장바구니 담기

		cdatas.put(pvo.getName(), pvo.getCnt());
		return cdatas;
	}

	public void deleteAllCart() {			// 장바구니 전체 비우기
		cdatas.clear();
	}

	public boolean deleteOneCart(ProductVO pvo) {	// 장바구니에서 하나 제거
		boolean isExists;
		String productName;
		for(int i=0; i<pdatas.size(); i++) {	// 실존 제품과 비교
			productName=pdatas.get(i).getName();	// 비교 제품 이름
			isExists = cdatas.containsKey(productName);
			if(isExists) {					// 제품이 있다면
				cdatas.remove(productName);	// 장바구니에서 제거
				System.out.println("	로그: 삭제 성공");
				return true;
			}
		}
		System.out.println("	로그: 삭제 실패");	// 제품이 없었다면
		return false;
	}
	
	
	// 가능한만큼 모듈화 고민 (컨트롤에서 더 사용하기 쉽게 하기 위해)
	// MemberDAO와 함께 사용했을 때도 고려, 유지보수에 용이하기 위해 로직 더 생각해보기
	public int buyCart(HashMap<String, Integer> cdatas) {
		ProductVO pvo=new ProductVO();
		int nowSalesPrice=0;
		int oneCartPrice=0;
		if(cdatas.isEmpty()) {		// 카트가 비어있다면
			return nowSalesPrice;
		}
		boolean isExists;	// 제품이 있는지 확인하는 flag
		String productName;	// 판매 제품 이름 (장바구니x, 판매하고 있는 제품)
		int buyCnt;	// 장바구니에 담은 제품 수량
		int shortCnt=0;	// 모자란 재고 (담은 수량 - 재고)
		while(true) {
			for(int i=0; i<pdatas.size(); i++) {	// 실존 제품과 비교
				productName=pdatas.get(i).getName();	// 비교 제품 이름
				isExists = cdatas.containsKey(productName);
				if(isExists) {		// 제품이 있다면
					buyCnt=cdatas.get(productName); // 장바구니에 담은 제품의 숫자
					if(pdatas.get(i).getCnt()<buyCnt){
						shortCnt=buyCnt-pdatas.get(i).getCnt();	// 모자란 재고
						buyCnt=pdatas.get(i).getCnt();	// 재고만큼 구매 수량 변경
					}

					salesNameDatas.add(pdatas.get(i).getName());	// 구매한 제품 이름 저장
					
					oneCartPrice+=(pdatas.get(i).getSalePrice()*buyCnt);
					nowSalesPrice=(pdatas.get(i).getSalePrice()*buyCnt);
					salesPriceDatas.add(nowSalesPrice);	// 구매한 제품 가격 * 수량 저장

					pdatas.get(i).setCnt(pdatas.get(i).getCnt()-buyCnt); // 구매한만큼 재고 소진
					pvo.setName(productName);
					pvo.setCnt(shortCnt);
					cantBuyDatas.add(pvo);	// 구매하지 못한 제품의 이름과 수량
					cdatas.remove(productName);	// 구매한 물품 장바구니에서 비우기
				}
			}
			if(cdatas.size()<=0) {	// 장바구니가 비워지면
				break;
			}
		}
		return oneCartPrice;
	}
	
	public ProductVO salesPriceAll() {
		ProductVO pvo=new ProductVO();
		int i;
		int allSalesPrice=0;
		for( i=0; i<salesPriceDatas.size(); i++) {
			
			allSalesPrice+=salesPriceDatas.get(i);
			System.out.println("로그 : " +allSalesPrice);
			pvo.setSalePrice(allSalesPrice);
		}
		return pvo;		// 총액
	}

	public boolean checkStock(ProductVO pvo) {	// 재고가 0인지 검사
		for(int i=0;i<pdatas.size();i++) {
			if(pdatas.get(i).getNum()==pvo.getNum()) {	// 제품이 있다면
				if(pdatas.get(i).getCnt()<=0) {
					System.out.println("  로그: 재고 없음");
					return false;
				} else {
					System.out.println("  로그: 재고 있음");
					return true;
				}
			}
		}
		System.out.println("  로그: 제품 없음");
		return false;
	}

}