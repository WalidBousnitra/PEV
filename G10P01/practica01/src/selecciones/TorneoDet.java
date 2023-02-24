package selecciones;

import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class TorneoDet extends AlgoritmoSeleccion{
	
	private Random rand = new Random();

	@Override
	public <T> void seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		List<Individuo<T>> newIndividuos = individuos;
		
		for(int i = 0; i< individuos.size(); i++) {
			int k1 = rand.nextInt(0,individuos.size());
			int k2 = rand.nextInt(0,individuos.size());
			int k3 = rand.nextInt(0,individuos.size());
			
			if (individuos.get(k1).getFitness() >= individuos.get(k2).getFitness() && individuos.get(k1).getFitness() >= individuos.get(k3).getFitness())
				newIndividuos.add(i, individuos.get(k1));
		    else if (individuos.get(k2).getFitness() >= individuos.get(k1).getFitness() && individuos.get(k2).getFitness() >= individuos.get(k3).getFitness())
		    	newIndividuos.add(i, individuos.get(k2));
		    else
		    	newIndividuos.add(i, individuos.get(k3));
		}
		individuos = newIndividuos;
	}

}
