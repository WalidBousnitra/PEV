package cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Individuos.Individuo;

public class ERX<T> extends AlgoritmosCruce<Integer>{
	
	private Map<Integer,List<Integer>>mAdy;
	private Random rand;
	
	public ERX(double p) {
		super(p);
		mAdy = new HashMap<Integer,List<Integer>>(26);
		for(int i = 1; i<=27;i++) {
			if(i!=25) {
				mAdy.put(i, new ArrayList<Integer>());
			}
		}
		rand = new Random();
	}

	@Override
	public List<Individuo<Integer>> pareja(Individuo<Integer> padre1, Individuo<Integer> padre2) {

		crearMatriz(padre1.getCromosoma(),padre2.getCromosoma());
		List<Individuo<Integer>> hijos = new ArrayList<Individuo<Integer>>(2);
		Individuo<Integer> hijo1 = crear(padre2);
		Individuo<Integer> hijo2 = crear(padre1);
		
		for(int i = 1; i<padre2.getCromosoma().size();i++) {
			int cosica =selecionar(hijo1.getCromosoma().get(i-1));
			hijo1.getCromosoma().set(i, cosica);
		}
		reset();
		crearMatriz(padre1.getCromosoma(),padre2.getCromosoma());
		
		for(int i = 1; i<padre1.getCromosoma().size();i++) {
			int cosica2 =selecionar(hijo2.getCromosoma().get(i-1));
			hijo2.getCromosoma().set(i, cosica2);
		}
		
		hijos.add(hijo1);
		hijos.add(hijo2);
		reset();
		return hijos;
	}
	
	private void reset() {
		mAdy.clear();
		for(int i = 1 ; i<=27;i++) {
			if(i!=25) {
				mAdy.put(i, new ArrayList<Integer>());
			}
		}
	}
	
	//fucion que devulve el elemento a sustituir en el gen
	public Integer selecionar(Integer elem) {
		
		//para los casos en los que la matriz de adyacencia llega a un punto de bloqueo
		//se elige un valor al azar de los que ya existen
		if(!mAdy.containsKey(elem) || mAdy.get(elem).isEmpty()) {
			mAdy.remove(elem);
			List<Integer> keysAsArray = new ArrayList<Integer>(mAdy.keySet());
			return keysAsArray.get(rand.nextInt(keysAsArray.size()));
		}
		int newElem = mAdy.get(elem).get(0);
		int min = mAdy.get(mAdy.get(elem).get(0)).size();
		for(int i = 1; i<mAdy.get(elem).size();i++) {
			//se coge el elemento con menor tamaño de lista de adyacencia
			if(mAdy.get(mAdy.get(elem).get(i)).size()<min) {
				newElem = mAdy.get(elem).get(i);
				min = mAdy.get(mAdy.get(elem).get(i)).size();
			}
			// en los casos de mismo tamaño actualizamos aleatoriamente
			else if(mAdy.get(mAdy.get(elem).get(i)).size()==min) {
				if(rand.nextDouble()>0.5) {
					newElem = mAdy.get(elem).get(i);
				}
			}
		}
		//se eliminan los elemntos de la matriz de adyacencia
		mAdy.remove(elem);
		for(List<Integer> obj : mAdy.values()) {
			for(int i = 0 ; i<obj.size();i++) {
				if(obj.get(i)==elem) {
					obj.remove(i);
					continue;
				}
			}
		}
		return newElem;
		 
	}	
	
	//Funcion para crear la matriz de adyacencia en para cada par de padres
	public void crearMatriz(List<Integer> padre1, List<Integer> padre2){
		
		//Añadimos la adyacencia de los primeros elementos porque no tiene elemento anterior
		mAdy.get(padre1.get(0)).add(padre1.get(1));
		mAdy.get(padre1.get(0)).add(padre1.get(padre1.size()-1));
		
		if(!mAdy.get(padre2.get(0)).contains(padre2.get(1)))
			mAdy.get(padre2.get(0)).add(padre2.get(1));
		if(!mAdy.get(padre2.get(0)).contains(padre2.get(padre2.size()-1)))
			mAdy.get(padre2.get(0)).add(padre2.get(padre2.size()-1));
		
		for(int i  = 1;i <padre1.size();i++) {
			//Para cada elemento añadimos el elemento anterior y posterior
			if(!mAdy.get(padre1.get(i)).contains(padre1.get(i-1)))
				mAdy.get(padre1.get(i)).add(padre1.get(i-1));
			if(!mAdy.get(padre1.get(i)).contains(padre1.get((i+1)%padre1.size())))
				mAdy.get(padre1.get(i)).add(padre1.get((i+1)%padre1.size()));
			if(!mAdy.get(padre2.get(i)).contains(padre2.get(i-1)))
				mAdy.get(padre2.get(i)).add(padre2.get(i-1));
			if(!mAdy.get(padre2.get(i)).contains(padre2.get((i+1)%padre1.size())))
				mAdy.get(padre2.get(i)).add(padre2.get((i+1)%padre1.size()));
		}		
	}

}
