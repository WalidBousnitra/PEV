package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aritmetico extends AlgoritmosCruce<Double>{
	
	private Random rand;

	@Override
	public List<Double[]> cruzar(Double[] padre1, Double[] padre2, double p) {
		List<Double[]> hijos = new ArrayList<Double[]>();
		
		Double[] hijo1 = new Double[padre1.length];
		Double[] hijo2 = new Double[padre1.length];
		
		double alfa = rand.nextDouble();
		
		for(int i = 0; i < padre1.length;++i) {
			hijo1[i] = alfa*padre1[i] + (1-alfa)*padre2[i];
			hijo2[i] = alfa*padre2[i] + (1-alfa)*padre1[i];
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}
}
