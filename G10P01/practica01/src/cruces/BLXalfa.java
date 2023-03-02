package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class BLXalfa<T> extends AlgoritmosCruce<Double>{
	
	private Random rand = new Random();
	
	public BLXalfa(String funcion,double p) {
		super(funcion,p);
	}

	@Override
	public List<Individuo<Double>> pareja(Individuo<Double> padre1, Individuo<Double> padre2) {
		
		List<Individuo<Double>> hijos = new ArrayList<Individuo<Double>>(2);
		
		Individuo<Double> hijo1 = crear(padre1);
		Individuo<Double> hijo2 = crear(padre2);
		
		for(int i = 0; i < padre1.getCromosoma().size(); i++) {
			double cMax = Math.max(padre1.getCromosoma().get(i), padre2.getCromosoma().get(i));
			double cMin = Math.min(padre1.getCromosoma().get(i), padre2.getCromosoma().get(i));
			double I = Math.abs(cMax-cMin);
			double alfa = rand.nextDouble();
			hijo1.getCromosoma().set(i,rand.nextDouble(cMin-I*alfa,cMax+I*alfa));
			hijo2.getCromosoma().set(i,rand.nextDouble(cMin-I*alfa,cMax+I*alfa));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}

}
