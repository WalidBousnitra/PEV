package selecciones;

import java.util.Random;

import Individuos.Individuo;

public class TorneoProb extends AlgoritmoSeleccion{
	
	private Random rand;

	@Override
	public <T> void seleccionar(Individuo<T>[] individuos, double[] fitness) {
		
		Individuo<T>[] newIndividuos = individuos;
		double p = rand.nextDouble(0.5, 1);
		
		for(int i = 0; i< individuos.length; i++) {
			int k1 = rand.nextInt(0,individuos.length);
			int k2 = rand.nextInt(0,individuos.length);
			int k3 = rand.nextInt(0,individuos.length);
			if(rand.nextDouble()>p) 
				mayorFit(i, individuos,newIndividuos,k1,k2,k3);
			else
				menorFit(i, individuos,newIndividuos,k1,k2,k3);
		}
		individuos = newIndividuos;
	}
	
	private <T> void menorFit(int i, Individuo<T>[] individuos, Individuo<T>[] newIndividuos, int k1, int k2, int k3) {
		if (individuos[k1].getFitness() <= individuos[k2].getFitness() && individuos[k1].getFitness() <= individuos[k3].getFitness())
			newIndividuos[i] = individuos[k1];
	    else if (individuos[k2].getFitness() <= individuos[k1].getFitness() && individuos[k2].getFitness() <= individuos[k3].getFitness())
	    	newIndividuos[i] = individuos[k2];
	    else
	    	newIndividuos[i] = individuos[k3];
	}

	private <T> void mayorFit(int i, Individuo<T>[] individuos, Individuo<T>[] newIndividuos, int k1, int k2, int k3) {
		if (individuos[k1].getFitness() >= individuos[k2].getFitness() && individuos[k1].getFitness() >= individuos[k3].getFitness())
			newIndividuos[i] = individuos[k1];
	    else if (individuos[k2].getFitness() >= individuos[k1].getFitness() && individuos[k2].getFitness() >= individuos[k3].getFitness())
	    	newIndividuos[i] = individuos[k2];
	    else
	    	newIndividuos[i] = individuos[k3];
	}

}
