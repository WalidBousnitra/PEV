package mutaciones;

import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public abstract class AlgoritmosMutacion<T>{
	
	private double p;
	private Random rand;
	
	public AlgoritmosMutacion(double probMutacion) {
		p = probMutacion;
		rand = new Random();
	}
	
	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public void mutar(List<Individuo<T>> poblacion) {
		
		for(int i = 0 ; i < poblacion.size(); i++) {
			cromosoma(poblacion.get(i));
		}
	}
	
	public abstract void cromosoma(Individuo<T> individuo);
}