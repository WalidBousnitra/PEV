package Main;

import java.util.ArrayList;
import java.util.List;
import Individuos.Individuo;
import cruces.*;
import elitismo.Elitismo;
import funciones.*;
import mutaciones.Mutacion;
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
	
	//Datos del Algoritmo
	private List<Individuo<T>> poblacion;
	private double[] fitness;

	//Datos Grafica
	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	private int posDatos;
	
	
	public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones, double probCruce, double probMutacion, String precision, 
						 String metodoSeleccion, String metodoCruce, double probElitismo, boolean marcar, String funcion, int d) {
		
		//Inicializacion de parametros
		this.tamPoblacion =  tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.cruce = iniciarCruce(metodoCruce, probCruce);
		this.mutacion = new Mutacion(probMutacion);
		this.precision = precision;
		this.seleccion = iniciarSeleccion(metodoSeleccion);
		this.probElitismo = new Elitismo<T>(marcar,probElitismo,tamPoblacion);
		this.funcion = funcion;
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
			cruce.cruzar(poblacion);
			poblacion = mutacion.mutar(poblacion);
			probElitismo.incorporar(poblacion);
			calculos();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarPoblacion() {
		
		double sumFitness = 0;
		double max = Double.MIN_VALUE;
		
		switch(funcion) {
		case "Función1(calibracion y prueba)":
			poblacion = new ArrayList<Individuo<T>>(tamPoblacion);
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion1(precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Función2(GrieWank)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion2(precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Función3(Styblinski-tang)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion3(precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Función4a(Michalewicz)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion4a(d,precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Función4b(Michalewicz)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion4b(d,precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;	
		}
		posDatos = 0;
		mejores[0] = max;
		absolutos[0] = max;
		media[0] = sumFitness/tamPoblacion;
		posDatos++;
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
	private AlgoritmosCruce<T> iniciarCruce(String metodoCruce, double probCruce) {

		switch(metodoCruce) {
		case "Aritmético":
			return new Aritmetico(probCruce);
		case "BLX-alfa":
			return new BLXalfa(probCruce);
		case "Monopunto":
			return new Monopunto<T>(probCruce);
		case "Uniforme":
			return new Uniforme<T>(probCruce);
		}
		
		return null;
	}
	
	public void calculos() {
		
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness();
			double sumFitness = fitness[0];
			double max = fitness[0];
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
				sumFitness += fitness[j];
				if(max < fitness[j])
					max = fitness[j];
			}
			media[posDatos] = sumFitness/tamPoblacion;
			mejores[posDatos] = max;
			if(max >= absolutos[posDatos-1])
				absolutos[posDatos] = max;
			else
				absolutos[posDatos] = absolutos[posDatos-1]; 
			posDatos++;
		}
	}

	public List<double[]> datos() {
		
		List<double[]> sol = new ArrayList<double[]>(3);
		
		sol.add(mejores);
		sol.add(absolutos);
		sol.add(media);
		
		return sol;
	}
}