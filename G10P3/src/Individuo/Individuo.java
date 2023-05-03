package Individuo;

import java.math.RoundingMode;

import java.text.DecimalFormat;

import TAD.Arbol;
import bloating.Bloating;

public abstract class Individuo<T> implements Comparable<Individuo<T>>{
	
	//Atributos
	private Arbol cromosoma;
	private DecimalFormat f = new DecimalFormat("0.0000");
	private DecimalFormat f2 = new DecimalFormat("0.00");
	private Bloating _k;
	private double error;
	
	public Individuo(Bloating k,int min ,int max, String tipo) {
		//Inicializacion de atributos
		set_k(k);
		this.cromosoma = new Arbol(min, max, tipo);
		f.setRoundingMode(RoundingMode.DOWN);
		error=0;
	}
	
	// Constructor de copia
	public Individuo(Individuo<T> obj) {
		this.cromosoma = new Arbol(obj.getCromosoma());
		set_k(obj.get_k());
		f.setRoundingMode(RoundingMode.DOWN);
		error = obj.getError();
	}
	
	//Funciones de calculos de distintos valores
	public double getFitness() {
		return this.getValor();
	}
	
	//Abstracts que requieren todas las funciones
	public abstract double getValor();
	public abstract String formula();
	public abstract double[] gx();
	
	public double getError() {
		return formato(error);
	}
	
	//Funcion para dar el formato necesario
	public double formato(double num) {
		
		String str = f.format(num);
		double sol = 0;
		
		if( str.indexOf(",") != -1 )
		     str = str.replace(',','.');
		
		sol = Double.parseDouble(str);
		
		return sol;
	}
	
	public double formato2(double num) {
		
		String str = f2.format(num);
		double sol = 0;
		
		if( str.indexOf(",") != -1 )
		     str = str.replace(',','.');
		
		sol = Double.parseDouble(str);
		
		return sol;
	}

	//Getters y setters necesarios
	public Arbol getCromosoma() {return cromosoma;}
	public void setCromosoma(Arbol cromosoma) {this.cromosoma = cromosoma;}
	public Bloating get_k() {	return _k;}
	public void set_k(Bloating _k) {this._k = _k;}
	public abstract double getFitness2();
	public void setError(double error) {this.error = error;	}

}