package selecciones;

import Individuos.Individuo;

public abstract class AlgoritmoSeleccion {
	//tabla Individuo fitness puntuacion punttuacion accumulada.
	public int[][] tabla;
	
	public abstract <T> Individuo<T> seleccionar(Individuo<T> individuos);
}