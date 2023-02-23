package selecciones;

import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import Individuos.Individuo;

public class Truncamiento extends AlgoritmoSeleccion{
	
	private Random rand;

	@Override
	public <T> void seleccionar(Individuo<T>[] individuos, double[] fitness) {
		
		double fitnessTotal = 0;
		double[] probSeleccion = new double[fitness.length+1];
		TreeMap<Double, Individuo<T>> mapSeleccion = new TreeMap<Double, Individuo<T>>();
		Individuo<T>[] newIndividuos = individuos;
		double r = rand.nextDouble();
		for(int i = 0; i<fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = fitness[i-1]/fitnessTotal + probSeleccion[i-1];
			mapSeleccion.put(probSeleccion[i], individuos[i]);
		}
		int i  = 0;
		for(Entry<Double, Individuo<T>> obj : mapSeleccion.entrySet()) {
			if(i>=r*individuos.length)
				break;
			newIndividuos[i] = obj.getValue();
			i++;
		}
		
		for( int j = i; j< individuos.length; j++) {
			newIndividuos[j] = newIndividuos[j-i];
		}

		individuos = newIndividuos;
	}

}
