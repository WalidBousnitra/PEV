package Main;
import java.util.ArrayList;
import java.util.List;

import Individuos.Individuo;
import funciones.IndividuoFuncion1;
import mutaciones.Mutacion;

class AlgoritmoGenetico {

	private int tamPoblacion;
	private int maxGeneraciones;
	private double probCruce;
	private Mutacion mutacion;
	private double precision;
	private String funcion;
	private String metodoSeleccion;
	private String metodoCruce;
	private double probElitismo;
	private int d;
	private List<Individuo> poblacion;
	private double[] fitness;
	private int tamTorneo;
	private Individuo elMejor;
	private int pos_mejor;
	
	
	public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones, double probCruce, double probMutacion, double precision, 
						String funcion, String metodoSeleccion, String metodoCruce, double probElitismo, int d) {
		this.tamPoblacion =  tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.probCruce = probCruce;
		this.mutacion = new Mutacion(probMutacion);
		this.precision = precision;
		this.funcion = funcion;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoCruce = metodoCruce;
		this.poblacion = new ArrayList<Individuo>();
		this.fitness = new double[tamPoblacion];
		
	}

	public void run() {
		
		for(int i = 0; i < tamPoblacion; i++) {
			poblacion.add(new IndividuoFuncion1(precision));
			fitness[i] = poblacion.get(i).getFitness();
		}
		
		for(int i = 0; i < maxGeneraciones; i++) {
			//Seleccion
			//Cruce
			for(int j = 0; i < tamPoblacion; i++) {
				mutacion.mutar(poblacion.get(j));
			}
			//evaluar poblaci�n
		}
		//Devolver mejor
	}
}