package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;
import funciones.IndividuoFuncion1;
import funciones.IndividuoFuncion2;
import funciones.IndividuoFuncion3;
import funciones.IndividuoFuncion4a;
import funciones.IndividuoFuncion4b;

public abstract class AlgoritmosCruce<T>{
	
	protected double p;
	private Random rand = new Random();
	private String funcion;
	
	public AlgoritmosCruce(String funcion, double p) {
		this.p =p;
		this.funcion=funcion;
	}
	
	public abstract  List<Individuo<T>>  pareja(Individuo<T> padre1, Individuo<T> padre2);
	
	public List<Individuo<T>> cruzar(List<Individuo<T>> individuos) {
		
		List<Individuo<T>> parejas = new ArrayList<Individuo<T>>();
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		
		boolean[] elegidos = new boolean[individuos.size()];
		
		for(int i = 0; i<individuos.size(); i++)
			if(rand.nextDouble()<p) {
				elegidos[i] = true;
				parejas.add(crear(individuos.get(i)));
			}
		for(int i = 0; parejas.size()%2!=0; i++)
			if(rand.nextDouble()<p && !elegidos[i%individuos.size()]) {
				elegidos[i%individuos.size()] = true;
				parejas.add(crear(individuos.get(i%individuos.size())));
			}
		
		for(int i = 0; i<individuos.size(); i++)
			if(!elegidos[i])
				newIndividuos.add(crear(individuos.get(i)));
		
		for (int i = 0; i <parejas.size()-1; i=i+2) {
			 List<Individuo<T>>  hijos = pareja(parejas.get(i), parejas.get(i+1));
			newIndividuos.add(crear(hijos.get(0)));
			newIndividuos.add(crear(hijos.get(1)));
		}
		return newIndividuos;
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
}