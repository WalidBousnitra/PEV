package selecciones;

import java.util.List;

import Individuos.Individuo;
import funciones.IndividuoFuncion1;
import funciones.IndividuoFuncion2;
import funciones.IndividuoFuncion3;
import funciones.IndividuoFuncion4a;
import funciones.IndividuoFuncion4b;

public abstract class AlgoritmoSeleccion {
	
	private String funcion;
	
	public AlgoritmoSeleccion(String funcion) {
		this.setFuncion(funcion);
	}
	
	public abstract <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness);
	
	@SuppressWarnings("unchecked")
	public <T> Individuo<T> crear(Individuo<T> obj){
		
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
	
	public double[]  ajustarFitness(double fitnessTotal, double[] fitness, double maxFitness) {
		
		fitnessTotal = 0;
		double[] sol  = new double[fitness.length];
		
		for(int i = 0; i< fitness.length; i++) {
			sol[i] = 1.05*maxFitness-fitness[i];
			fitnessTotal += sol[i];
		}
		return sol;
	}

	public String getFuncion() {return funcion;}
	public void setFuncion(String funcion) {this.funcion = funcion;	}
}