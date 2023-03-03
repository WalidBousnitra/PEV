package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Uniforme<T> extends AlgoritmosCruce<T>{
	
	private Random rand = new Random();
	
	public Uniforme(String funcion, double p) {
		super(funcion,p);
	}

	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {
		
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		
		Individuo<T> hijo1 = crear(padre1);
		Individuo<T> hijo2 = crear(padre2);
		
		for(int i = 0; i < padre1.getCromosoma().size(); i++)
			if(rand.nextDouble() <= p) {
				hijo1.getCromosoma().set(i, padre2.getCromosoma().get(i));
				hijo2.getCromosoma().set(i, padre1.getCromosoma().get(i));
			}
		
		hijos.add(hijo1);
		hijos.add(hijo2);		
		
		return hijos;
	}

}
