package mutaciones;

import java.util.Collections;

import Individuos.Individuo;

public class Intercambio<T> extends AlgoritmosMutacion<T>{

	public Intercambio(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		//Elegimos los 2 genes a intercambiar
		int pos1 = getRand().nextInt(0,individuo.getCromosoma().size());
		int pos2 = getRand().nextInt(0,individuo.getCromosoma().size());
		
		Collections.swap(individuo.getCromosoma(), pos1, pos2);
	}

}
