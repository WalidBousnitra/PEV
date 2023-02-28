package elitismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import Individuos.Individuo;

public class Elitismo<T> {

	private double p;
	private boolean activado;
	private List<Individuo<T>> elite;
	
	public Elitismo(boolean activado, double probElitismo) {
		p = probElitismo;
		this.activado = activado;
	}
	
	public void extraer(List<Individuo<T>> poblacion) {

		elite = new ArrayList<Individuo<T>>();
		int numElite = (int)(poblacion.size()*p);
		if(activado) {
			List<Individuo<T>> copia = new ArrayList<Individuo<T>>(poblacion.size());
			copia.addAll(poblacion);
	        Collections.sort(copia, Comparator.comparing(Individuo<T>::getFitness).reversed());
	        
	        // Obtener los x individuos con mayor fitness
	        elite = copia.subList(0, numElite);
		}
	}
	
	public List<Individuo<T>> incorporar(List<Individuo<T>> poblacion) {
		
		List<Individuo<T>> copia = new ArrayList<Individuo<T>>(poblacion.size());
		copia.addAll(poblacion);
		if(activado) {
	        // Ordenar la lista de nuevos individuos por fitness de mayor a menor
	        Collections.sort(elite, Comparator.comparing(Individuo<T>::getFitness).reversed());
	        
	        // Sustituir los x individuos con menor fitness por los nuevos individuos
	        for (int i = 0; i < elite.size(); i++) {
	            Individuo<T> nuevoIndividuo = elite.get(i);
	            int peorIndice = copia.size() - 1 - i;
	            copia.set(peorIndice, nuevoIndividuo);
	        }
		}
		else
			return poblacion;
				
		return copia;
	}
	
}
