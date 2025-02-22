package mutaciones;

import Individuo.Individuo;

public class Permutacion extends AlgoritmosMutacion<Integer>{

	public Permutacion(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		int p = getValidIndx(individuo.getCromosoma().getNumFunciones(),individuo.getCromosoma().getIzquierda().getNumFunciones());
		if(p!=-1)
			individuo.getCromosoma().permutarNodo(p);
	}
}
