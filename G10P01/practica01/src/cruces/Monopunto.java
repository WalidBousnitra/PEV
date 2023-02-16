package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monopunto extends AlgoritmosCruce{

	private Random rand;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T[]> cruzar(T[] padre1, T[] padre2, double p) {
		
		List<T[]> hijos = new ArrayList<T[]>();
		
		T[] hijo1 = (T[]) new Object[padre1.length];
		T[] hijo2 = (T[]) new Object[padre1.length];
		
		int puntoCorte = rand.nextInt(1,padre1.length);
		
		for(int i = 0; i < puntoCorte;++i) {
			hijo1[i] = padre1[i];
			hijo2[i] = padre2[i];
		}
		for(int i = puntoCorte; i < padre1.length;++i) {
			hijo1[i] = padre2[i];
			hijo2[i] = padre2[i];
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
