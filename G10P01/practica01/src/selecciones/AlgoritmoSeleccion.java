package selecciones;

import Individuos.Individuo;

public abstract class AlgoritmoSeleccion {
	
	public abstract <T> void seleccionar(Individuo<T>[] individuos, double[] fitness);
}