package mutaciones;

import java.util.Collections;

import Individuos.Individuo;

public class Inversion<T> extends AlgoritmosMutacion<T>{

	public Inversion(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		//Elegimos los 2 genes a intercambiar forzando que la pos1 sea menos que pos2
		int pos1 = getRand().nextInt(0,individuo.getCromosoma().size()/2);
		int pos2 = getRand().nextInt(individuo.getCromosoma().size()/2,individuo.getCromosoma().size());
		
		//Swapeamos los elementos de fuera hacia dentro del segmento selecionado
		for(int i = 0; i< (pos2-pos1+1)/2; i++)
			Collections.swap(individuo.getCromosoma(), pos1+i, pos2-i);
	}

}
