package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Uniforme extends AlgoritmosCruce{
	
	private Random rand;

	@Override
	public <T> T[] cruzar(T[] padre1, T[] padre2, double p) {
		
		
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>();
		
		T[] hijo1 = new T[];
		Individuo hijo2 = new Individuo();
		
		for(int i = 0; i < padre1.getTamTotal(); i++)
			if(rand.nextDouble() <= p) {
				hijo1.getCromosoma()[i] = padre2.getCromosoma()[i];
				hijo2.getCromosoma()[i] = padre1.getCromosoma()[i];
			}
			else {
				hijo1.getCromosoma()[i] = padre1.getCromosoma()[i];
				hijo2.getCromosoma()[i] = padre2.getCromosoma()[i];
			}
		
		hijos.add(hijo1);
		hijos.add(hijo2);		
		
		return hijos;
	}

}
