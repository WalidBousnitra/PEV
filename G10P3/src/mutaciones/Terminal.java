package mutaciones;

import java.util.Random;

import Individuo.Individuo;

public class Terminal<T> extends AlgoritmosMutacion<Integer>{
	
	private Random rand;

	public Terminal(double probMutacion) {
		super(probMutacion);
		rand = new Random();
	}

	@Override
	public void cromosoma(Individuo<Integer> individuo) {
		
		//Haces un cambio de terminal seleccionando al azar uno de los nodos terminales
		individuo.getCromosoma().cambioTerminal(rand.nextInt(1,individuo.getCromosoma().getNumTerminales()+1));
	}

}
