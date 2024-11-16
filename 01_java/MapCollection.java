package day16;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class MapCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// REST : 리소스와 http명령어로 웹 접속을 하는 프로토콜
		HashMap<String, Integer> hm = new HashMap();  // <  > : Generic
		TreeMap<String, Integer> tm = new TreeMap();
		
		// (1) put() : 데이터 저장
		String[] food = {"Steak", "Chicken", "Rice", "Curri"};
		int[] price = {10000, 15000, 9000, 500};
		
		for(int i=0; i<food.length; i++) {
			hm.put(food[i], price[i]);
			tm.put(food[i], price[i]);
		}
		
		System.out.println("HashMap : " + hm);
		System.out.println("TreeMap : " + tm);
		
		// (2) get() : 데이터 조회
		System.out.println("Chicken : " + hm.get("Chicken"));
		
		// 모두 꺼내고 싶을 때
		// 1) Enhanced for
		for(String key : hm.keySet()) {
			System.out.println(key + " : " + hm.get(key));
		}
		
		// 2) Iterator()
		Iterator keys = tm.keySet().iterator();
		
		while(keys.hasNext()) {
			String key = (String)keys.next();
			System.out.println(key + " : " + tm.get(key));
		}
		
		// (3) remove(키값) : 데이터 삭제, replace(키값, Value) : 해당 키의 데이터를 Value로 변경
		hm.remove("Rice");
		hm.replace("Steak", 1000);
		System.out.println("New HM : " + hm);
		
		// Map Collection은 차후 등장할 JSON 형식을 해석하는데 유용하다.
		
	}

}
