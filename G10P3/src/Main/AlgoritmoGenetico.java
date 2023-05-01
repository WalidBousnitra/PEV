package Main;

import java.util.ArrayList;
import java.util.List;
import Individuo.Formula;
import Individuo.Individuo;
import bloating.Bloating;
import cruces.Intercambio;
import elitismo.Elitismo;
import mutaciones.*;
import selecciones.*;

public class AlgoritmoGenetico<T> {
	
	//Parametros	
	private int tamPoblacion;
	private int min,max;
	private String metodoInicializacion;
	private int maxGeneraciones;
	private boolean[] marcados; //boton de activar GUIs
	private Elitismo<Integer> probElitismo;
	private Intercambio<Integer> cruce;
	private AlgoritmoSeleccion seleccion;

	private AlgoritmosMutacion<Integer> mutacion;
	
	//Datos del Algoritmo
	private List<Individuo<Integer>> poblacion;
	private double[] fitness;
	private int[] tams;
	private Individuo<Integer> elMejor;
	private Bloating k;

	//Datos de la Grafica
	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	private int posDatos;
	
	
	public AlgoritmoGenetico(int tamPoblacion,
							int min, int max,
							int maxGeneraciones,
							boolean[] marcados, 
							double probElitismo,
							String metodoInicializacion, double probCruce, 
							String metodoSeleccion,
							String metodoMutacion, double probMutacion,
							boolean bloatActivado) {
		
		//Inicializacion de parametros
		this.tamPoblacion =  tamPoblacion;
		this.min = min;
		this.max = max;
		this.metodoInicializacion = metodoInicializacion;
		this.maxGeneraciones = maxGeneraciones;
		this.marcados= marcados;
		this.probElitismo = new Elitismo<Integer>(marcados[2],probElitismo*tamPoblacion,tamPoblacion);
		//Inicializacion de los distintos metodos cruce/seleccion/mutacion
		this.k = new Bloating(bloatActivado);
		this.cruce = new Intercambio<Integer>(probCruce,k);
		this.seleccion = iniciarSeleccion(metodoSeleccion);
		this.mutacion = iniciarMutacion(metodoMutacion,probMutacion);

		//Inicializacion de datos del algoritmo y grafica
		this.poblacion = new ArrayList<Individuo<Integer>>();
		this.fitness = new double[tamPoblacion];
		this.tams = new int[tamPoblacion];
		this.mejores = new double[maxGeneraciones];
		this.absolutos = new double[maxGeneraciones];
		this.media = new double[maxGeneraciones];		
	}

	public void run() {
		
		//Poblacion inicial
		if(metodoInicializacion == "Ramped and Half") {
			iniciarPoblacion2(min,max);
		}
		else {
			iniciarPoblacion(min,max,metodoInicializacion);
		}
		
		k.PoliAndMcPhee(tamPoblacion, tams,fitness);
		
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
			
			calculos2();
			
			//recalcular ponalizacion
			k.PoliAndMcPhee(tamPoblacion, tams,fitness);
			
			//reevaluacion
			calculos();
		}

	}

	private void calculos2() {
		//Reevaluacion de individuos / identificacion del mejor de la generacion
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness2();
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness2();
			}
		}
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private void iniciarPoblacion(int minP, int maxP, String tipo) {
		//Sumatorio de fitness para la media
		double sumFitness = 0;
		
		//Inicializacion de la poblacion / identificacion mejor individuo
		poblacion = new ArrayList<Individuo<Integer>>(tamPoblacion);
		Individuo<Integer> max = new Formula(k,minP,maxP,tipo);
		poblacion.add(max);
		fitness[0] = poblacion.get(0).getFitness();
		tams[0] = poblacion.get(0).getCromosoma().getN();
		for(int i = 1; i < tamPoblacion; i++) {
			Individuo<Integer> nuevo = new Formula(k,minP,maxP,tipo);
			poblacion.add(nuevo);
			fitness[i] = poblacion.get(i).getFitness();
			tams[i] = poblacion.get(i).getCromosoma().getN();
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
	

	@SuppressWarnings({"unchecked","rawtypes"})
	private void iniciarPoblacion2(int minP, int maxP) {
		//Sumatorio de fitness para la media
		double sumFitness = 0;
		int D = maxP-1;
		//Inicializacion de la poblacion / identificacion mejor individuo
		poblacion = new ArrayList<Individuo<Integer>>(tamPoblacion);
		Individuo<Integer> max = new Formula(k,2,2,"Completa");
		poblacion.add(max);
		fitness[0] = poblacion.get(0).getFitness();
		tams[0] = poblacion.get(0).getCromosoma().getN();
		int tamGrupo = (tamPoblacion/D);
		int  r = 1;
		for(int i = 2; i<=maxP; i++) {
			for(int j = 0; j < tamGrupo; j++) {
				if(!(j==0 && i==2)) {
					Individuo<Integer> nuevo = null;
					if(j <= tamGrupo/2) {
						nuevo = new Formula(k,minP,i,"Completa");
					}
					else{
						nuevo = new Formula(k,minP,i,"Creciente");
					}
					r++;
					poblacion.add(nuevo);
					fitness[poblacion.size()-1] = poblacion.get(poblacion.size()-1).getFitness();
					tams[poblacion.size()-1] = poblacion.get(poblacion.size()-1).getCromosoma().getN();
					sumFitness+=fitness[poblacion.size()-1];
					if(max.compareTo(nuevo) == -1) {
						max = new Formula(nuevo);
					}
				}
			}
		}
		
		for(int i = r; i< poblacion.size();i++) {
			Individuo<Integer> nuevo = new Formula(k,minP,maxP,"Completa");
			poblacion.add(nuevo);
			fitness[poblacion.size()-1] = poblacion.get(poblacion.size()-1).getFitness();
			tams[poblacion.size()-1] = poblacion.get(poblacion.size()-1).getCromosoma().getN();
			sumFitness+=fitness[poblacion.size()-1];
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
	private AlgoritmosMutacion<Integer> iniciarMutacion(String metodoMutacion, double probMutacion) {

		switch(metodoMutacion) {
		case "Terminal": return new Terminal(probMutacion);
		case "Funcional": return new Funcional(probMutacion);
		case "Permutación": return new Permutacion(probMutacion);
		case "Contracción": return new Contraccion(probMutacion);
		}
		return null;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void calculos() {
		
		//Reevaluacion de individuos / identificacion del mejor de la generacion
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness();
			tams[0] = poblacion.get(0).getCromosoma().getN();
			double sumFitness = fitness[0];
			Individuo<Integer> max = poblacion.get(0);
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
				tams[j] = poblacion.get(j).getCromosoma().getN();
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
	
	//Getter para el mejor individuo
	public Individuo<Integer> getElMejor() {	return elMejor;	}
}