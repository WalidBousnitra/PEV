package Main;

import java.util.ArrayList;
import java.util.List;
import Individuos.Individuo;
import cruces.*;
import elitismo.Elitismo;
import funciones.*;
import mutaciones.*;
import selecciones.*;

public class AlgoritmoGenetico<T> {
	
	//Parametros	
	private int tamPoblacion;
	private int maxGeneraciones;
	private boolean[] marcados; //boton de activar GUIs
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
		this.probElitismo = new Elitismo<T>(marcados[2],probElitismo*tamPoblacion,tamPoblacion);
		//Inicializacion de los distintos metodos cruce/seleccion/mutacion
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
			
			//cruce si esta activado
			if(marcados[0])
				poblacion = cruce.cruzar(poblacion);
			
			//mutacion si esta activado
			if(marcados[1]) {
				mutacion.mutar(poblacion);
				//actualizar numero de generacines para las mutaciones no estacionarias
				mutacion.actGen();
			}
			
			//insercion elite
			probElitismo.incorporar(poblacion);
			
			//reevaluacion
			calculos();
		}

	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private void iniciarPoblacion() {
		
		//Sumatorio de fitness para la media
		double sumFitness = 0;
		
		//Inicializacion de la poblacion / identificacion mejor individuo
		poblacion = new ArrayList<Individuo<T>>(tamPoblacion);
		Individuo<T> max = new Formula();
		poblacion.add(max);
		fitness[0] = poblacion.get(0).getFitness();
		for(int i = 1; i < tamPoblacion; i++) {
			Individuo<T> nuevo = new Formula();
			poblacion.add(nuevo);
			fitness[i] = poblacion.get(i).getFitness();
			sumFitness+=fitness[i];
			if(max.compareTo(nuevo) == -1) {
				max = new Formula(nuevo);
			}
		}

		elMejor = max;
		posDatos = 0;
		mejores[0] = elMejor.getFitness();
		absolutos[0] = elMejor.getFitness();
		media[0] = sumFitness/tamPoblacion;
		posDatos++;
	}
	
	//Funcion de seleccion de cruce
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AlgoritmosCruce<T> iniciarCruce(String metodoCruce, double probCruce) {

		switch(metodoCruce) {
		case "CO": return new CO(probCruce);
		case "CX": return new CX(probCruce);
		case "ERX": return new ERX(probCruce);
		case "Nuestro Cruce": return new NuestroCruce(probCruce);
		case "OX": return new OX(probCruce);
		case "OXPP": return new OXPP(probCruce);
		case "OXOP": return new OXOP(probCruce);
		case "PMX": return new PMX(probCruce);
		}
		return null;
	}
	
	//Funcion de seleccion de seleccion
	private AlgoritmoSeleccion iniciarSeleccion(String metodoSeleccion) {

		switch(metodoSeleccion) {
		case "Estocástico Universal": return new EstocasticoUni();
		case "Restos": return new Restos();
		case "Ranking": return new Ranking();
		case "Ruleta": return new Ruleta();
		case "Torneo Determinista": return new TorneoDet();
		case "Torneo Probabilístico": return new TorneoProb();
		case "Truncamiento": return new Truncamiento();
		}
		return null;
	}
	
	//Funcion de seleccion de mutacion
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AlgoritmosMutacion<T> iniciarMutacion(String metodoMutacion, double probMutacion) {

		switch(metodoMutacion) {
		case "Heurística": return new Heuristica(probMutacion);
		case "Inserción": return new Insercion(probMutacion);
		case "Intercambio": return new Intercambio(probMutacion);
		case "Inversión": return new Inversion(probMutacion);
		case "Nuestra Mutación": return new NuestraMutacion(probMutacion);
		}
		return null;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void calculos() {
		
		//Reevaluacion de individuos / identificacion del mejor de la generacion
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness();
			double sumFitness = fitness[0];
			Individuo<T> max = poblacion.get(0);
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
				sumFitness += fitness[j];
				if(max.compareTo(poblacion.get(j)) == -1) {
					max = new Formula(poblacion.get(j));
				}
			}
			
			//Añadir datos de la generacion a los datos de la grafica
			media[posDatos] = sumFitness/tamPoblacion;
			mejores[posDatos] = max.getFitness();
			if(max.compareTo(elMejor) == 1) {
				elMejor = new Formula(max);
			}
			absolutos[posDatos] = elMejor.getFitness();
			posDatos++;
		}
	}

	//Funcion para obtener los datos finales para la grafica
	public List<double[]> datos() {
		
		List<double[]> sol = new ArrayList<double[]>(3);
		sol.add(mejores);
		sol.add(absolutos);
		sol.add(media);
		
		return sol;
	}

	public Individuo<T> getElMejor() {	return elMejor;	}
	
	//Funcion para obtener las ciudades del mejor individuo desde la grafica
	public List<T> obtenerMejor() {
		List<T> sol = new ArrayList<T>();
		
		for(int i = 0 ; i<elMejor.getCromosoma().getN(); i++) {
			//sol.add(elMejor.getCromosoma().get(i));
		}
		
		return sol;
	}
}