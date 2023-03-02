package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BLXalfa<T> extends AlgoritmosCruce<Double>{
	
	private Random rand = new Random();
	
	public BLXalfa(String funcion,double p) {
		super(funcion,p);
	}

	@Override
	public List<List<Double>> pareja(List<Double> padre1, List<Double> padre2) {
		
		List<List<Double>> hijos = new ArrayList<List<Double>>();
		
		List<Double> hijo1 = new ArrayList<Double>(padre1.size());
		List<Double> hijo2 = new ArrayList<Double>(padre1.size());
		
		for(int i = 0; i < padre1.size(); i++) {
			double cMax = Math.max(padre1.get(i), padre2.get(i));
			double cMin = Math.min(padre1.get(i), padre2.get(i));
			double I = Math.abs(cMax-cMin);
			double alfa = rand.nextDouble();
			hijo1.add(i,rand.nextDouble(cMin-I*alfa,cMax+I*alfa));
			hijo2.add(i,rand.nextDouble(cMin-I*alfa,cMax+I*alfa));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
