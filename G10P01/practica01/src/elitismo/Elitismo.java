package elitismo;

import java.util.ArrayList;
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
		List<Individuo<T>> copia = new ArrayList<Individuo<T>>(poblacion.size());
		copia.addAll(poblacion);
		elite = new ArrayList<Individuo<T>>();
		int numElite = (int)(poblacion.size()*p);
		if(activado) {
			while (elite.size()<numElite) {
				Individuo<T> maximo = poblacion.get(0);
				int pos = 0;
				for(int i = 0; i< copia.size(); i++) {
					if(copia.get(i).getFitness()>copia.get(0).getFitness()) {
						maximo = copia.get(i);
					 	pos = i;
					}
				}
				elite.add(maximo);
				copia.remove(pos);
			}
		}
				
	}
	
	public List<Individuo<T>> incorporar(List<Individuo<T>> poblacion) {
		
		List<Individuo<T>> newPoblacion = new ArrayList<Individuo<T>>();
		if(activado) {
			for(int i = 0 ; i<elite.size(); i++) {
				Individuo<T> min = poblacion.get(0);
				for(int j = 0; j<poblacion.size();j++){
					if(poblacion.get(i).getFitness()<min.getFitness()) {
						min = poblacion.get(i);
					}
				}
				poblacion.remove(min);
			}
			newPoblacion.addAll(poblacion);
			newPoblacion.addAll(elite);
		}
		else
			return poblacion;
				
		return newPoblacion;
	}
	
}
