package mutaciones;

import Individuos.Individuo;

public class Insercion<T> extends AlgoritmosMutacion<T>{

	public Insercion(double probMutacion) {
		super(probMutacion);
	}
	
	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		//Generamos el numero de ciudades a mutar
		int numciudades = getRand().nextInt(0,individuo.getCromosoma().size()+1);
		
		//Para cada ciudad se extrae y se posiciona en una posicion aletoria
		for(int i = 0; i <numciudades; i++) {
			int elegido = getRand().nextInt(0, individuo.getCromosoma().size());
			T gen = individuo.getCromosoma().get(elegido);
			individuo.getCromosoma().remove(elegido);
			int pos = getRand().nextInt(0, individuo.getCromosoma().size());
			individuo.getCromosoma().add(pos,gen);
		}
	}

}
