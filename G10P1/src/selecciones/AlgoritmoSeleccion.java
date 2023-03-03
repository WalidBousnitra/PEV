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
	private double fitnessTotal;
	
	public AlgoritmoSeleccion(String funcion) {
		this.setFuncion(funcion);
		this.setFitnessTotal(0);
	}
	
	public double getFitnessTotal() {return fitnessTotal;}

	public void setFitnessTotal(double fitnessTotal) {	this.fitnessTotal = fitnessTotal;}

	public abstract <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness);
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public <T> Individuo<T> crear(Individuo<T> obj){
		
		switch(funcion) {
		case "Función1(calibracion y prueba)":
			Individuo<T> nuevo = new IndividuoFuncion1(obj);
			return nuevo;
		case "Función2(GrieWank)":
			Individuo<T> nuevo2 = new IndividuoFuncion2(obj);
			return nuevo2;
		case "Función3(Styblinski-tang)":
			Individuo<T> nuevo3 = new IndividuoFuncion3(obj);
			return nuevo3;
		case "Función4a(Michalewicz)":
			Individuo<T> nuevo4a = new IndividuoFuncion4a(obj);
			return nuevo4a;
		case "Función4b(Michalewicz)":
			Individuo<T> nuevo4b = new IndividuoFuncion4b(obj);
			return nuevo4b;
		}
		return null;
	}
	
	
	
	public double[]  ajustarFitness(double[] fitness, double maxFitness) {
		
		setFitnessTotal(0);
		double[] sol  = new double[fitness.length];
		
		for(int i = 0; i< fitness.length; i++) {
			sol[i] = 1.05*maxFitness-fitness[i];
			setFitnessTotal(getFitnessTotal() + sol[i]);
		}
		return sol;
	}

	public String getFuncion() {return funcion;}
	public void setFuncion(String funcion) {this.funcion = funcion;	}
}