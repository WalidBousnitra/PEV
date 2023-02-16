package mutaciones;

import java.util.Random;

import Individuos.Individuo;

public class Mutacion {
	
	private double p;
	private Random rand;
	
	public Mutacion(double probMutacion) {
		p = probMutacion;
	}
	
	public <T> Individuo<T> mutar(Individuo<T> individuo) {
		
		/*for(int i = 0; i < individuo.tamGenes; ++i) {
			if(rand.nextDouble()<p)
				//Individuo.getCromosoma(i).invertir(); por generalizar 
		}*/
		
		return individuo;
	}
}
