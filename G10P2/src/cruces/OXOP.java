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
		int numpos = rand.nextInt(0,padre1.getCromosoma().size()+1);
		
		//Elegimos los 2 genes a intercambiar forzando que la pos1 sea menos que pos2
		int pos1 = rand.nextInt(0,padre1.getCromosoma().size()/2);
		int pos2 = rand.nextInt(padre1.getCromosoma().size()/2,padre1.getCromosoma().size());
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		Individuo<T> hijo1 = crear(padre2);
		Collections.fill(hijo1.getCromosoma(),null);  
		Individuo<T> hijo2 = crear(padre1);
		Collections.fill(hijo2.getCromosoma(),null); 
		
		//copiamos las posiciones pero invertidas
		hijo1.getCromosoma().set(pos1, padre2.getCromosoma().get(pos1));
		hijo2.getCromosoma().set(pos1, padre1.getCromosoma().get(pos1));
		hijo1.getCromosoma().set(pos2, padre2.getCromosoma().get(pos2));
		hijo2.getCromosoma().set(pos2, padre1.getCromosoma().get(pos2));
		
		//colocar el resto de genes
		for(int i = padre1.getCromosoma().size()-1 ; i>=0;i--) {
			if(i!=pos1 && i!=pos2) {
				//hijo1.getCromosoma().set(i, seleccion(i,padre1,hijo1));
				//hijo2.getCromosoma().set(i, seleccion(i,padre2,hijo2));
			}
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		return hijos;
	}

}
