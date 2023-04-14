package Individuos;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import TAD.Arbol;

public abstract class Individuo<T> implements Comparable<Individuo<T>>{
	
	//Atributos
	private Arbol cromosoma;
	private DecimalFormat f = new DecimalFormat("0.0000");
	
	public Individuo() {
		//Inicializacion de atributos
		this.cromosoma = new Arbol();
		f.setRoundingMode(RoundingMode.DOWN);
	}
	
	// Constructor de copia
	public Individuo(Individuo<T> obj) {
		this.cromosoma = new Arbol(obj.getCromosoma());
	}
	
	//Funciones de calculos de distintos valores
	public double getFitness() {
		return this.getValor();
	}
	
	//Abstracts que requieren todas las funciones
	public abstract double getValor();
	public abstract String formula();
	public abstract double[] gx();
	
	//Funcion para dar el formato necesario
	public double formato(double num) {
		
		String str = f.format(num);
		double sol = 0;
		
		if( str.indexOf(",") != -1 )
		     str = str.replace(',','.');
		
		sol = Double.parseDouble(str);
		
		return sol;
	}

	//Getters y setters necesarios
	public Arbol getCromosoma() {return cromosoma;}
	public void setCromosoma(Arbol cromosoma) {this.cromosoma = cromosoma;}

}