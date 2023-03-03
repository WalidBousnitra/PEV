package selecciones;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class EstocasticoUni extends AlgoritmoSeleccion{
	
	private Random rand = new Random();

	public EstocasticoUni(String funcion) {
		super(funcion);
	}

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {

		double[] probSeleccion = new double[fitness.length+1];
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		double r = rand.nextDouble();
		double maxFitness = Double.MIN_VALUE;
		for(int i = 0; i<fitness.length; i++) {
			setFitnessTotal(getFitnessTotal() + fitness[i]);
			maxFitness = Math.max(maxFitness, fitness[i]);
		}
		
		switch(getFuncion()) {
		case "Función1(calibracion y prueba)":
			break;
		default:
			fitness = ajustarFitness(fitness, maxFitness);
			break;
		}
		
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = fitness[i-1]/getFitnessTotal() + probSeleccion[i-1];
		}
		
		for(int i = 0; i<individuos.size(); i++) {
			double number = (r+i)/individuos.size();
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
