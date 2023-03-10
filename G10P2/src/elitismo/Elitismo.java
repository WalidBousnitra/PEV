package elitismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Individuos.Individuo;
import funciones.Viajero;

public class Elitismo<T> {

	private double p;
	private boolean activado;
	private List<Individuo<T>> elite;
	private int numElite;
	
	public Elitismo(boolean activado, double probElitismo,int tamPoblacion) {
		p = probElitismo;
		this.activado = activado;
		numElite = (int)(tamPoblacion*p);
		elite = new ArrayList<Individuo<T>>(numElite);
	}
	
	public void extraer(List<Individuo<T>> poblacion) {
		
		if(activado) {
	        Collections.sort(poblacion);
	        Collections.reverse(poblacion);
	        for(int i = 0; i<numElite; i++) {
	        	elite.add(crear(poblacion.get(i)));
	        }
		}
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public Individuo<T> crear(Individuo<T> obj){
		Individuo<T> nuevo = new Viajero(obj);
		return nuevo;
	}
	
	public void incorporar(List<Individuo<T>> poblacion) {

		if(activado) {
	        Collections.sort(poblacion);
	        Collections.reverse(poblacion);
	        for (int i = 0; i < elite.size(); i++) {
	            Individuo<T> nuevoIndividuo = elite.get(i);
	            int peorIndice = poblacion.size() - 1 - i;
	            poblacion.set(peorIndice, nuevoIndividuo);
	        }
			elite.clear();
		}
	}
	
}
