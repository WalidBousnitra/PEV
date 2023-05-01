package mutaciones;

import Individuo.Individuo;

public class Terminal<T> extends AlgoritmosMutacion<Integer>{

	public Terminal(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		
		//Haces un cambio de terminal seleccionando al azar uno de los nodos terminales
		int p = getValidIndx(individuo.getCromosoma().getNumTerminales(),individuo.getCromosoma().getIzquierda().getNumTerminales());
		individuo.getCromosoma().cambioTerminal(p);
	}

}
