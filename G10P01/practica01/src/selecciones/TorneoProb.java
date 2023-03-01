package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class TorneoProb extends AlgoritmoSeleccion{
	
	public TorneoProb(String funcion) {
		super(funcion);
	}

	private Random rand = new Random();

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		double p = rand.nextDouble(0.5, 1);
		
		for(int i = 0; i< individuos.size(); i++) {
			int k1 = rand.nextInt(0,individuos.size());
			int k2 = rand.nextInt(0,individuos.size());
			int k3 = rand.nextInt(0,individuos.size());
			if(rand.nextDouble()>p) 
				mayorFit(i, individuos,newIndividuos,k1,k2,k3);
			else
				menorFit(i, individuos,newIndividuos,k1,k2,k3);
		}
		return newIndividuos;
	}
	
	private <T> void menorFit(int i, List<Individuo<T>> individuos, List<Individuo<T>> newIndividuos, int k1, int k2, int k3) {
		if (individuos.get(k1).getFitness() <= individuos.get(k2).getFitness() && individuos.get(k1).getFitness() <= individuos.get(k3).getFitness())
			newIndividuos.add(i, crear(individuos.get(k1)));
	    else if (individuos.get(k2).getFitness() >= individuos.get(k1).getFitness() && individuos.get(k2).getFitness() <= individuos.get(k3).getFitness())
	    	newIndividuos.add(i, crear(individuos.get(k2)));
	    else
	    	newIndividuos.add(i, individuos.get(k3));
	}

	private <T> void mayorFit(int i, List<Individuo<T>> individuos, List<Individuo<T>> newIndividuos, int k1, int k2, int k3) {
		if (individuos.get(k1).getFitness() >= individuos.get(k2).getFitness() && individuos.get(k1).getFitness() >= individuos.get(k3).getFitness())
			newIndividuos.add(i, crear(individuos.get(k1)));
	    else if (individuos.get(k2).getFitness() >= individuos.get(k1).getFitness() && individuos.get(k2).getFitness() >= individuos.get(k3).getFitness())
	    	newIndividuos.add(i, crear(individuos.get(k2)));
	    else
	    	newIndividuos.add(i, crear(individuos.get(k3)));
	}

}
