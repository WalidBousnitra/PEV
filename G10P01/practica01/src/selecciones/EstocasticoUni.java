package selecciones;

import java.util.Random;

import Individuos.Individuo;

public class EstocasticoUni extends AlgoritmoSeleccion{
	
	private Random rand;

	@Override
	public <T> void seleccionar(Individuo<T>[] individuos, double[] fitness) {
		double fitnessTotal = 0;
		double[] probSeleccion = new double[fitness.length+1];
		Individuo<T>[] newIndividuos = individuos;
		double r = rand.nextDouble();
		for(int i = 0; i<fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = fitness[i-1]/fitnessTotal + probSeleccion[i-1];
		}
		
		for(int i = 0; i<individuos.length; i++) {
			double number = (r+i)/individuos.length;
			for(int j = 1; j < probSeleccion.length; j++) {
				if(probSeleccion[j-1] < number && number <= probSeleccion[j]) {
					newIndividuos[i] = individuos[j-1];
					break;
				}
			}
		}
		individuos = newIndividuos;
	}

}
