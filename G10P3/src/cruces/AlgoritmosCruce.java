package cruces;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import Individuos.Individuo;
import funciones.Formula;

public abstract class AlgoritmosCruce<T>{
	
	protected double p;
	private Random rand = new Random();
	
	public AlgoritmosCruce(double p) {
		this.p =p;
	}
	
	public abstract  List<Individuo<T>>  pareja(Individuo<T> padre1, Individuo<T> padre2);
	
	public List<Individuo<T>> cruzar(List<Individuo<T>> poblacion) {
		
		List<Individuo<T>> parejas = new ArrayList<Individuo<T>>();
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(poblacion.size());
		
		boolean[] elegidos = new boolean[poblacion.size()];
		
		for(int i = 0; i<poblacion.size(); i++)
			if(rand.nextDouble()<p) {
				elegidos[i] = true;
				parejas.add(crear(poblacion.get(i)));
			}
		for(int i = 0; parejas.size()%2!=0; i++)
			if(rand.nextDouble()<p && !elegidos[i%poblacion.size()]) {
				elegidos[i%poblacion.size()] = true;
				parejas.add(crear(poblacion.get(i%poblacion.size())));
			}
		
		for(int i = 0; i<poblacion.size(); i++)
			if(!elegidos[i])
				newIndividuos.add(crear(poblacion.get(i)));
		
		for (int i = 0; i <parejas.size()-1; i=i+2) {
			List<Individuo<T>>  hijos = pareja(parejas.get(i), parejas.get(i+1));
			newIndividuos.add(crear(hijos.get(0)));
			newIndividuos.add(crear(hijos.get(1)));
		}
		return newIndividuos;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public Individuo<T> crear(Individuo<T> obj){
		Individuo<T> nuevo = new Formula(obj);
		return nuevo;
	}
}