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
			cromosoma(poblacion.get(i));
			newPoblacion.add(poblacion.get(i));
		}
				
		return newPoblacion;
	}
	
	public <T> void cromosoma(Individuo<T> individuo) {
		
		for(int i = 0; i < individuo.getTamTotal(); ++i) {
			if(rand.nextDouble()<p)
				individuo.mutar(i);
		}
	}
}