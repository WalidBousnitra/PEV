package cruces;

import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public abstract class AlgoritmosCruce{
	
	protected double p;
	private Random rand = new Random();
	
	public AlgoritmosCruce(double p) {
		this.p =p;
	}
	
	public <T> void cruzar(List<Individuo<T>> individuos) {

		for(int  i = 0; i< individuos.size(); i++) {
			if(rand.nextDouble()<p) {
				for(int  j = 0; j< individuos.size(); j++) {
					if(i!=j && rand.nextDouble()<p) {
						List<List<T>> hijos = pareja(individuos.get(i).getCromosoma(),
								individuos.get(j).getCromosoma());
						individuos.get(i).setCromosoma(hijos.get(0));
						individuos.get(i).setCromosoma(hijos.get(1));
					}
				}
			}
		}
		
	}
	public abstract <T> List<List<T>> pareja(List<T> padre1, List<T> padre2);
}