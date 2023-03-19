package selecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Ranking extends AlgoritmoSeleccion{
	
	private Random rand = new Random();
	
	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		double[] probSeleccion = new double[fitness.length+1];
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());

		//Primero ordenamos de mayor a menor la poblacion para crear la ordenacion por ranking
		Collections.sort(individuos);
		Collections.reverse(individuos);
		for(int i = 0; i<individuos.size();i++)
			fitness[0] = individuos.get(i).getFitness();
		
		//Ajustamos el fitness para calcular lapresion selectiva
		double maxFitness = Double.MIN_VALUE;
		for(int i = 0; i<fitness.length; i++) {
			setFitnessTotal(getFitnessTotal() + fitness[i]);
			maxFitness = Math.max(maxFitness, fitness[i]);
		}
		fitness = ajustarFitness(fitness, maxFitness);
		
		double beta = 2;
		
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = (1/individuos.size())*(beta-2*(beta-1)*((i-1)/(individuos.size()-1)));
		}
		
		//Seleccion de individuos por probabilidad
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
