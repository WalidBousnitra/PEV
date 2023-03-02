package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Aritmetico<T> extends AlgoritmosCruce<Double>{
	
	private Random rand = new Random();
	
	public Aritmetico(String funcion,double p) {
		super(funcion,p);
	}

	@Override
	public List<Individuo<Double>> pareja(Individuo<Double> padre1, Individuo<Double> padre2) {
		
		List<Individuo<Double>> hijos = new ArrayList<Individuo<Double>>(2);
		Individuo<Double> hijo1 = crear(padre1);
		Individuo<Double> hijo2 = crear(padre2);
		
		double alfa = rand.nextDouble();
		
		for(int i = 0; i < padre1.getCromosoma().size();++i) {
			hijo1.getCromosoma().set(i, alfa*padre1.getCromosoma().get(i) + (1-alfa)*padre2.getCromosoma().get(i));
			hijo2.getCromosoma().set(i, alfa*padre2.getCromosoma().get(i) + (1-alfa)*padre1.getCromosoma().get(i));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}
}
