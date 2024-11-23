package db;

public class HospitalVO {
	String hptl_nm;
	String sido_cd;
	int doc_num;
	
	public void setVO(String name, String sido, int num) {
		hptl_nm = name;
		sido_cd = sido;
		doc_num = num;
	}
	
	// Object 클래스의 후손
	// toString 메서드를 자연스럽게 보유하게 됨
	// toString의 내용이 맘에 안든다면(무조건 안듬)
	// Overriding
	public String toString() {
		return "병원의 이름은 " + hptl_nm + ", 위치는 " + sido_cd 
				+ ", 의사 수는 " + doc_num + "입니다.";
	}
}
