package mutaciones;

import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public abstract class Mutacion<T>{
	
	private double p;
	private Random rand;
	
	public Mutacion(double probMutacion) {
		p = probMutacion;
		rand = new Random();
	}
	
	public void mutar(List<Individuo<T>> poblacion) {
		
		for(int i = 0 ; i < poblacion.size(); i++) {
			cromosoma(poblacion.get(i));
		}
	}
	
	public abstract void cromosoma(Individuo<T> individuo);
}