package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monopunto<T> extends AlgoritmosCruce<T>{
	
	private Random rand = new Random();

	public Monopunto(double p) {
		super(p);
	}
	
	@Override
	public List<List<T>> pareja(List<T> padre1, List<T> padre2) {
		
		List<List<T>> hijos = new ArrayList<List<T>>();
		
		List<T> hijo1 = new ArrayList<T>(padre1.size());
		List<T> hijo2 = new ArrayList<T>(padre1.size());
		
		int puntoCorte = rand.nextInt(1,padre1.size());
		
		for(int i = 0; i < puntoCorte;++i) {
			hijo1.add(i, padre1.get(i));
			hijo2.add(i, padre2.get(i));
		}
		for(int i = puntoCorte; i < padre1.size();++i) {
			hijo1.add(i, padre2.get(i));
			hijo2.add(i, padre1.get(i));
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
				
		return hijos;
	}
}
