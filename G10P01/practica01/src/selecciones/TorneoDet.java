package selecciones;

import java.util.Random;

import Individuos.Individuo;

public class TorneoDet extends AlgoritmoSeleccion{
	
	private Random rand;
	private int k = 3;

	@Override
	public <T> void seleccionar(Individuo<T>[] individuos, double[] fitness) {
		
		Individuo<T>[] newIndividuos = individuos;
		
		for(int i = 0; i< individuos.length; i++) {

			
		}
		individuos = newIndividuos;
	}

}
