package elitismo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class Elitismo {

	private double p;
	private Random rand;
	private boolean activado;
	
	public Elitismo(double probElitismo) {
		p = probElitismo;
		rand = new Random();
	}
	
	public <T> void extraer() {
		
		if(rand.nextDouble()<p) {
			
		}
		
	}
	
	public <T> List<Individuo<T>> incorporar(List<Individuo<T>> poblacion) {
		
		List<Individuo<T>> newPoblacion = new ArrayList<Individuo<T>>();
		
		if(activado) {
			
		}
		else
			return poblacion;
				
		return newPoblacion;
	}
	
}
