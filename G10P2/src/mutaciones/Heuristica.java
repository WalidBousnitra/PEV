package mutaciones;

import java.util.ArrayList;
import java.util.List;

import Individuos.Individuo;

public class Heuristica<T> extends AlgoritmosMutacion<T>{

	public Heuristica(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		//Generamos el numero de ciudades a mutar
		int numciudades = getRand().nextInt(0,individuo.getCromosoma().size()+1);
		List<T> mejor = new ArrayList<T>(individuo.getCromosoma().size());
		mejor = individuo.getCromosoma();
		//Array que contiene las posicones originales
		int[] posiciones= new int[numciudades];  
		
		for(int i = 0 ; i< numciudades; i++) {
			posiciones[i]= getRand().nextInt(0,individuo.getCromosoma().size());
			individuo.getCromosoma().set(posiciones[i], null);
		}
		mejor = individuo.getCromosoma();
		for(int i = 0 ; i< individuo.getCromosoma().size()-numciudades; i++) {
			if(mejor.get(i)==null){
				mejor.remove(i);
				i--;
			}
		}
	}

}
