package cruces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Individuos.Individuo;

public class CX<T> extends AlgoritmosCruce<T>{

	//array para marcar las posiciones que no hay cambiar
	private boolean[] cambio;
	
	public CX(double p) {
		super(p);
		cambio = new boolean[26];
	}

	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {
		
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		
		marcar(padre1,padre2);
		
		for(int i = 0 ; i<padre1.getCromosoma().size();i++) {
			//para los elementos sin marcar realiza el cambio
			if(!cambio[i]) {
				T aux = padre1.getCromosoma().get(i);
				padre1.getCromosoma().set(i, padre2.getCromosoma().get(i));
				padre2.getCromosoma().set(i, aux);				
			}
		}
		
		hijos.add(crear(padre1));
		hijos.add(crear(padre2));
		vaciar();
		return hijos;
	}
	
	//funcion que marca las posicones a cambiar para cada par de padres
	public void marcar(Individuo<T> padre1, Individuo<T> padre2) {
		//funcion que contiene los elementos que pertenecen al ciclo;
		Set<T> ciclo = new HashSet<T>(padre1.getCromosoma().size());
		ciclo.add(padre1.getCromosoma().get(0));
		
		int pos = padre1.getCromosoma().indexOf(padre2.getCromosoma().get(0));
		cambio[0] = true;
		
		while(!ciclo.contains(padre1.getCromosoma().get(pos))) {
			ciclo.add(padre1.getCromosoma().get(pos));
			cambio[pos] = true;
			pos = padre1.getCromosoma().indexOf(padre2.getCromosoma().get(pos));
		}
	}

	//funcion para resetear el array cambios
	private void vaciar() {
		for(int i = 0; i < 26; i++) {
			cambio[i] = false;
		}
	}
	
}
