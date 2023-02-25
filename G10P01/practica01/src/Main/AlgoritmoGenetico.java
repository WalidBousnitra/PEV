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
	private AlgoritmosCruce cruce;

	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	private int posDatos;
	
	
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
		this.cruce = iniciarCruce();
		this.fitness = new double[tamPoblacion];
		this.mejores = new double[maxGeneraciones];
		this.absolutos = new double[maxGeneraciones];
		this.media = new double[maxGeneraciones];
		this.probElitismo = probElitismo;
		
	}

	public void run() {
		
		iniciarPoblacion();
		
		for(int i = 0; i < maxGeneraciones; i++) {

			
			poblacion = seleccion.seleccionar(poblacion,fitness);

			poblacion = cruce.cruzar(poblacion);

			poblacion = mutacion.mutar(poblacion);
			
			calculos();
		}

	}

	private void iniciarPoblacion() {
		
		double sumFitness = 0;
		double max = Double.MIN_VALUE;
		
		switch(funcion) {
		case "Funcion1(calibracion y prueba)":
			poblacion = new ArrayList<Individuo<Boolean>>(tamPoblacion);
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion1(precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Funcion2(GrieWank)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion2(precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Funcion3(Styblinski-tang)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion3(precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		case "Funcion4a(Michalewicz)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion4a(d,precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
				if(max<fitness[i])
					max = fitness[i];
			}
			break;
		/*case "Funcion4b(Michalewicz)":
			for(int i = 0; i < tamPoblacion; i++) {
				poblacion.add(new IndividuoFuncion4b(d,precision));
				fitness[i] = poblacion.get(i).getFitness();
				sumFitness+=fitness[i];
								if(max<fitness[i])
					max = fitness[i];
			}
			break;*/	
		}
		posDatos = 0;
		mejores[0] = max;
		absolutos[0] = max;
		media[0] = sumFitness/tamPoblacion;
		posDatos++;
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
	
	private AlgoritmosCruce iniciarCruce() {

		switch(metodoCruce) {
		case "Aritmetico":
			return new Aritmetico(probCruce);
		case "BLX-alfa":
			return new BLXalfa(probCruce);
		case "Monopunto":
			return new Monopunto(probCruce);
		case "Uniforme":
			return new Uniforme(probCruce);
		}
		
		return null;
	}
	
	public void calculos() {
		
		if(posDatos<maxGeneraciones) {
			fitness[0] = poblacion.get(0).getFitness();
			double sumFitness = fitness[0];
			double max = Double.MIN_VALUE;
			for(int j = 1; j < tamPoblacion; j++) {
				fitness[j] = poblacion.get(j).getFitness();
				sumFitness += fitness[j];
				if(max < fitness[j])
					max = fitness[j];
			}
			media[posDatos] = sumFitness/tamPoblacion;
			mejores[posDatos] = max;
			if(max > absolutos[posDatos-1])
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