package Main;

import java.util.ArrayList;
import java.util.List;
import Individuos.Individuo;
import cruces.*;
import elitismo.Elitismo;
import funciones.*;
import mutaciones.*;
import selecciones.*;

class AlgoritmoGenetico<T> {
	
	//Parametros	
	private int tamPoblacion;
	private int maxGeneraciones;
	private AlgoritmosCruce<T> cruce;
	private Mutacion mutacion;
	private String precision;
	private AlgoritmoSeleccion seleccion;
	private Elitismo<T> probElitismo;
	private String funcion;
	private int d;
	private boolean[] marcados;
	
	//Datos del Algoritmo
	private List<Individuo<T>> poblacion;
	private double[] fitness;
	private Individuo<T> elMejor;

	//Datos Grafica
	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	private int posDatos;
	
	
	public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones, double probCruce, double probMutacion, String precision, 
						 String metodoSeleccion, String metodoCruce, double probElitismo, boolean[] marcados, String metodoMutacion, int d) {
		
		//Inicializacion de parametros
		this.tamPoblacion =  tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.cruce = iniciarCruce(metodoCruce, probCruce);
		this.mutacion = iniciarMutacion(metodoMutacion,probMutacion);
		this.precision = precision;
		this.seleccion = iniciarSeleccion(metodoSeleccion);
		this.marcados= marcados;
		this.probElitismo = new Elitismo<T>(marcados[5],probElitismo,tamPoblacion);
		this.d = d;

		//
		this.poblacion = new ArrayList<Individuo<T>>();
		this.fitness = new double[tamPoblacion];
		this.mejores = new double[maxGeneraciones];
		this.absolutos = new double[maxGeneraciones];
		this.media = new double[maxGeneraciones];
		
	}

	public void run() {
		
		iniciarPoblacion();
		
		for(int i = 1; i < maxGeneraciones; i++) {

			probElitismo.extraer(poblacion);
			poblacion = seleccion.seleccionar(poblacion,fitness);
			if(marcados[3]) {
				poblacion = cruce.cruzar(poblacion);
			}
			if(marcados[4]) {
				///mutacion.mutar(poblacion);
			}
			probElitismo.incorporar(poblacion);
			calculos();
		}

	}

	private void iniciarPoblacion() {
		
		double sumFitness = 0;
		
		poblacion = new ArrayList<Individuo<T>>(tamPoblacion);
		Individuo<T> max = crearIndividuo();
		poblacion.add(max);
		for(int i = 1; i < tamPoblacion; i++) {
			Individuo<T> nuevo = crearIndividuo();
			poblacion.add(nuevo);
			fitness[i] = poblacion.get(i).getFitness();
			sumFitness+=fitness[i];
			if(max.compareTo(nuevo) == -1) {
				max = crear(nuevo);
			}
		}

		elMejor = max;
		posDatos = 0;
		mejores[0] = elMejor.getFitness();
		absolutos[0] = elMejor.getFitness();
		media[0] = sumFitness/tamPoblacion;
		posDatos++;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Individuo<T> crearIndividuo() {
		return new Viajero(false,d,precision);
	}	
	
	private AlgoritmoSeleccion iniciarSeleccion(String metodoSeleccion) {

		switch(metodoSeleccion) {
		case "Estocástico Universal":
			return new EstocasticoUni(funcion);
		case "Restos":
			return new Restos(funcion);
		case "Ruleta":
			return new Ruleta(funcion);
		case "Torneo Determinista":
			return new TorneoDet(funcion);
		case "Torneo Probabilístico":
			return new TorneoProb(funcion);
		case "Truncamiento":
			return new Truncamiento(funcion);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Mutacion<T> iniciarMutacion(String metodoMutacion, double probMutacion) {

		switch(metodoMutacion) {
		case "Heurística":
			return new Heuristica(probMutacion);
		case "Inserción":
			return new Insercion(probMutacion);
		case "Intercambio":
			return new Intercambio(probMutacion);
		case "Inversión":
			return new Inversion(probMutacion);
		case "Nuestra Mutación":
			return new NuestraMutacion(probMutacion);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AlgoritmosCruce<T> iniciarCruce(String metodoCruce, double probCruce) {

		/*switch(metodoCruce) {
		case "Aritmético":
			return new Aritmetico(funcion,probCruce);
		case "BLX-alfa":
			return new BLXalfa(funcion,probCruce);
		case "Monopunto":
			return new Monopunto<T>(funcion,probCruce);
		case "Uniforme":
			return new Uniforme<T>(funcion,probCruce);
		}
		*/
		return null;
	}
	
	public void calculos() {
		
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness();
			double sumFitness = fitness[0];
			Individuo<T> max = poblacion.get(0);
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
				sumFitness += fitness[j];
				if(max.compareTo(poblacion.get(j)) == -1) {
					max = crear(poblacion.get(j));
				}
			}
			media[posDatos] = sumFitness/tamPoblacion;
			mejores[posDatos] = max.getFitness();
			mejor(max);
			absolutos[posDatos] = elMejor.getFitness();
			posDatos++;
		}
	}
	
	public void mejor(Individuo<T> obj) {
		if(obj.compareTo(elMejor) == 1) {
			elMejor = crear(obj);
		}
	}

	public List<double[]> datos() {
		
		List<double[]> sol = new ArrayList<double[]>(3);
		
		sol.add(mejores);
		sol.add(absolutos);
		sol.add(media);
		
		return sol;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public Individuo<T> crear(Individuo<T> obj){
		Individuo<T> nuevo = new Viajero(obj);
		return nuevo;
	}

	public List<Double> obtenerMejor() {
		List<Double> sol = new ArrayList<Double>();
		
		for(int i = 0 ; i<elMejor.getMax().length; i++) {
			sol.add(elMejor.getFenotipo(i));
		}
		
		return sol;
	}
}