package Individuos;

public abstract class Individuo<T> {
	private T[] cromosoma;
	int[] tamGenes;
	
	 public abstract void getValor();

	public T[] getCromosoma() {
		return cromosoma;
	}

	public void setCromosoma(T[] cromosoma) {
		this.cromosoma = cromosoma;
	}
}