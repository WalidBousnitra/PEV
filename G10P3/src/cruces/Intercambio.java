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
		
		int i1 = rand.nextInt(0,puntoHijo1);
		int i2 = rand.nextInt(0,puntoHijo2);
		int i3 = rand.nextInt(0,puntoHijo1);
		int i4 = rand.nextInt(0,puntoHijo2);
		
		Arbol aux = hijo1.getCromosoma().extraer(i1);
		Arbol aux2 = hijo1.getCromosoma().extraer(i2);
		
		hijo1.getCromosoma().print("", hijo1.getCromosoma(), false);
		System.out.println("\n\n\n");
		aux.print("", aux, false);
		System.out.println("\n\n\n");
		hijo2.getCromosoma().print("", hijo2.getCromosoma(), false);
		aux2.print("", aux2, false);
		System.out.println("\n\n\n");
		
		hijo1.getCromosoma().insertar(i3, aux2);
		hijo1.getCromosoma().print("", hijo1.getCromosoma(), false);
		System.out.println("\n\n\n");
		hijo2.getCromosoma().insertar(i4, aux);
		hijo2.getCromosoma().print("", hijo2.getCromosoma(), false);
		System.out.println("\n\n\n");
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		
		return hijos;
		
	}

}
