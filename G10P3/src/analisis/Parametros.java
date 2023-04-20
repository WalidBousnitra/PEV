package analisis;

public class Parametros {

	//Clase para coger los parametros actuales de la interfaz
	
	private int tamPoblacion, min, max, maxGeneraciones;
	private double probCruce,probMutacion,probElitismo;
	private String metodoMutacion,metodoSeleccion,metodoInicializacion;
	private boolean[] marcados;
	
	public Parametros(int tamPoblacion, int min, int max, int maxGeneraciones, boolean[] marcados, double probElitismo,
			String metodoInicializacion, double probCruce, String metodoSeleccion, String metodoMutacion, double probMutacion) {
		this.tamPoblacion = tamPoblacion;
		this.min = min;
		this.max = max;
		this.maxGeneraciones = maxGeneraciones;
		this.marcados = marcados;
		this.probElitismo = probElitismo;
		this.metodoInicializacion = metodoInicializacion;
		this.probCruce = probCruce;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoMutacion = metodoMutacion;
		this.probMutacion = probMutacion;
	}

	//Gentters necesarios
	public int getTamPoblacion() {return tamPoblacion;}
	public int getMin() {return min;}
	public int getMax() {return max;}
	public int getMaxGeneraciones() {return maxGeneraciones;}
	public double getProbCruce() {return probCruce;}
	public double getProbMutacion() {return probMutacion;}
	public double getProbElitismo() {return probElitismo;}
	public String getMetodoMutacion() {return metodoMutacion;}
	public String getMetodoSeleccion() {return metodoSeleccion;}
	public String getMetodoInicializacion() {return metodoInicializacion;}
	public boolean[] getMarcados() {return marcados;}

}
