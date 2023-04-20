package mutaciones;

import java.util.Random;

import Individuos.Individuo;

public class Funcional extends AlgoritmosMutacion<Integer>{

	private Random rand;

	public Funcional(double probMutacion) {
		super(probMutacion);
		rand = new Random();
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		//Haces un cambio de terminal seleccionando al azar uno de los nodos funcionales
		individuo.getCromosoma().cambioTerminal(rand.nextInt(0,individuo.getCromosoma().getN()-individuo.getCromosoma().getNumTerminales()));
	}

}
