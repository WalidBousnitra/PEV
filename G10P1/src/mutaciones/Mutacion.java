package mutaciones;

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
	
	public <T> void mutar(List<Individuo<T>> poblacion) {
		
		for(int i = 0 ; i < poblacion.size(); i++) {

			cromosoma(poblacion.get(i));
		}
	}
	
	public <T> void cromosoma(Individuo<T> individuo) {

		for(int i = 0; i<individuo.getCromosoma().size();i++) {
			if(rand.nextDouble()<p) {
				individuo.mutar(i);
			}
		}
	}
}