package model;

public class ProductVO {
	private int num; // PK, 제품 번호
	private int cnt; // 재고
	private int realPrice;	// 일반 가격(할인 전 가격)
	private int salePrice;	// 판매 가격(할인 가격, 할인 안하는 제품 가격)
	private String name; // 제품 이름
	
	// getter, setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(int realPrice) {
		this.realPrice = realPrice;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ProductVO [num=" + num + ", cnt=" + cnt + ", realPrice=" + realPrice + ", salePrice=" + salePrice
				+ ", name=" + name + "]";
	}

	
	
}
