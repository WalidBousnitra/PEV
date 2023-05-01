package mutaciones;

import Individuo.Individuo;

public class Contraccion extends AlgoritmosMutacion<Integer>{


	public Contraccion(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		//Haces un cambio de terminal seleccionando al azar uno de los nodos funcionales
		int p= getValidIndx(individuo.getCromosoma().getNumFunciones(),individuo.getCromosoma().getIzquierda().getNumFunciones());
		if(p!=-1)
			individuo.getCromosoma().hacerTerminal(p);
	}

}
