package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Monopunto extends AlgoritmosCruce{

	private Random rand;
	
	@Override
	public <T> List<Individuo<T>> cruzar(Individuo<T> padre1, Individuo<T> padre2, double p) {

		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>();
		
		int puntoCorte = rand.nextInt(1,padre1.getTamTotal());
		
		Individuo<T> hijo1 = new Individuo<T>();
		Individuo<T> hijo2 = new Individuo<T>();
		
		for(int i = 0; i < puntoCorte;++i) {
			hijo1.getCromosoma()[i] = padre1.getCromosoma()[i];
			hijo2.getCromosoma()[i] = padre2.getCromosoma()[i];
		}
		for(int i = puntoCorte; i < padre1.getTamTotal();++i) {
			hijo1.getCromosoma()[i] = padre2.getCromosoma()[i];
			hijo2.getCromosoma()[i] = padre2.getCromosoma()[i];
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
