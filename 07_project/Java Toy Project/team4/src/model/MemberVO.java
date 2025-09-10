package model;

public class MemberVO {
	private String id; // PK, 아이디
	private String pw; // 비밀번호
	private String name; // 회원 이름
	private int money; // 잔액
	
	// getter, setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public MemberVO() { // 기본 생성자

	}
	public MemberVO(String id, String pw, String name) { // 관리자용
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	public MemberVO(String id, String pw, String name, int money) { // 회원용
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.money = money;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", money=" + money + "]";
	}
}
