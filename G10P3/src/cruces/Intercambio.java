package cruces;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import Individuo.Individuo;
import bloating.Bloating;
import TAD.Arbol;

public class Intercambio<T> extends AlgoritmosCruce<Integer>{

	private Random rand;
	private Bloating _k;
	
	public Intercambio(double p, Bloating k) {
		super(p);
		_k = k;
		rand = new Random();
	}

	@Override
	public List<Individuo<Integer>> pareja(Individuo<Integer> padre1, Individuo<Integer> padre2) {
		
		List<Individuo<Integer>> hijos = new ArrayList<Individuo<Integer>>(2);
		Individuo<Integer> hijo1 = crear(padre1);
		Individuo<Integer> hijo2 = crear(padre2);
		
		//puntos de corte;
		int p1 = 0;
		int p2 = 0;
		Arbol aux = null;
		Arbol aux2 = null;
		
		if(rand.nextDouble()<=0.9) {
			
			p1 = getValidIndx(hijo1.getCromosoma().getNumFunciones(),hijo1.getCromosoma().getIzquierda().getNumFunciones());
			p2 = getValidIndx(hijo2.getCromosoma().getNumFunciones(),hijo2.getCromosoma().getIzquierda().getNumFunciones());

			aux = hijo1.getCromosoma().extraerFuncion(p1);
			aux2 = hijo2.getCromosoma().extraerFuncion(p2);
			
			hijo1.getCromosoma().insertarFuncion(p1, aux2);
			hijo2.getCromosoma().insertarFuncion(p2, aux);
		}
		else {
			p1 = getValidIndx(hijo1.getCromosoma().getN(),hijo1.getCromosoma().getIzquierda().getN());
			p2 = getValidIndx(hijo2.getCromosoma().getN(),hijo2.getCromosoma().getIzquierda().getN());

			aux = hijo1.getCromosoma().extraer(p1);
			aux2 = hijo2.getCromosoma().extraer(p2);
			
			hijo1.getCromosoma().insertar(p1, aux2);
			hijo2.getCromosoma().insertar(p2, aux);
		}	
		if(_k.is_activado()) {
			if(hijo1.getFitness2()>padre1.getFitness2()) {
				hijos.add(crear(padre1));
			}
			else {
				hijos.add(hijo1);
			}
			if(hijo2.getFitness2()>padre2.getFitness2()) {
				hijos.add(crear(padre2));
			}
			else {
				hijos.add(hijo2);
			}
		}
		else {
			hijos.add(hijo1);
			hijos.add(hijo2);
		}
		return hijos;
		
	}
	
	public int getValidIndx(int tam, int tamIzq) {
		int p = rand.nextInt(1,tam+1);
		if(tamIzq+1 !=1) {
			while(p==tamIzq+1) {
				 p=rand.nextInt(1,tam+1);
			}
		}
		else{
			p = 1;
		}
		
		return p;
	}

}
