package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuo.Individuo;

public class TorneoProb extends AlgoritmoSeleccion{

	private Random rand = new Random();

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		double p = rand.nextDouble(0.5, 1);
		
		for(int i = 0; i< individuos.size(); i++) {
			int k1 = rand.nextInt(0,individuos.size());
			int k2 = rand.nextInt(0,individuos.size());
			int k3 = rand.nextInt(0,individuos.size());
			while(k1==k2||k2==k3||k3==k1) {
				k2 = rand.nextInt(0,individuos.size());
				k3 = rand.nextInt(0,individuos.size());
			}
			if(rand.nextDouble()<p) 
				newIndividuos = mayorFit(i, individuos,newIndividuos,k1,k2,k3);
			else
				newIndividuos = menorFit(i, individuos,newIndividuos,k1,k2,k3);
		}
		return newIndividuos;
	}
	
	private <T> List<Individuo<T>> menorFit(int i, List<Individuo<T>> individuos, List<Individuo<T>> newIndividuos, int k1, int k2, int k3) {
		if (individuos.get(k1).compareTo(individuos.get(k2)) == -1 && individuos.get(k1).compareTo(individuos.get(k3)) == -1 )
			newIndividuos.add(i, crear(individuos.get(k1)));
	    else if (individuos.get(k2).compareTo(individuos.get(k1)) == -1  && individuos.get(k2).compareTo(individuos.get(k3)) == -1 )
	    	newIndividuos.add(i, individuos.get(k2));
	    else
	    	newIndividuos.add(i, crear(individuos.get(k3)));
		return newIndividuos;
	}

	private <T> List<Individuo<T>> mayorFit(int i, List<Individuo<T>> individuos, List<Individuo<T>> newIndividuos, int k1, int k2, int k3) {
		if (individuos.get(k1).compareTo(individuos.get(k2)) == 1 && individuos.get(k1).compareTo(individuos.get(k3)) == 1 )
			newIndividuos.add(i, crear(individuos.get(k1)));
	    else if (individuos.get(k2).compareTo(individuos.get(k1)) == 1  && individuos.get(k2).compareTo(individuos.get(k3)) == 1 )
	    	newIndividuos.add(i, individuos.get(k2));
	    else
	    	newIndividuos.add(i, crear(individuos.get(k3)));
		return newIndividuos;
	}

}
