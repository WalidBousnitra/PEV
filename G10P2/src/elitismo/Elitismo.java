package elitismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Individuos.Individuo;
import funciones.Viajero;

public class Elitismo<T> {

	// Atributos
	private boolean activado; //Con/sin elitismo
	private List<Individuo<T>> elite;
	private int numElite;
	
	public Elitismo(boolean activado, double tamElite,int tamPoblacion) {
		this.activado = activado;
		numElite = (int)(tamElite);
		elite = new ArrayList<Individuo<T>>(numElite);
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	// Funcion de extracion de elite por copia
	public void extraer(List<Individuo<T>> poblacion) {
		
		if(activado) {
			// Gracias a Comparable se puede ordenar e invertir para coger los mejores
	        Collections.sort(poblacion);
	        Collections.reverse(poblacion);
	        for(int i = 0; i<numElite; i++)
	        	elite.add(new Viajero(poblacion.get(i)));
		}
	}
	
	// Funcion de incorporacion de elite
	public void incorporar(List<Individuo<T>> poblacion) {

		if(activado) {
			// Gracias a Comparable se puede ordenar e invertir para sustituir los peor
	        Collections.sort(poblacion);
	        Collections.reverse(poblacion);
	        for (int i = 0; i < elite.size(); i++) {
	            Individuo<T> nuevoIndividuo = elite.get(i);
	            int peorIndice = poblacion.size() - 1 - i;
	            poblacion.set(peorIndice, nuevoIndividuo);
	        }
	        //liberacion de la elite para la siguiente generacion
			elite.clear();
		}
	}
	
}
