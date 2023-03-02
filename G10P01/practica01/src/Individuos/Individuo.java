package Individuos;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Individuo<T> implements Comparable<Individuo<T>>{
	
	//Atributos
	private Random rand;
	private int[] tamGenes;
	private double[] min;
	private double[] max;
	private DecimalFormat formato;
	private double valorError;
	private String precision;
	private int tamTotal;
	private List<T> cromosoma;
	private int d;
	
	public Individuo(int[] tamGenes, String precision, double[] min, double[] max) {
		
		//Inicializacion de atributos;
		this.rand = new Random();
		this.setMin(min);
		this.setMax(max);
		this.setTamGenes(tamGenes);
		this.precision = precision;
		this.formato = new DecimalFormat(precision);
		this.valorError = Double.parseDouble(precision);
		int sum = 0;
		for(int i = 0 ; i < tamGenes.length; ++i){
			tamGenes[i] = tamGen(this.valorError, min[i], max[i]);
			sum+=tamGenes[i];
		}
		this.setTamTotal(sum);
		this.cromosoma = iniCromosoma(getRand());
	}
	public Individuo(int[] tamGenes, String precision, double[] min, double[] max, int d) {
		
		//Inicializacion de atributos;
		this.rand = new Random();
		this.setMin(min);
		this.setMax(max);
		this.setTamGenes(tamGenes);
		this.precision = precision;
		this.formato = new DecimalFormat(precision);
		this.valorError = Double.parseDouble(precision);
		int sum = 0;
		for(int i = 0 ; i < tamGenes.length; ++i){
			tamGenes[i] = tamGen(this.valorError, min[i], max[i]);
			sum+=tamGenes[i];
		}
		this.setTamTotal(sum);
		this.cromosoma = iniCromosoma(getRand());
		this.d = d;
	}
	
	public Individuo(boolean marca, int[] tamGenes, String precision, double[] min, double[] max, int d) {
		
		//Inicializacion de atributos;
		this.rand = new Random();
		this.setMin(min);
		this.setMax(max);
		this.setTamGenes(tamGenes);
		this.precision = precision;
		this.formato = new DecimalFormat(precision);
		this.valorError = Double.parseDouble(precision);
		this.setTamTotal(d);
		this.cromosoma = iniCromosoma(getRand());
		this.d = d;
	}

	public Individuo(Individuo<T> obj) {
		this.rand = new Random();
		min = new double[obj.min.length];
		min = new double[obj.min.length];
		this.min = obj.min;
		this.max = obj.max;
		this.precision = obj.precision;
		this.formato = new DecimalFormat(obj.precision);
		this.valorError = obj.valorError;
		tamGenes = new int[obj.tamGenes.length];
		for(int i = 0 ; i < tamGenes.length; ++i){
			tamGenes[i] = obj.tamGenes[i];
		}
		this.tamTotal = obj.tamTotal;
		this.cromosoma = new ArrayList<T>(obj.cromosoma.size());
		for(int i = 0 ; i < obj.cromosoma.size(); ++i){
			this.cromosoma.add(obj.cromosoma.get(i));
		}
		this.d = obj.d;
	}
	
	//Funciones de calculos de distintos valores
	public double getFitness() {
		return this.getValor();
	}
	public int tamGen(double valorError, double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	public double getFenotipo(int var) {
		 return formato(min[var] + Integer.parseInt(getGenotipo(var),2) * ((max[var] - min[var])/(Math.pow(2,getTamGenes()[var])-1)));
	}
	
	//Funcion para dar el formato necesario
	public double formato(double num) {
		
		String str = formato.format(num);
		double sol = 0;
		
		if( str.indexOf(",") != -1 )
		     str = str.replace(',','.');
		
		sol = Double.parseDouble(str);
		
		return sol;
	}
	
	//Abstracts que requieren todas las funciones
	public abstract double getValor();
	public abstract List<T> iniCromosoma(Random rand);
	protected abstract String getGenotipo(int var);
	public abstract void mutar(int i);

	//Getters y setters necesarios
	public double[] getMin() {return min;}
	public void setMin(double[] min) {this.min = min;}
	public double[] getMax() {return max;}
	public void setMax(double[] max) {this.max = max;}
	public int[] getTamGenes() {return tamGenes;}
	public void setTamGenes(int[] tamGenes) {this.tamGenes = tamGenes;}
	public int getTamTotal() {return tamTotal;}
	public void setTamTotal(int tamTotal) {this.tamTotal = tamTotal;}
	public List<T> getCromosoma() {return cromosoma;}
	public void setCromosoma(List<T> cromosoma) {this.cromosoma = cromosoma;}
	public Random getRand() {return rand;}
	public void setRand(Random rand) {this.rand = rand;}
	public DecimalFormat getFormato() {	return formato;	}
	public void setFormato(DecimalFormat formato) {	this.formato = formato;	}
	public double getValorError() {	return valorError;}
	public void setValorError(double valorError) {	this.valorError = valorError;}
	public String getPrecision() {	return precision;}
	public void setPrecision(String precision) {this.precision = precision;	}

}