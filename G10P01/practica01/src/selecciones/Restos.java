package selecciones;

import java.util.Arrays;

import Individuos.Individuo;

public class Restos extends AlgoritmoSeleccion{
	
	private int k;

	@Override
	public <T> void seleccionar(Individuo<T>[] individuos, double[] fitness) {
		
		double fitnessTotal = 0;
		double[] probSeleccion = new double[fitness.length+1];
		Individuo<T>[] newIndividuos = individuos;
		k = individuos.length;
		
		for(int i = 0; i<fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		for(int i = 0; i<fitness.length; i++) {
			probSeleccion[i] = fitness[i]/fitnessTotal*k;
		}
		int pos =0;
		for(int i = 0; i<probSeleccion.length; ++i) {
			if(probSeleccion[i]>=1) {
				newIndividuos[pos] = individuos[i];
				pos++;
			}
		}
		individuos = newIndividuos;
	}

}