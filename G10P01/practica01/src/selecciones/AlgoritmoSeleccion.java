package selecciones;

import java.util.List;

import Individuos.Individuo;

public abstract class AlgoritmoSeleccion {
	
	public abstract <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness);
}