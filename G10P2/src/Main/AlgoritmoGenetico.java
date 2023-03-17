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
	private boolean[] marcados; //boton de activar GUI
	private Elitismo<T> probElitismo;
	private AlgoritmosCruce<T> cruce;
	private AlgoritmoSeleccion seleccion;
	private AlgoritmosMutacion<T> mutacion;
	
	//Datos del Algoritmo
	private List<Individuo<T>> poblacion;
	private double[] fitness;
	private Individuo<T> elMejor;

	//Datos de la Grafica
	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	private int posDatos;
	
	
	public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones,
							boolean[] marcados, 
							double probElitismo,
							String metodoCruce, double probCruce, 
							String metodoSeleccion,
							String metodoMutacion, double probMutacion) {
		
		//Inicializacion de parametros
		this.tamPoblacion =  tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.marcados= marcados;
		this.probElitismo = new Elitismo<T>(marcados[5],probElitismo,tamPoblacion);
		this.cruce = iniciarCruce(metodoCruce, probCruce);
		this.seleccion = iniciarSeleccion(metodoSeleccion);
		this.mutacion = iniciarMutacion(metodoMutacion,probMutacion);

		//Inicializacion de datos del algoritmo y grafica
		this.poblacion = new ArrayList<Individuo<T>>();
		this.fitness = new double[tamPoblacion];
		this.mejores = new double[maxGeneraciones];
		this.absolutos = new double[maxGeneraciones];
		this.media = new double[maxGeneraciones];
		
	}

	public void run() {
		
		//Poblacion inicial
		iniciarPoblacion();
		
		//n-generaciones
		for(int i = 1; i < maxGeneraciones; i++) {
			
			//extraer elite
			probElitismo.extraer(poblacion);
			
			//seleccion de poblacion
			poblacion = seleccion.seleccionar(poblacion,fitness);
			
			//cruce
			if(marcados[3])
				poblacion = cruce.cruzar(poblacion);
			
			//mutacion
			if(marcados[4])
				mutacion.mutar(poblacion);
			
			//insercion elite
			probElitismo.incorporar(poblacion);
			
			//reevaluacion
			calculos();
		}

	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private void iniciarPoblacion() {
		
		double sumFitness = 0;
		
		poblacion = new ArrayList<Individuo<T>>(tamPoblacion);
		Individuo<T> max = new Viajero();
		poblacion.add(max);
		for(int i = 1; i < tamPoblacion; i++) {
			Individuo<T> nuevo = new Viajero();
			poblacion.add(nuevo);
			fitness[i] = poblacion.get(i).getFitness();
			sumFitness+=fitness[i];
			if(max.compareTo(nuevo) == -1) {
				max = new Viajero(nuevo);
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
	private AlgoritmosCruce<T> iniciarCruce(String metodoCruce, double probCruce) {

		switch(metodoCruce) {
		case "CO":
			return new CO(probCruce);
		case "CX":
			return new CX(probCruce);
		case "ERX":
			return new ERX(probCruce);
		case "Nuestro Cruce":
			return new NuestroCruce(probCruce);
		case "OX":
			return new OX(probCruce);
		case "OXPP":
			return new OXPP(probCruce);
		case "OXOP":
			return new OXOP(probCruce);
		case "PMX":
			return new PMX(probCruce);
		}
		return null;
	}
	
	private AlgoritmoSeleccion iniciarSeleccion(String metodoSeleccion) {

		switch(metodoSeleccion) {
		case "Estocástico Universal":
			return new EstocasticoUni();
		case "Restos":
			return new Restos();
		case "Ruleta":
			return new Ruleta();
		case "Torneo Determinista":
			return new TorneoDet();
		case "Torneo Probabilístico":
			return new TorneoProb();
		case "Truncamiento":
			return new Truncamiento();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AlgoritmosMutacion<T> iniciarMutacion(String metodoMutacion, double probMutacion) {

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
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void calculos() {
		
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness();
			double sumFitness = fitness[0];
			Individuo<T> max = poblacion.get(0);
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
				sumFitness += fitness[j];
				if(max.compareTo(poblacion.get(j)) == -1) {
					max = new Viajero(poblacion.get(j));
				}
			}
			media[posDatos] = sumFitness/tamPoblacion;
			mejores[posDatos] = max.getFitness();
			mejor(max);
			absolutos[posDatos] = elMejor.getFitness();
			posDatos++;
		}
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void mejor(Individuo<T> obj) {
		if(obj.compareTo(elMejor) == 1) {
			elMejor = new Viajero(obj);
		}
	}

	public List<double[]> datos() {
		
		List<double[]> sol = new ArrayList<double[]>(3);
		
		sol.add(mejores);
		sol.add(absolutos);
		sol.add(media);
		
		return sol;
	}

	public List<Double> obtenerMejor() {
		List<Double> sol = new ArrayList<Double>();
		
		for(int i = 0 ; i<elMejor.getCromosoma().size(); i++) {
			sol.add(elMejor.getFenotipo(i));
		}
		
		return sol;
	}
}