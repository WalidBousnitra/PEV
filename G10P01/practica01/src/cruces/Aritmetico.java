package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aritmetico<T> extends AlgoritmosCruce<Double>{
	
	private Random rand = new Random();
	
	public Aritmetico(double p) {
		super(p);
	}

	@Override
	public List<List<Double>> pareja(List<Double> padre1, List<Double> padre2) {
		List<List<Double>> hijos = new ArrayList<List<Double>>();
		
		List<Double> hijo1 = new ArrayList<Double>(padre1.size());
		List<Double> hijo2 = new ArrayList<Double>(padre1.size());
		
		double alfa = rand.nextDouble();
		
		for(int i = 0; i < padre1.size();++i) {
			hijo1.add(i, alfa*padre1.get(i) + (1-alfa)*padre2.get(i));
			hijo2.add(i, alfa*padre2.get(i) + (1-alfa)*padre1.get(i));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}
}
