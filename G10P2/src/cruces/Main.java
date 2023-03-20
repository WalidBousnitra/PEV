package cruces;

import java.util.ArrayList;
import java.util.Collections;
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
		//Elegimos los 2 genes a intercambiar forzando que la pos1 sea menos que pos2
		int pos1 = 2;
		int pos2 = 4;
		List<List<Integer>> hijos = new ArrayList<List<Integer>>(2);
		List<Integer> hijo1 = padre2;
		Collections.fill(hijo1,null);  
		List<Integer> hijo2 = padre1;
		Collections.fill(hijo2,null); 
		
		//copiamos el segmento invertido
		for(int i = pos1; i<=pos2;i++) {
			hijo1.set(i, padre2.get(i));
			hijo2.set(i, padre1.get(i));
		}
		
		//colocar el resto de genes
		for(int i = 0 ; i< padre1.size();i++) {
			if(i<pos1 || i>pos2) {
				hijo1.set(i, seleccion(i,padre1,padre2,hijo1));
				hijo2.set(i, seleccion(i,padre2,padre1,hijo2));
			}
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		return hijos;
	}
	
	//funcion que devuelve el homologo en caso de que tenga conflito
	public static Integer seleccion(int i,List<Integer> padre1, List<Integer> padre2,List<Integer> hijo) {
		
		Integer elem = padre1.get(i);
		
		while(hijo.contains(elem)){
			elem = padre1.get(padre2.indexOf(elem));
		}
		
		return elem;
	}
			/*

		crearMatriz(padre1,padre2);
		saca();
		List<List<Integer>> hijos = new ArrayList<List<Integer>>(2);
		List<Integer> hijo1 = padre2;
		List<Integer> hijo2 = padre1;
		
		for(int i = 1; i<padre1.size();i++) {
			int cosica =selecionar(padre2.get(i-1));
			hijo1.set(i, cosica);
		}
		reset();
		crearMatriz(padre1,padre2);
		for(int i = 1; i<padre2.size();i++) {
			int cosica2 =selecionar(padre1.get(i-1));
			hijo2.set(i, cosica2);
		}		
		
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		reset();
		return hijos;
	}
	
	private static void reset() {
		mAdy.clear();
		for(int i = 0; i<=9;i++) {
				mAdy.put(i, new ArrayList<Integer>());
		}
	}
	
	public static void saca() {
		for(Integer key : mAdy.keySet()) {
			System.out.println(key + ":    " + mAdy.get(key));
		}
	}
	
	//fucion que devulve el elemento a sustituir en el gen
	public static Integer selecionar(Integer elem) {
		int newElem = mAdy.get(elem).get(0);
		int min = mAdy.get(mAdy.get(elem).get(0)).size();
		for(int i = 1; i<mAdy.get(elem).size();i++) {
			if(mAdy.get(mAdy.get(elem).get(i)).size()<min) {
				newElem = mAdy.get(elem).get(i);
				min = mAdy.get(mAdy.get(elem).get(i)).size();
			}
		}
		
		mAdy.remove(elem);
		for(List<Integer> obj : mAdy.values()) {
			for(int i = 0 ; i<obj.size();i++) {
				if(obj.get(i)==elem) {
					obj.remove(i);
					i--;
				}
			}
		}
		
		return newElem; 
	}
	
	
	//Funcion para crear la matriz de adyacencia en para cada par de padres
	public static void crearMatriz(List<Integer> padre1, List<Integer> padre2){
		
		//Añadimos la adyacencia de los primeros elementos porque no tiene elemento anterior
		mAdy.get(padre1.get(0)).add(padre1.get(1));
		mAdy.get(padre1.get(0)).add(padre1.get(padre1.size()-1));
		
		if(!mAdy.get(padre2.get(0)).contains(padre2.get(1)))
			mAdy.get(padre2.get(0)).add(padre2.get(1));
		if(!mAdy.get(padre2.get(0)).contains(padre2.get(padre2.size()-1)))
			mAdy.get(padre2.get(0)).add(padre2.get(padre2.size()-1));
		
		for(int i  = 1;i <padre1.size();i++) {
			//Para cada elemento añadimos el elemento anterior y posterior
			if(!mAdy.get(padre1.get(i)).contains(padre1.get(i-1)))
				mAdy.get(padre1.get(i)).add(padre1.get(i-1));
			if(!mAdy.get(padre1.get(i)).contains(padre1.get((i+1)%padre1.size())))
				mAdy.get(padre1.get(i)).add(padre1.get((i+1)%padre1.size()));
			if(!mAdy.get(padre2.get(i)).contains(padre2.get(i-1)))
				mAdy.get(padre2.get(i)).add(padre2.get(i-1));
			if(!mAdy.get(padre2.get(i)).contains(padre2.get((i+1)%padre1.size())))
				mAdy.get(padre2.get(i)).add(padre2.get((i+1)%padre1.size()));
		}		
	}*/
}
