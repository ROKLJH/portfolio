package model;

import java.util.ArrayList;

public class MemberDAO {
	ProductVO pvo;
	ArrayList<MemberVO> mdatas;
	public MemberDAO() { // 시작시 데이터
		mdatas=new ArrayList<MemberVO>();
		mdatas.add(new MemberVO("admin", "admin", "관리자")); // 관리자
		mdatas.add(new MemberVO("kim", "1234", "김", 1000000)); // 초기 임시데이터
	} // 시작시 데이터 끝

	public boolean checkId(MemberVO mvo) { // ID 중복 확인
		for(int i=0;i<mdatas.size();i++) { // 배열돌면서 id검사
			if(mdatas.get(i).getId().equals(mvo.getId())) { // 중복인것 찾으면
				System.out.println("로그 : 아이디 중복");
				return false;
			}
		}
		return true;
	}

	public boolean addMember(MemberVO mvo) { // 회원가입
		if(!(mvo==null)) {
			mdatas.add(new MemberVO(mvo.getId(), mvo.getPw(), mvo.getName()));
			System.out.println("로그 : 회원가입 성공");
			return true; // 성공시
		}
		System.out.println("로그 : 회원가입 실패");
		return false; // 실패시
	} // 회원가입 끝

	public MemberVO login(MemberVO mvo) { // 로그인
		for(int i=0;i<mdatas.size();i++) { // 배열 검사
			if(mdatas.get(i).getId().equals(mvo.getId())) { // ID 찾기
				System.out.println("로그 : 아이디 있음");
				if(mdatas.get(i).getPw().equals(mvo.getPw())) { // 비밀번호 찾기
					System.out.println("로그 : 비밀번호 일치");
					System.out.println("로그 : 로그인 성공");
					return mdatas.get(i); // i번지 값을 반환
				}
			}
		}
		System.out.println("로그 : 로그인 실패"); // ID나 PW둘중 하나라도 일치하지 않으면
		return null; // null을 반환
	} // 로그인 끝

	public boolean buyMember(MemberVO mvo) { // 구매시 잔액 차감
		for(int i=0;i<mdatas.size();i++) {
			if(mdatas.get(i).getId().equals(mvo.getId())) { // 구매자 id 찾기
				if(mdatas.get(i).getMoney() < pvo.getSalePrice()) { // sellPrice가 mvo.getMoney보다 크다면
					System.out.println("로그 : 잔액부족");
					return false; // 잔액부족으로 구매불가
				} else { // 갖고있는 돈이 판매가보다 많거나 같다면
					mdatas.get(i).setMoney(mdatas.get(i).getMoney()-pvo.getSalePrice()); // 잔액 차감
					break;
				}
			}
		}
		System.out.println("로그 : 물건구매 성공");
		return true; // for문 탈출
	}
}