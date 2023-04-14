package Individuos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import TAD.Arbol;

public abstract class Individuo<T> implements Comparable<Individuo<T>>{
	
	//Atributos
	private Arbol cromosoma;
	
	public Individuo() {
		//Inicializacion de atributos
		this.cromosoma = new Arbol();
	}
	
	// Constructor de copia
	public Individuo(Individuo<T> obj) {
		this.cromosoma = obj.cromosoma;
	}
	
	//Funciones de calculos de distintos valores
	public double getFitness() {
		return this.getValor();
	}
	
	//Abstracts que requieren todas las funciones
	public abstract double getValor();

	//Getters y setters necesarios
	public Arbol getCromosoma() {return cromosoma;}
	public void setCromosoma(Arbol cromosoma) {this.cromosoma = cromosoma;}

}