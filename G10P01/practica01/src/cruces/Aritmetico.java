package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Aritmetico extends AlgoritmosCruce{
	
	private Random rand = new Random();
	
	public Aritmetico(double p) {
		super(p);
	}

	@Override
	public <T> List<List<T>> pareja(List<T> padre1, List<T> padre2) {
		List<List<T>> hijos = new ArrayList<List<T>>();
		
		List<T> hijo1 = new ArrayList<>(padre1.size());
		List<T> hijo2 = new ArrayList<>(padre1.size());
		
		double alfa = rand.nextDouble();
		
		for(int i = 0; i < padre1.size();++i) {
			//hijo1.add(i, alfa*padre1.get(i) + (1-alfa)*padre2.get(i));
			//hijo2.add(i, alfa*padre2.get(i) + (1-alfa)*padre1.get(i));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}
}
