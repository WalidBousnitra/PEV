package Individuos;

public abstract class Individuo<T> {
	
	private T[] cromosoma;
	int[] tamGenes;
	
	public abstract double getValor();
	
	public double getFitness() {
		return this.getValor();
	}
	
	public int tamGen(double valorError, double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
}