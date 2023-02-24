package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class BLXalfa extends AlgoritmosCruce{
	
	private Random rand = new Random();
	
	public BLXalfa(double p) {
		super(p);
	}

	@Override
	public <T> List<List<T>> pareja(List<T> padre1, List<T> padre2) {
		
		List<List<T>> hijos = new ArrayList<List<T>>();
		
		List<T> hijo1 = new ArrayList<>(padre1.size());
		List<T> hijo2 = new ArrayList<>(padre1.size());
		
		for(int i = 0; i < padre1.size(); i++) {
			//double cMax = Math.max(padre1.get(i), padre2.get(i));
			//double cMin = Math.min(padre1.get(i), padre2.get(i));
			//double I = cMax-cMin;
			double alfa = rand.nextDouble();
			//hijo1.add(i,rand.nextDouble(cMin-I*alfa,cMax+I*alfa));
			//hijo2.add(i,rand.nextDouble(cMin-I*alfa,cMax+I*alfa));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
