package cruces;

import java.util.List;

import Individuos.Individuo;

public abstract class AlgoritmosCruce{
	
	public abstract  List<Individuo> cruzar(Individuo padre1, Individuo padre2, double p);
}