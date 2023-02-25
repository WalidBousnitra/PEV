package Individuos;
import java.util.List;
import java.util.Random;

public abstract class Individuo<T> {
	
	private List<T> cromosoma;
	private int[] tamGenes;
	private double[] min;
	private double[] max;
	private int tamTotal;
	private double valorError;
	private Random rand;
	
	public Individuo(int[] tamGenes, double valorError, double[] min, double[] max) {
		this.rand = new Random();
		this.setTamGenes(tamGenes);
		this.valorError = valorError;
		this.setMin(min);
		this.setMax(max);
		int sum = 0;
		for(int i = 0 ; i < tamGenes.length; ++i){
			tamGenes[i] = tamGen(this.valorError, min[i], max[i]);
			sum+=tamGenes[i];
		}
		this.setTamTotal(sum);
		this.cromosoma = iniCromosoma(getRand());
	}

	public abstract double getValor();
	public abstract List<T> iniCromosoma(Random rand);
	protected abstract String getGenotipo(int var);
	public abstract void mutar(int i);
	
	public double getFitness() {
		return this.getValor();
	}
	
	public int tamGen(double valorError, double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getFenotipo(int var) {
		 return min[var] + Integer.parseInt(getGenotipo(var),2) * ((max[var] - min[var])/(Math.pow(2,getTamTotal())-1));
	}

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

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}
}