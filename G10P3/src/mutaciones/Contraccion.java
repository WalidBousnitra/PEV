package mutaciones;

import java.util.Random;

import Individuos.Individuo;

public class Contraccion extends AlgoritmosMutacion<Integer>{

	private Random rand;

	public Contraccion(double probMutacion) {
		super(probMutacion);
		rand = new Random();
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		//Haces un cambio de terminal seleccionando al azar uno de los nodos funcionales
		int p= rand.nextInt(1,individuo.getCromosoma().getNumFunciones()+1);
		while(p==individuo.getCromosoma().getIzquierda().getNumFunciones()+1) {
			 p=rand.nextInt(1,individuo.getCromosoma().getNumFunciones()+1);
		}
		individuo.getCromosoma().hacerTerminal(p);
	}

}
