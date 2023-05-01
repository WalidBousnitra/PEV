package mutaciones;

import java.util.List;
import java.util.Random;

import Individuo.Individuo;

public abstract class AlgoritmosMutacion<T>{
	
	private double p;
	private Random rand;
	private int generaciones;
	
	public AlgoritmosMutacion(double probMutacion) {
		p = probMutacion;
		rand = new Random();
		generaciones = 1;
	}
	
	//funcion que recorre la poblacion y muta los individuos elegidos
	public void mutar(List<Individuo<T>> poblacion) {
		
		//mutacion estacionaria
		if(generaciones%5==0) {
			generaciones*=0.95;
		}
		
		for(int i = 0 ; i < poblacion.size(); i++)
			if(rand.nextDouble()<=p)
				cromosoma(poblacion.get(i));
	}
	
	//funcion para mutar dentro de un mismo individuo
	public abstract void cromosoma(Individuo<T> individuo);
	
	//getter y setters necesarios
	public double getP() {return p;	}
	public void setP(double p) {this.p = p;}
	public Random getRand() {return rand;}
	public void setRand(Random rand) {this.rand = rand;	}

	public void actGen() {
		generaciones++;
	}
	
	public int getValidIndx(int tam, int tamIzq) {
		int p = rand.nextInt(1,tam+1);
		if(tamIzq+1 !=1) {
			while(p==tamIzq+1) {
				 p=rand.nextInt(1,tam+1);
			}
		}
		else{
			p = -1;
		}
		
		return p;
	}
}