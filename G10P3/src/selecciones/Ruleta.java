package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuo.Individuo;

public class Ruleta extends AlgoritmoSeleccion{

	private Random rand = new Random();

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		double[] probSeleccion = new double[fitness.length+1];
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		
		double maxFitness = Double.MIN_VALUE;
		for(int i = 0; i<fitness.length; i++) {
			setFitnessTotal(getFitnessTotal() + fitness[i]);
			maxFitness = Math.max(maxFitness, fitness[i]);
		}
		fitness = ajustarFitness(fitness, maxFitness);
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = fitness[i-1]/getFitnessTotal() + probSeleccion[i-1];
		}
		
		for(int i = 0; i<individuos.size(); i++) {
			double number = rand.nextDouble();
			for(int j = 1; j < probSeleccion.length; j++) {
				if(probSeleccion[j-1] < number && number <= probSeleccion[j]) {
					newIndividuos.add(i, crear(individuos.get(j-1)));
					break;
				}
			}
		}
		setFitnessTotal(0);
		return newIndividuos;
	}
	
}