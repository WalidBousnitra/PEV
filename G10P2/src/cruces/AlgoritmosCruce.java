package cruces;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import Individuos.Individuo;
import funciones.Viajero;

public abstract class AlgoritmosCruce<T>{
	
	protected double p;
	private Random rand = new Random();
	
	public AlgoritmosCruce(double p) {
		this.p =p;
	}
	
	public abstract  List<Individuo<T>>  pareja(Individuo<T> padre1, Individuo<T> padre2);
	
	public List<Individuo<T>> cruzar(List<Individuo<T>> individuos) {
		
		List<Individuo<T>> parejas = new ArrayList<Individuo<T>>();
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		
		boolean[] elegidos = new boolean[individuos.size()];
		
		for(int i = 0; i<individuos.size(); i++)
			if(rand.nextDouble()<p) {
				elegidos[i] = true;
				parejas.add(crear(individuos.get(i)));
			}
		for(int i = 0; parejas.size()%2!=0; i++)
			if(rand.nextDouble()<p && !elegidos[i%individuos.size()]) {
				elegidos[i%individuos.size()] = true;
				parejas.add(crear(individuos.get(i%individuos.size())));
			}
		
		for(int i = 0; i<individuos.size(); i++)
			if(!elegidos[i])
				newIndividuos.add(crear(individuos.get(i)));
		
		for (int i = 0; i <parejas.size()-1; i=i+2) {
			 List<Individuo<T>>  hijos = pareja(parejas.get(i), parejas.get(i+1));
			newIndividuos.add(crear(hijos.get(0)));
			newIndividuos.add(crear(hijos.get(1)));
		}
		return newIndividuos;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public Individuo<T> crear(Individuo<T> obj){
		Individuo<T> nuevo = new Viajero(obj);
		return nuevo;
	}
}