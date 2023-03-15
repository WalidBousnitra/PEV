package mutaciones;

import java.util.ArrayList;
import java.util.List;

import Individuos.Individuo;
import funciones.Viajero;

public class Heuristica<T> extends AlgoritmosMutacion<T>{

	public Heuristica(double probMutacion) {
		super(probMutacion);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		//Generamos el numero de ciudades a mutar
		int numciudades = 3;
		List<T> mejor = new ArrayList<T>(individuo.getCromosoma().size());
		mejor = individuo.getCromosoma();
		int[] posiciones= new int[numciudades];
		int r1 = getRand().nextInt(0,individuo.getCromosoma().size());
		int r2 = getRand().nextInt(0,individuo.getCromosoma().size());
		int r3 = getRand().nextInt(0,individuo.getCromosoma().size());
		
		while(r1==r2||r2==r3||r3==r1) {
			r2 = getRand().nextInt(0,individuo.getCromosoma().size());
			r3 = getRand().nextInt(0,individuo.getCromosoma().size());
		}
		posiciones[0] = r1;
		posiciones[1]= r2;
		posiciones[2]= r3;
		
		//Array que contiene las posicones originales
		int[][] valores = permutar(individuo,numciudades,6);
		int fitness = Integer.MAX_VALUE;
		
		@SuppressWarnings("unchecked")
		Individuo<T> aux= new Viajero(individuo);
		for(int j= 0 ; j<6;j++) {
			for(int i= 0; i<individuo.getCromosoma().size();i++) {
				if(i==posiciones[0]) {
					aux.getCromosoma().set(i,valores[j][0]);
				}
				else if(i==posiciones[1]) {
					aux.getCromosoma().set(i,valores[j][1]);
				}
				else if(i==posiciones[2]) {
					aux.getCromosoma().set(i,valores[j][2]);
				}
				else {
					aux.getCromosoma().set(i,individuo.getCromosoma().get(i));
				}
				
			}
			if(fitness<aux.getFitness()) {
				mejor = aux.getCromosoma();
				individuo.setCromosoma(mejor);
			}
		}

	}
	
	public int[][] permutar(Individuo<T> individuo,int numciudades, int numperm){
		int[][] valores= new int[numperm][numciudades];
		
		return valores;
	}
}
