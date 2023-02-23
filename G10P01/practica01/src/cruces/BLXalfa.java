package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BLXalfa extends AlgoritmosCruce<Double>{
	
	private Random rand;

	@Override
	public List<Double[]> cruzar(Double[] padre1, Double[] padre2, double p) {
		
		List<Double[]> hijos = new ArrayList<Double[]>();
		
		Double[] hijo1 = new Double[padre1.length];
		Double[] hijo2 = new Double[padre1.length];		
		
		for(int i = 0; i < padre1.length; i++) {
			double cMax = Math.max(padre1[i], padre2[i]);
			double cMin = Math.min(padre1[i], padre2[i]);
			double I = cMax-cMin;
			double alfa = rand.nextDouble();
			hijo1[i] = rand.nextDouble(cMin-I*alfa,cMax+I*alfa);
			hijo2[i] = rand.nextDouble(cMin-I*alfa,cMax+I*alfa);
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
