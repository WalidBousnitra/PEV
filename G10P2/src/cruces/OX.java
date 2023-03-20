package cruces;

import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class OX<T> extends AlgoritmosCruce<T>{
	
	private Random rand;
	
	public OX(double p) {
		super(p);
		rand = new Random();
	}

	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {
		//Elegimos los 2 genes a intercambiar forzando que la pos1 sea menos que pos2
		int pos1 = rand.nextInt(0,padre1.getCromosoma().size()/2);
		int pos2 = rand.nextInt(padre1.getCromosoma().size()/2,padre1.getCromosoma().size());
		
		
		
		return null;
	}

}
