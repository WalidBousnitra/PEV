package elitismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Individuos.Individuo;
import funciones.IndividuoFuncion1;
import funciones.IndividuoFuncion2;
import funciones.IndividuoFuncion3;
import funciones.IndividuoFuncion4a;
import funciones.IndividuoFuncion4b;

public class Elitismo<T> {

	private double p;
	private boolean activado;
	private List<Individuo<T>> elite;
	private int numElite;
	private String funcion;
	
	public Elitismo(String funcion,boolean activado, double probElitismo,int tamPoblacion) {
		p = probElitismo;
		this.activado = activado;
		this.funcion = funcion;
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
	
	@SuppressWarnings("unchecked")
	public Individuo<T> crear(Individuo<T> obj){
		
		switch(funcion) {
		case "Función1(calibracion y prueba)":
			@SuppressWarnings("rawtypes") Individuo<T> nuevo = new IndividuoFuncion1(obj);
			return nuevo;
		case "Función2(GrieWank)":
			@SuppressWarnings("rawtypes") Individuo<T> nuevo2 = new IndividuoFuncion2(obj);
			return nuevo2;
		case "Función3(Styblinski-tang)":
			@SuppressWarnings("rawtypes") Individuo<T> nuevo3 = new IndividuoFuncion3(obj);
			return nuevo3;
		case "Función4a(Michalewicz)":
			@SuppressWarnings("rawtypes") Individuo<T> nuevo4a = new IndividuoFuncion4a(obj);
			return nuevo4a;
		case "Función4b(Michalewicz)":
			@SuppressWarnings("rawtypes") Individuo<T> nuevo4b = new IndividuoFuncion4b(obj);
			return nuevo4b;
		}
		return null;
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
