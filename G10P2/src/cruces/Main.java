package cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;


public class Main {

	public static void main(String[] args) {
		List<Integer> p1 = new ArrayList<Integer>(9);
		p1.add(1);
		p1.add(2);
		p1.add(3);
		p1.add(4);
		p1.add(5);
		p1.add(6);
		p1.add(7);
		p1.add(8);
		p1.add(9);
		List<Integer> p2 = new ArrayList<Integer>(9);
		p2.add(4);
		p2.add(5);
		p2.add(2);
		p2.add(1);
		p2.add(8);
		p2.add(7);
		p2.add(6);
		p2.add(9);
		p2.add(3);
		
		List<List<Integer>> hijos = pareja(p1,p2);
		
		for(int i = 0; i<9;i++) {
			System.out.print(hijos.get(0).get(i)+"-");
		}
		System.out.print("\n");
		for(int i = 0; i<9;i++) {
			System.out.print(hijos.get(1).get(i)+"-");
		}
	}
	
public static List<List<Integer>> pareja(List<Integer> padre1, List<Integer> padre2) {
		
		Random rand = new Random();
		//Elegimos el numero de posiciones a seleccionar
		int[] posiciones= new int[]{3,4,6,9};

		// Comprobamos que sean diferentes
		while(posiciones[0]== posiciones[1]||posiciones[1]==posiciones[2]||posiciones[2]==posiciones[0] ||
				posiciones[3]==posiciones[1] || posiciones[3]==posiciones[0] || posiciones[3]==posiciones[2]) {
			posiciones[1] = rand.nextInt(0,padre1.size());
			posiciones[2] = rand.nextInt(0,padre1.size());
			posiciones[3] = rand.nextInt(0,padre1.size());
		}
		
		//Para cada hijo aplica el argoritmo sobre el padre correspondiente
		List<List<Integer>> hijos = new ArrayList<List<Integer>>(2);
		List<Integer> hijo1 = algoritmo(padre1, padre2, posiciones);
		List<Integer> hijo2 = algoritmo(padre2, padre1, posiciones); 
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		return hijos;
	}
	
	//funcion que aplica el algoritmo OX-OP
	public static List<Integer> algoritmo(List<Integer> padre1, List<Integer> padre2, int[] posiciones){
		
		List<Integer> hijo = new ArrayList<Integer>(padre1);
		Collections.fill(hijo, null);
		List<Integer> posOtropadre = new ArrayList<Integer>(posiciones.length);
		
		//COnseguimos las posiones en el padre2 de los elemenestos del padre1 con las posicones originales
		for(int i = 0; i<posiciones.length;i++){
			Integer tmp = padre1.get(posiciones[i]-1);
			posOtropadre.add(padre2.indexOf(tmp));
		}
		
		//Copiamos los elemtnos del padre2 sin tener en cuenta las posciones obtenidas
		for(int i = 0; i<padre1.size();i++){
			if(!posOtropadre.contains(i)) {
				int tmp = padre2.get(i);
				hijo.set(i, tmp);
			}
		}
		
		Collections.sort(posOtropadre);
		
		//copiamos los elemntos de las posicones originales
		for(int i = 0; i<posiciones.length;i++){
			Integer tmp = padre1.get(posiciones[i]-1);
			hijo.set(posOtropadre.get(i), tmp);
		}
		
		return hijo;
	}
}
