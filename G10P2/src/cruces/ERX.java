package cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Individuos.Individuo;

public class ERX<T> extends AlgoritmosCruce<T>{
	
	private Map<T,List<T>>mAdy;
	
	public ERX(double p) {
		super(p);
		mAdy = new HashMap<T,List<T>>(26);
	}

	@Override
	public List<Individuo<T>> pareja(Individuo<T> padre1, Individuo<T> padre2) {

		crearMatriz(padre1,padre2);
		List<Individuo<T>> hijos = new ArrayList<Individuo<T>>(2);
		
		for(int i = 0; i< padre1.getCromosoma().size();i++) {
			
			padre1.getCromosoma().set(i, selecionar(padre1.getCromosoma().get(i)));
			padre2.getCromosoma().set(i, selecionar(padre2.getCromosoma().get(i)));
		}
		
		hijos.add(padre1);
		hijos.add(padre2);
		mAdy.clear();
		return hijos;
	}
	
	//fucion que devulve el elemento a sustituir en el gen
	public T selecionar(T elem) {
		int indc = 0;
		int min= mAdy.get(mAdy.get((int) elem).get(0)).size();
		for(int i = 1; i< mAdy.get((int) elem).size();i++) {
			if(mAdy.get(mAdy.get((int) elem).get(i)).size()<min) {
				indc = (int) mAdy.get((int) elem).get(i);
				min = mAdy.get(mAdy.get((int) elem).get(i)).size();
			}
		}
		int i = 0;
		for (T key : mAdy.keySet()) {
			if(i==indc)
				return key;
		     i++;
		}
		return null; 
	}
	
	
	//Funcion para crear la matriz de adyacencia en para cada par de padres
	public void crearMatriz(Individuo<T> padre1, Individuo<T> padre2){
		
		//Añadimos la adyacencia de los primeros elementos porque no tiene elemento anterior		
		mAdy.get((int) padre1.getCromosoma().get(0)).add(padre1.getCromosoma().get(1));
		mAdy.get((int) padre2.getCromosoma().get(0)).add(padre2.getCromosoma().get(1));
		
		for(int i  = 1;i <padre1.getCromosoma().size()-1;i++) {
			//Para cada elemento añadimos el elemento anterior y posterior
			mAdy.get((int) padre1.getCromosoma().get(i)).add(padre1.getCromosoma().get(i-1));
			mAdy.get((int) padre1.getCromosoma().get(i)).add(padre1.getCromosoma().get(i+1));
			mAdy.get((int) padre2.getCromosoma().get(i)).add(padre2.getCromosoma().get(i-1));
			mAdy.get((int) padre2.getCromosoma().get(i)).add(padre2.getCromosoma().get(i+1));
		}
		
		int ultimo = padre1.getCromosoma().size()-1;
		
		//Añadimos la adyacencia de los ultimos elementos porque no tiene elemento posterior	
		mAdy.get((int) padre1.getCromosoma().get(ultimo)).add(padre1.getCromosoma().get(ultimo-1));
		mAdy.get((int) padre2.getCromosoma().get(ultimo)).add(padre2.getCromosoma().get(ultimo-1));
		
	}

}
