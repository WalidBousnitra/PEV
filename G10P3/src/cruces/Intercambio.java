package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;
import TAD.Arbol;

public class Intercambio<T> extends AlgoritmosCruce<Integer>{

	private Random rand;
	
	public Intercambio(double p) {
		super(p);
		rand = new Random();
	}

	@Override
	public List<Individuo<Integer>> pareja(Individuo<Integer> padre1, Individuo<Integer> padre2) {
		
		List<Individuo<Integer>> hijos = new ArrayList<Individuo<Integer>>(2);
		Individuo<Integer> hijo1 = crear(padre1);
		Individuo<Integer> hijo2 = crear(padre2);
		
		int puntoHijo1  = hijo1.getCromosoma().getN();
		int puntoHijo2  = hijo2.getCromosoma().getN();
		
		Arbol aux = hijo1.getCromosoma().extraer(rand.nextInt(0,puntoHijo1));
		Arbol aux2 = hijo1.getCromosoma().extraer(rand.nextInt(0,puntoHijo1));
		
		hijo1.getCromosoma().insertar(puntoHijo1, aux2);
		hijo2.getCromosoma().insertar(puntoHijo2, aux);
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		
		return hijos;
		
	}

}
