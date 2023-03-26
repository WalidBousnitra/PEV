package mutaciones;

import java.util.ArrayList;
import java.util.List;

import Individuos.Individuo;
import funciones.Viajero;

public class Heuristica<T> extends AlgoritmosMutacion<T>{

	//Numero de ciudades a mutar
	private int numciudades;
	//Posiciones a permutar contenidos
	private int[] posiciones;
	//Numero de permutaciones;
	private int numperm;
	private List<Individuo<T>> combinaciones;
	private int pos;
	
	public Heuristica(double probMutacion) {
		super(probMutacion);
		numciudades = 3;
		numperm = 6;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		posiciones= new int[]{	getRand().nextInt(0,individuo.getCromosoma().size()),
								getRand().nextInt(0,individuo.getCromosoma().size()),
								getRand().nextInt(0,individuo.getCromosoma().size())};
		
		// Comprobamos que sean diferentes
		while(posiciones[0]==posiciones[1]||posiciones[1]==posiciones[2]||posiciones[2]==posiciones[0]) {
			posiciones[1] = getRand().nextInt(0,individuo.getCromosoma().size());
			posiciones[2] = getRand().nextInt(0,individuo.getCromosoma().size());
		}
		
		//Lista con numperm individuos que empiezan con el mismo cromosoma que el individuo original
		//Son individuos para calcular facilmente el fitness
		combinaciones = new ArrayList<Individuo<T>>(numperm);
		pos = 0;
		for(int i = 0; i<numperm;i++)
			combinaciones.add(new Viajero(individuo));
	
		//elementos a combinar
		List<T> array = new ArrayList<T>(numciudades);
		array.add(individuo.getCromosoma().get(posiciones[0]));
		array.add(individuo.getCromosoma().get(posiciones[1]));
		array.add(individuo.getCromosoma().get(posiciones[2]));
		
		permutacion(array,numciudades,numciudades);
		
		// Seleccionamos la mejor combinacion
		for(int i = 0; i<numperm; i++)
			if(combinaciones.get(i).compareTo(individuo) == 1)
				individuo.setCromosoma(combinaciones.get(i).getCromosoma());
		combinaciones.clear();		
	}
	
	//algoritmo para permutar
	public void permutacion(List<T> array, int size, int n)  {
		if (size == 1)  
			editar(array);  
		for (int i = 0; i < size; i++){  
			permutacion(array, size - 1, n);  
			if (size % 2 == 1)  {  
				T temp = array.get(0);  
				array.set(0, array.get(size - 1));  
				array.set(size - 1,temp);  
			}  
			else {    
				T temp; 
				temp = array.get(i);  
				array.set(i,array.get(size - 1));  
				array.set(size - 1,temp);  
			}  
		}  
	}
	
	// Editar cromosoma de las combinaciones ya que empiezan siendo todas iguales
	public void editar(List<T> array) {
		combinaciones.get(pos).getCromosoma().set(posiciones[0],array.get(0));
		combinaciones.get(pos).getCromosoma().set(posiciones[1],array.get(1));
		combinaciones.get(pos).getCromosoma().set(posiciones[2],array.get(2));
		pos++;
	}
}
