package mutaciones;

import Individuo.Individuo;

public class Funcional extends AlgoritmosMutacion<Integer>{

	public Funcional(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		//Haces un cambio de funcion seleccionando al azar uno de los nodos funcionales
		int p = getValidIndx(individuo.getCromosoma().getNumFunciones(),individuo.getCromosoma().getIzquierda().getNumFunciones());
		if(p!=-1)
			individuo.getCromosoma().cambioFuncion(p);
	}
}
