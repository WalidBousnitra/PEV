package analisis;

public class Parametros {

	//Clase para coger los parametros actuales de la interfaz
	
	private int tamPoblacion,maxGeneraciones;
	private double probCruce,probMutacion,probElitismo;
	private String metodoMutacion,metodoSeleccion,metodoCruce;
	private boolean[] marcados;
	
	public Parametros(int tamPoblacion, int maxGeneraciones, boolean[] marcados, double probElitismo,
			String metodoCruce, double probCruce, String metodoSeleccion, String metodoMutacion, double probMutacion) {
		this.tamPoblacion = tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.marcados = marcados;
		this.probElitismo = probElitismo;
		this.metodoCruce = metodoCruce;
		this.probCruce = probCruce;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoMutacion = metodoMutacion;
		this.probMutacion = probMutacion;
	}

	//Gentters necesarios
	public int getTamPoblacion() {return tamPoblacion;}
	public int getMaxGeneraciones() {return maxGeneraciones;}
	public double getProbCruce() {return probCruce;}
	public double getProbMutacion() {return probMutacion;}
	public double getProbElitismo() {return probElitismo;}
	public String getMetodoMutacion() {return metodoMutacion;}
	public String getMetodoSeleccion() {return metodoSeleccion;}
	public String getMetodoCruce() {return metodoCruce;}
	public boolean[] getMarcados() {return marcados;}

}
