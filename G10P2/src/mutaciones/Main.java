package mutaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Integer> individuo = new ArrayList<Integer>();
		for(int i= 0; i< 10; i++) {
			individuo.add(i);
		}
		cromosoma(individuo);
		
		System.out.println(individuo);
	}
	
	public static <T> void cromosoma(List<Integer> individuo) {
		
		//Elegimos los 2 genes a intercambiar
		int pos1 = 3;
		int pos2 = 6;
		
		for(int i = 0; i< (pos2-pos1+1)/2; i++) {
			Collections.swap(individuo, pos1+i, pos2-i);
		}
	}


}
