package day17;

import java.util.ArrayList;

public class Generic1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Food ramen = new Food("라면", "그냥 끓이면 됨 아님 날로 먹음");
		
		// OldBox
		OldBox ob = new OldBox();
		ob.add(ramen);
		System.out.println(((Food)(ob.get(0))).getRecipe());
		
		// NewBox
		NewBox<Food> nb = new NewBox();
		nb.add(ramen);
		//  nb.add(new String("AAA"));  // Generic을 사용하면 정한 타입 외에는 사용 불가
		System.out.println(nb.get(0).getRecipe());
	}

}

class Food {
	String name;
	String recipe;
	
	Food(String name, String recipe){
		this.name = name;
		this.recipe = recipe;
	}
	
	String getRecipe() {
		return recipe;
	}
}

class OldBox {
	ArrayList item = new ArrayList();
	
	void add(Object o) {
		item.add(o);
	}
	
	Object get(int index) {
		return item.get(index);
	}
}

class NewBox<T> {
	// T, E, (K, V) : T=Type, E=Element, K=Key, V=Value
	ArrayList<T> item = new ArrayList();
	
	void add(T o) {
		item.add(o);
	}
	
	T get(int index) {
		return item.get(index);
	}
}

class StrangeBox<DisneyLand> {
	DisneyLand name;
}