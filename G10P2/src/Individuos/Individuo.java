package Individuos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Individuo<T> implements Comparable<Individuo<T>>{
	
	//Atributos
	private Random rand;
	private List<T> cromosoma;
	private int tamTotal;
	
	public Individuo(int tamTotal) {
		
		//Inicializacion de atributos;
		this.rand = new Random();
		this.cromosoma = iniCromosoma(getRand());
		this.tamTotal= tamTotal;
	}
	
	public Individuo(Individuo<T> obj) {
		this.rand = new Random();
		this.cromosoma = new ArrayList<T>(obj.cromosoma.size());
		for(int i = 0 ; i < obj.cromosoma.size(); ++i){
			this.cromosoma.add(obj.cromosoma.get(i));
		}
	}
	
	//Funciones de calculos de distintos valores
	public double getFitness() {
		return this.getValor();
	}
	
	public double getFenotipo(int var) {
		 return (double) getCromosoma().get(var);
	}
	
	//Abstracts que requieren todas las funciones
	public abstract double getValor();
	public abstract List<T> iniCromosoma(Random rand);

	//Getters y setters necesarios
	public List<T> getCromosoma() {return cromosoma;}
	public void setCromosoma(List<T> cromosoma) {this.cromosoma = cromosoma;}
	public Random getRand() {return rand;}
	public void setRand(Random rand) {this.rand = rand;}
	public int getTamTotal() {	return tamTotal;}

	public void setTamTotal(int tamTotal) {	this.tamTotal = tamTotal;}


}