package cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;

public class NuestroCruce<T> extends AlgoritmosCruce<T>{
	
	private AlgoritmosCruce<T> cruce1;
	private AlgoritmosCruce<T> cruce2;
	private Random rand;

	public NuestroCruce(double p) {
		super(p);
		rand = new Random();
	}

	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {
		
		//Seleccionamos al azar los cruces
		cruce1 = seleccion(rand.nextInt(0,3));
		cruce2 = seleccion(rand.nextInt(4,6));
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		//creamos los hijos para cada cruce
		List<Individuo<T>> hijosCruce1 = cruce1.pareja(padre1, padre2);
		List<Individuo<T>> hijosCruce2 = cruce2.pareja(padre1, padre2);
		
		//ordenamos e mayor a menor (mejor a peor)
		Collections.sort(hijosCruce1);
		Collections.reverse(hijosCruce1);
		Collections.sort(hijosCruce2);
		Collections.reverse(hijosCruce2);
		
		//cogemos los mejores de cada cruce (los cuales estan el la primera posicion por estar ordenados de mayor a menor)
		hijos.add(crear(hijosCruce1.get(0)));
		hijos.add(crear(hijosCruce2.get(0)));
		
		return hijos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AlgoritmosCruce<T> seleccion(int nextInt) {
		switch (nextInt) {
		case 0:
			return new CO(p);
		case 1:
			return new CX(p);
		case 2:
			return new ERX(p);
		case 3:
			return new OX(p);
		case 4:
			return new OXOP(p);
		case 5:
			return new OXPP(p);
		case 6:
			return new PMX(p);
		}
		return null;
	}
	
	

}
