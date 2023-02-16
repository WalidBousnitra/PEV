package cruces;

import java.util.List;

import Individuos.Individuo;

public abstract class AlgoritmosCruce{
	
	public abstract  <T> List<Individuo<T>> cruzar(Individuo<T> padre1, Individuo<T> padre2, double p);
}