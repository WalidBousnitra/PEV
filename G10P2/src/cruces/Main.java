package cruces;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		List<Integer> p1 = new ArrayList<Integer>(6);
		p1.add(1);
		p1.add(2);
		p1.add(3);
		p1.add(5);
		p1.add(6);
		p1.add(4);
		List<Integer> p2 = new ArrayList<Integer>(6);
		p2.add(4);
		p2.add(3);
		p2.add(2);
		p2.add(1);
		p2.add(5);
		p2.add(6);
		
		List<List<Integer>> hijos = pareja(p1,p2);
		
		for(int i = 0; i<6;i++) {
			System.out.print(hijos.get(0).get(i)+"-");
		}
		System.out.print("\n");
		for(int i = 0; i<6;i++) {
			System.out.print(hijos.get(1).get(i)+"-");
		}
	}
	
	public static List<List<Integer>> pareja(List<Integer> padre1, List<Integer> padre2) {

		List<Integer> listaDin = rellenarLista(padre1.size());
		List<Integer> listaDin2 = rellenarLista(padre1.size());
		List<List<Integer>> hijos = new ArrayList<List<Integer>>(2);
		List<Integer> hijo1 = new ArrayList<Integer>(padre1);
		List<Integer> hijo2 = new ArrayList<Integer>(padre2);

		for(int i = 0; i< padre1.size();i++){
			int pos = listaDin.indexOf(padre1.get(i));
			int pos2 = listaDin2.indexOf(padre2.get(i));
			padre1.set(i, pos);
			listaDin.remove(pos);
			padre2.set(i, pos2);
			listaDin2.remove(pos2);
		}
		
		List<List<Integer>> tmp = pareja2(padre1, padre2);
		
		padre1 = tmp.get(0);
		padre2 = tmp.get(1);
		
		listaDin = rellenarLista(padre1.size());
		listaDin2 = rellenarLista(padre1.size());
		
		for(int i = 0; i< padre1.size();i++){
			int pos = padre1.get(i);
			int pos2 = padre2.get(i);
			hijo1.set(i, listaDin.get(pos));
			listaDin.remove(pos);
			hijo2.set(i, listaDin2.get(pos2));
			listaDin2.remove(pos2);
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}
	
	public static List<Integer> rellenarLista(int size){
		List<Integer> sol = new ArrayList<Integer>(size);
		for(int i = 1; i<size+1; i++) {
			sol.add(i);
		}
		return sol;
	}
	
	public static List<List<Integer>> pareja2(List<Integer> padre1, List<Integer> padre2) {
		

		List<List<Integer>> hijos = new ArrayList<List<Integer>>(2);
		List<Integer> hijo1 = new ArrayList<Integer>(padre1);
		List<Integer> hijo2 = new ArrayList<Integer>(padre2);
		
		int puntoCorte = 3;
		
		for(int i = puntoCorte; i < padre1.size();++i) {
			hijo1.set(i, padre2.get(i));
			hijo2.set(i, padre1.get(i));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
