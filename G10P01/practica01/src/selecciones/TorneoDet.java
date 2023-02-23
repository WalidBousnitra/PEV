package selecciones;

import java.util.Random;

import Individuos.Individuo;

public class TorneoDet extends AlgoritmoSeleccion{
	
	private Random rand;

	@Override
	public <T> void seleccionar(Individuo<T>[] individuos, double[] fitness) {
		
		Individuo<T>[] newIndividuos = individuos;
		
		for(int i = 0; i< individuos.length; i++) {
			int k1 = rand.nextInt(0,individuos.length);
			int k2 = rand.nextInt(0,individuos.length);
			int k3 = rand.nextInt(0,individuos.length);
			
			if (individuos[k1].getFitness() >= individuos[k2].getFitness() && individuos[k1].getFitness() >= individuos[k3].getFitness())
				newIndividuos[i] = individuos[k1];
		    else if (individuos[k2].getFitness() >= individuos[k1].getFitness() && individuos[k2].getFitness() >= individuos[k3].getFitness())
		    	newIndividuos[i] = individuos[k2];
		    else
		    	newIndividuos[i] = individuos[k3];
		}
		individuos = newIndividuos;
	}

}
