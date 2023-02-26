package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public abstract class AlgoritmosCruce<T>{
	
	protected double p;
	private Random rand = new Random();
	
	public AlgoritmosCruce(double p) {
		this.p =p;
	}
	
	public List<Individuo<T>> cruzar(List<Individuo<T>> individuos) {
		
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		List<Individuo<T>> parejas = new ArrayList<Individuo<T>>();
		boolean[] elegidos = new boolean[individuos.size()];
		
		for(int i = 0; i<individuos.size(); i++)
			if(rand.nextDouble()<p) {
				elegidos[i] = true;
				parejas.add(individuos.get(i));
			}
		for(int i = 0; parejas.size()%2!=0; i++)
			if(rand.nextDouble()<p) {
				elegidos[i%individuos.size()] = true;
				parejas.add(individuos.get(i%individuos.size()));
			}
		
		for(int i = 0; i<individuos.size(); i++)
			if(!elegidos[i])
				newIndividuos.add(individuos.get(i));
		
		for (int i = 0; i <parejas.size()-1; i=i+2) {
			List<List<T>> hijos = pareja(parejas.get(i).getCromosoma(),
					parejas.get(i+1).getCromosoma());
			parejas.get(i).setCromosoma(hijos.get(0));
			parejas.get(i+1).setCromosoma(hijos.get(1));
			newIndividuos.add(parejas.get(i));
			newIndividuos.add(parejas.get(i+1));
		}
		
		return newIndividuos;
	}
	public abstract List<List<T>> pareja(List<T> padre1, List<T> padre2);
}