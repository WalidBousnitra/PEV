package cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class PMX<T> extends AlgoritmosCruce<T>{

	private Random rand;
	
	public PMX(double p) {
		super(p);
		rand = new Random();
	}
	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {
		
		//Elegimos los 2 genes a intercambiar forzando que la pos1 sea menos que pos2
		int pos1 = rand.nextInt(0,padre1.getCromosoma().size()/2);
		int pos2 = rand.nextInt(padre1.getCromosoma().size()/2,padre1.getCromosoma().size());
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		Individuo<T> hijo1 = crear(padre2);
		Collections.fill(hijo1.getCromosoma(),null);  
		Individuo<T> hijo2 = crear(padre1);
		Collections.fill(hijo2.getCromosoma(),null); 
		
		//copiamos el segmento invertido
		for(int i = pos1; i<=pos2;i++) {
			hijo1.getCromosoma().set(i, padre2.getCromosoma().get(i));
			hijo2.getCromosoma().set(i, padre1.getCromosoma().get(i));
		}
		
		//colocar el resto de genes
		for(int i = 0 ; i< padre1.getCromosoma().size();i++) {
			if(i<pos1 || i>pos2) {
				hijo1.getCromosoma().set(i, seleccion(i,padre1,padre2,hijo1));
				hijo2.getCromosoma().set(i, seleccion(i,padre2,padre1,hijo2));
			}
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		return hijos;
	}
	
	//funcion que devuelve el homologo en caso de que tenga conflito
	public T seleccion(int i,Individuo<T> padre1, Individuo<T> padre2,Individuo<T> hijo) {
		
		T elem = padre1.getCromosoma().get(i);
		
		while(hijo.getCromosoma().contains(elem)){
			elem = padre1.getCromosoma().get(padre2.getCromosoma().indexOf(elem));
		}
		
		return elem;
	}

}
