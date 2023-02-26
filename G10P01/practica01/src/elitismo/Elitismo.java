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
		elite = new ArrayList<Individuo<T>>();
		boolean[] elegidos = new boolean[poblacion.size()];
		if(activado) {
			while (elite.size()<(int)(poblacion.size()*p)) {
				double max = Double.MIN_VALUE;
				Individuo<T> maximo = poblacion.get(0);
				int pos = 0;
				for(int i = 0; i< poblacion.size(); i++) {
					if(!elegidos[i] && poblacion.get(i).getFitness()>max) {
						max = poblacion.get(i).getFitness();
						maximo = poblacion.get(i);
						elegidos[i] = true;
						elegidos[pos] = false;
						pos = i;
					}
				}
				elite.add(maximo);
			}
		}
	}
	
	public List<Individuo<T>> incorporar(List<Individuo<T>> poblacion, List<Individuo<T>> elite) {
		
		List<Individuo<T>> newPoblacion = new ArrayList<Individuo<T>>();
		if(activado) {
		
		}
		else
			return poblacion;
				
		return newPoblacion;
	}
	
}
