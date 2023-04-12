package cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class OXOP<T> extends AlgoritmosCruce<T>{
	
	private Random rand;

	public OXOP(double probCruce) {
		super(probCruce);
		rand= new Random();
	}

	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {
		
		//Elegimos el numero de posiciones a seleccionar
		int[] posiciones= new int[]{	rand.nextInt(0,padre1.getCromosoma().size()),
				rand.nextInt(0,padre1.getCromosoma().size()), rand.nextInt(0,padre1.getCromosoma().size()),
				rand.nextInt(0,padre1.getCromosoma().size())};

		// Comprobamos que sean diferentes
		while(posiciones[0]== posiciones[1]||posiciones[1]==posiciones[2]||posiciones[2]==posiciones[0] ||
				posiciones[3]==posiciones[1] || posiciones[3]==posiciones[0] || posiciones[3]==posiciones[2]) {
			posiciones[1] = rand.nextInt(0,padre1.getCromosoma().size());
			posiciones[2] = rand.nextInt(0,padre1.getCromosoma().size());
			posiciones[3] = rand.nextInt(0,padre1.getCromosoma().size());
		}
		
		//Para cada hijo aplica el argoritmo sobre el padre correspondiente
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		Individuo<T> hijo1 = algoritmo(padre1, padre2, posiciones);
		Individuo<T> hijo2 = algoritmo(padre2, padre1, posiciones); 
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		return hijos;
	}
	
	//funcion que aplica el algoritmo OX-OP
	public Individuo<T> algoritmo(Individuo<T> padre1, Individuo<T> padre2, int[] posiciones){
		
		Individuo<T> hijo = crear(padre1);
		Collections.fill(hijo.getCromosoma(),null);
		List<Integer> posOtropadre = new ArrayList<Integer>(posiciones.length);
		
		//COnseguimos las posiones en el padre2 de los elemenestos del padre1 con las posicones originales
		for(int i = 0; i<posiciones.length;i++){
			T tmp = padre1.getCromosoma().get(posiciones[i]);
			posOtropadre.add(padre2.getCromosoma().indexOf(tmp));
		}
		
		//Copiamos los elemtnos del padre2 sin tener en cuenta las posciones obtenidas
		for(int i = 0; i<padre1.getCromosoma().size();i++){
			if(!posOtropadre.contains(i)) {
				T tmp = padre2.getCromosoma().get(i);
				hijo.getCromosoma().set(i, tmp);
			}
		}
		
		Collections.sort(posOtropadre);
		
		//copiamos los elemntos de las posicones originales
		for(int i = 0; i<posiciones.length;i++){
			T tmp = padre1.getCromosoma().get(posiciones[i]);
			hijo.getCromosoma().set(posOtropadre.get(i), tmp);
		}
		
		return hijo;
	}
}
