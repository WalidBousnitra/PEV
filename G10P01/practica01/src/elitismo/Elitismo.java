package elitismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Individuos.Individuo;

public class Elitismo<T> {

	private double p;
	private boolean activado;
	private List<Individuo<T>> elite;
	private int numElite;;
	
	public Elitismo(boolean activado, double probElitismo,int tamPoblacion) {
		p = probElitismo;
		this.activado = activado;
		numElite = (int)(tamPoblacion*p);
		elite = new ArrayList<Individuo<T>>(numElite);
	}
	
	public void extraer(List<Individuo<T>> poblacion) {
		
		for(Individuo<T> obj : poblacion) {
			System.out.println(obj.getFitness());
		}
		System.out.println("_______________________");
		if(activado) {
	        Collections.sort(poblacion);
	        Collections.reverse(poblacion);
	        elite.addAll(poblacion.subList(0, numElite));
		}
		for(Individuo<T> obj : elite) {
			System.out.println(obj.getFitness());
		}
		System.out.println("_______________________");
	}
	
	public void incorporar(List<Individuo<T>> poblacion) {

		if(activado) {
			
			// Ordenar la lista de nuevos individuos por fitness de mayor a menor
	        Collections.sort(poblacion);
	        Collections.reverse(poblacion);
			for(Individuo<T> obj : poblacion) {
				System.out.println(obj.getFitness());
			}
			System.out.println("_______________________");
	        // Ordenar la lista de nuevos individuos por fitness de mayor a menor
	        Collections.sort(elite);
	        Collections.reverse(elite);
	        
			for(Individuo<T> obj : elite) {
				System.out.println(obj.getFitness());
			}
			System.out.println("_______________________");
	        // Sustituir los x individuos con menor fitness por los nuevos individuos
	        for (int i = 0; i < elite.size(); i++) {
	            Individuo<T> nuevoIndividuo = elite.get(i);
	            int peorIndice = poblacion.size() - 1 - i;
	            poblacion.set(peorIndice, nuevoIndividuo);
	        }
			for(Individuo<T> obj : poblacion) {
				System.out.println(obj.getFitness());
			}
			System.out.println("_______________________");
			elite.clear();
		}
	}
	
}
