package mutaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class Mutacion{
	
	private double p;
	private Random rand;
	
	public Mutacion(double probMutacion) {
		p = probMutacion;
		rand = new Random();
	}
	
	public <T> List<Individuo<T>> mutar(List<Individuo<T>> poblacion) {
		
		List<Individuo<T>> newPoblacion = new ArrayList<Individuo<T>>();
		
		for(int i = 0 ; i < poblacion.size(); i++) {
			newPoblacion.add(cromosoma(poblacion.get(i)));
		}
				
		return newPoblacion;
	}
	
	public <T> Individuo<T> cromosoma(Individuo<T> individuo) {
		
		Individuo<T> newIndividuo = individuo;
		
		for(int i = 0; i < newIndividuo.getTamTotal(); ++i) {
			if(rand.nextDouble()<p)
				newIndividuo.mutar(i);
		}
		
		return newIndividuo;
	}
}