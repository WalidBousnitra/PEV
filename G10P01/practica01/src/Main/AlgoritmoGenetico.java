package Main;
import java.util.ArrayList;
import java.util.List;

import Individuos.Individuo;
import cruces.*;
import funciones.*;
import mutaciones.Mutacion;
import selecciones.*;

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
	private List<Individuo<Boolean>> poblacion;
	private double[] fitness;
	private AlgoritmoSeleccion seleccion;
	private AlgoritmosCruce<Boolean> cruce;
	private Individuo<Boolean> elMejor;
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
		this.poblacion = new ArrayList<Individuo<Boolean>>();
		this.seleccion = iniciarSeleccion();
		//this.cruce = iniciarCruce();
		this.fitness = new double[tamPoblacion];
		
	}

	public void run() {
		
		iniciarPoablacion();
		
		for(int i = 0; i < maxGeneraciones; i++) {
			seleccion.seleccionar(poblacion,fitness);
				
			for(int j = 0; j < tamPoblacion; j++) {
				//cruce.cruzar
			}
			
			for(int j = 0; j < tamPoblacion; j++) {
				mutacion.mutar(poblacion.get(j));
			}
			for(int j = 0; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
			}
		}
		elMejor = poblacion.get(0);
		pos_mejor = 0;
		for(int i = 1; i < tamPoblacion; i++) {
			if(elMejor.getFitness()<fitness[i]) {
				elMejor = poblacion.get(i);
				pos_mejor = i;
			}
		}
	}

	private void iniciarPoablacion() {
		switch(funcion) {
		case "Funcion1(calibracion y prueba)":
			poblacion = new ArrayList<Individuo<Boolean>>(tamPoblacion);
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion1(precision));
				fitness[i] = poblacion.get(i).getFitness();
			}
			break;
		case "Funcion2(GrieWank)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion2(precision));
				fitness[i] = poblacion.get(i).getFitness();
			}
			break;
		case "Funcion3(Styblinski-tang)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion3(precision));
				fitness[i] = poblacion.get(i).getFitness();
			}
			break;
		case "Funcion4a(Michalewicz)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion4a(d,precision));
				fitness[i] = poblacion.get(i).getFitness();
			}
			break;
		/*case "Funcion4b(Michalewicz)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion4b(d,precision));
				fitness[i] = poblacion.get(i).getFitness();
			}
			break;*/
			
		}
	}
	
	private AlgoritmoSeleccion iniciarSeleccion() {

		switch(metodoSeleccion) {
		case "Estocastico Universal":
			return new EstocasticoUni();
		case "Restos":
			return new Restos();
		case "Ruleta":
			return new Ruleta();
		case "Torneo Determinista":
			return new TorneoDet();
		case "Torneo Probabilistico":
			return new TorneoProb();
		case "Truncamiento":
			return new Truncamiento();
		}
		
		return null;
	}
/*	private AlgoritmosCruce<Boolean> iniciarCruce() {

		switch(metodoCruce) {
		/*case "Aritmetico":
			return new Aritmetico();
		case "BLX-alfa":
			return new BLXalfa();
		case "Monopunto":
			return new Monopunto();
		case "Uniforme":
			return new Uniforme();
		}
		
		return null;
	}*/
}