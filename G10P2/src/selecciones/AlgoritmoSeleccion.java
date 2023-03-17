package selecciones;

import java.util.List;

import Individuos.Individuo;
import funciones.Viajero;


public abstract class AlgoritmoSeleccion {
	
	private double fitnessTotal;
	
	public AlgoritmoSeleccion() {
		this.setFitnessTotal(0);
	}
	
	public double getFitnessTotal() {return fitnessTotal;}

	public void setFitnessTotal(double fitnessTotal) {	this.fitnessTotal = fitnessTotal;}

	public abstract <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness);
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public <T> Individuo<T> crear(Individuo<T> obj){
		Individuo<T> nuevo = new Viajero(obj);
		return nuevo;
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
}