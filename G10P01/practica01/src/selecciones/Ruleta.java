package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Ruleta extends AlgoritmoSeleccion{
	
	private Random rand = new Random();

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		double fitnessTotal = 0;
		double[] probSeleccion = new double[fitness.length+1];
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		
		for(int i = 0; i<fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = fitness[i-1]/fitnessTotal + probSeleccion[i-1];
		}
		
		for(int i = 0; i<individuos.size(); i++) {
			double r = rand.nextDouble();
			for(int j = 1; j <probSeleccion.length; j++) {
				if(probSeleccion[j-1] < r && r <= probSeleccion[j]) {
					newIndividuos.add(i, individuos.get(j-1));
					break;
				}
			}
		}
		return newIndividuos;
	}
	
}