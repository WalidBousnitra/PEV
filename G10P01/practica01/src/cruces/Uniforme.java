package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Uniforme extends AlgoritmosCruce{
	
	private Random rand;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T[]> cruzar(T[] padre1, T[] padre2, double p) {
		
		List<T[]> hijos = new ArrayList<T[]>();
		
		T[] hijo1 = (T[]) new Object[padre1.length];
		T[] hijo2 = (T[]) new Object[padre1.length];
		
		for(int i = 0; i < padre1.length; i++)
			if(rand.nextDouble() <= p) {
				hijo1[i] = padre2[i];
				hijo2[i] = padre1[i];
			}
			else {
				hijo1[i] = padre1[i];
				hijo2[i] = padre2[i];
			}
		
		hijos.add(hijo1);
		hijos.add(hijo2);		
		
		return hijos;
	}

}
