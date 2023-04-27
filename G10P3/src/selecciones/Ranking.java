package selecciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuo.Individuo;

public class Ranking extends AlgoritmoSeleccion{
	
	private Random rand = new Random();
	
	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		double[] probSeleccion = new double[fitness.length+1];
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());

		//Primero ordenamos de mayor a menor la poblacion para crear la ordenacion por ranking
		Collections.sort(individuos);
		Collections.reverse(individuos);
		Arrays.sort(fitness);
		
		//Ajustamos el fitness para calcular lapresion selectiva
		double maxFitness = Double.MIN_VALUE;
		for(int i = 0; i<fitness.length; i++) {
			setFitnessTotal(getFitnessTotal() + fitness[i]);
			maxFitness = Math.max(maxFitness, fitness[i]);
		}
		fitness = ajustarFitness(fitness, maxFitness);
		
		double beta = presSel(fitness);
		
		probSeleccion[0] = 0;
		int n = individuos.size();
		for(int i = 1; i<=fitness.length; i++) {
			
			double uno = 1.0/n;
			double dos = 2.0*(beta-1.0);
			double tres = (i-1.0)/(n-1.0);
			probSeleccion[i] = (uno*(beta-dos*tres)) + probSeleccion[i-1];
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
	
	public double presSel(double[] fitness) {
		double sum= 0.0;
		
		for(int i = 0; i<fitness.length;i++) {
			sum+=fitness[i];
		}
		
		double sol = fitness[0]/(sum/fitness.length);
		
		return sol;
	}

}
