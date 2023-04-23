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
		individuo.getCromosoma().hacerTerminal(rand.nextInt(1,individuo.getCromosoma().getN()-individuo.getCromosoma().getNumTerminales()));
	}

}
