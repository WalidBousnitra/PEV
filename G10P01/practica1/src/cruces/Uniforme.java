package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Uniforme extends AlgoritmosCruce{

	@Override
	public List<Individuo> cruzar(Individuo padre1, Individuo padre2, double p) {
		
		Random r = new Random();
		List<Object> hijo1 = new ArrayList<>(), hijo2 = new ArrayList<>();
		for(int i = 0; i < padre1.getCromosoma().length ; i++)
			if(r.nextDouble() <= p) {
				hijo1.add(padre2.getCromosoma()[i]);
				hijo2.add(padre1.getCromosoma()[i]);
			}
			else {
				hijo1.add(padre1.getCromosoma()[i]);
				hijo2.add(padre2.getCromosoma()[i]);
			}
		return new ArrayList<Individuo> ();
	}

}
