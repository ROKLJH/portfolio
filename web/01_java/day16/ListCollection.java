package day16;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. ArrayList
		ArrayList ar = new ArrayList();
		
		// (1) 리스트에 요소 추가(add)
		ar.add("돈까스");
		ar.add("치킨까스");
		ar.add("쌀국수");
		ar.add("라면");
		System.out.println(ar);
		
		for(int i=0; i<ar.size(); i++) {
			System.out.println(i + " : " + ar.get(i));  // get(i) i에 해당하는 데이터 가져오기
		}
		
		// (2) 요소 삭제
		ar.remove(1);
		ar.remove("돈까스");
		System.out.println("[메뉴 2개 삭제 후] " + ar);
		
		// (3) 요소 변경
		ar.set(0, "스파게티");
		System.out.println("[메뉴 변경 후] " + ar);

		try {
			System.out.println("[메뉴 변경 후] " + ar.get(100));
		}
		catch(Exception e) {
			System.out.println("[메뉴 변경 후] " + "니가 직접 해먹어");
		}
		
		// (4) size() : 요소 개수 구하기
		System.out.println("총 메뉴 개수는 " + ar.size() + "개 입니다");
		
		// (5) contains() : 요소 있는지 알아보기(true, false)
		System.out.println("contains : 라면 있나요 - " + (ar.contains("라면")? "응":"아니"));
		System.out.println("contains : 돈까스 있나요 - " + ar.contains("돈까스"));
		
		// 2. LinkedList
		LinkedList link = new LinkedList();
		link.add("슬램덩크");
		link.add(0, "트위스터스");
		link.add(1, "사랑의하츄핑");
		System.out.println("LinkedList : " + link);
		
		// 3. Performance
		ArrayList app1 = new ArrayList();
		LinkedList app2 = new LinkedList();
		
		final int SAMPLE = 10000;
		for(int i=0; i<SAMPLE; i++) {
			app1.add(i);
			app2.add(i);
		}
		
		// read test
		long start, mid, end;
		start = System.currentTimeMillis();
		for(int i=0; i<SAMPLE; i++) {
			app1.get(i);
		}
		mid = System.currentTimeMillis();
		for(int i=0; i<SAMPLE; i++) {
			app2.get(i);
		}
		end = System.currentTimeMillis();
		
		System.out.println("***** Read Competition *****");
		System.out.println("ArrayList : " + (mid-start));
		System.out.println("LinkedList : " + (end-mid));
		
		// insert test
		start = System.currentTimeMillis();
		for(int i=0; i<SAMPLE; i++) {
			app1.add(7000, i);
		}
		mid = System.currentTimeMillis();
		for(int i=0; i<SAMPLE; i++) {
			app2.add(7000, i);
		}
		end = System.currentTimeMillis();
		
		System.out.println("***** Insert Competition *****");
		System.out.println("ArrayList : " + (mid-start));
		System.out.println("LinkedList : " + (end-mid));
	}

}
