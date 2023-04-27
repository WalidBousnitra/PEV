package mutaciones;

import java.util.Random;

import Individuo.Individuo;

public class Permutacion extends AlgoritmosMutacion<Integer>{

	private Random rand;

	public Permutacion(double probMutacion) {
		super(probMutacion);
		rand = new Random();
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		individuo.getCromosoma().permutarNodo(rand.nextInt(1,individuo.getCromosoma().getNumFunciones()+1));
	}
}
