package selecciones;

import java.util.List;

import Individuos.Individuo;

public abstract class AlgoritmoSeleccion {
	
	public abstract <T> void seleccionar(List<Individuo<T>> individuos, double[] fitness);
}