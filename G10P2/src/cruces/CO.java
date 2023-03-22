package cruces;

import java.util.ArrayList;
import java.util.List;

import Individuos.Individuo;

public class CO<T> extends AlgoritmosCruce<Integer>{
	
	private Monopunto<Integer> cruce;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CO(double p) {
		super(p);
		cruce = new Monopunto(p);
	}

	@Override
	public List<Individuo<Integer>> pareja(Individuo<Integer> padre1, Individuo<Integer> padre2) {
		
		//listas dinamicas para codificar ambos padres a la vez
		List<Integer> listaDin = rellenarLista(padre1.getCromosoma().size());
		List<Integer> listaDin2 = rellenarLista(padre2.getCromosoma().size());
		List<Individuo<Integer>> hijos = new ArrayList<Individuo<Integer>>(2);

		for(int i = 0; i< padre1.getCromosoma().size();i++){
			int pos = listaDin.indexOf(padre1.getCromosoma().get(i));
			int pos2 = listaDin2.indexOf(padre2.getCromosoma().get(i));
			padre1.getCromosoma().set(i, pos);
			listaDin.remove(pos);
			padre2.getCromosoma().set(i, pos2);
			listaDin2.remove(pos2);
		}
		
		List<Individuo<Integer>> tmp = cruce.pareja(padre1, padre2);
		
		padre1.setCromosoma(tmp.get(0).getCromosoma());
		padre2.setCromosoma(tmp.get(1).getCromosoma());
		
		listaDin = rellenarLista(padre1.getCromosoma().size());
		listaDin2 = rellenarLista(padre1.getCromosoma().size());
		
		for(int i = 0; i< padre1.getCromosoma().size();i++){
			int pos = padre1.getCromosoma().get(i);
			int pos2 = padre2.getCromosoma().get(i);
			padre1.getCromosoma().set(i, listaDin.get(pos));
			listaDin.remove(pos);
			padre2.getCromosoma().set(i, listaDin2.get(pos2));
			listaDin2.remove(pos2);
		}
		
		hijos.add(crear(padre1));
		hijos.add(crear(padre2));
				
		return hijos;
	}
	
	public List<Integer> rellenarLista(int size){
		List<Integer> sol = new ArrayList<Integer>(size);
		for(int i = 1; i<size+2; i++) {
			sol.add(i);
		}
		sol.remove(24);
		return sol;
	}
}
