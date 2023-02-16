package Main;
import Individuos.Individuo;
import funciones.IndividuoFuncion1;
import mutaciones.Mutacion;

class AlgoritmoGenetico {

	private int tamPoblacion;
	private int maxGeneraciones;
	private double probCruce;
	private Mutacion mutacion;
	private double precision;
	private Individuo[] poblacion;
	private double[] fitness;
	private int tamTorneo;
	private Individuo elMejor;
	private int pos_mejor;
	
	public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones, double probCruce, double probMutacion, double precision) {
		this.tamPoblacion =  tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.probCruce = probCruce;
		this.mutacion = new Mutacion(probMutacion);
		this.precision = precision;
	}

	public void run() {
		
		for(int i = 0; i < tamPoblacion; i++) {
			poblacion[i] = new IndividuoFuncion1(probCruce);
			fitness[i] = poblacion[i].getFitness();
		}
		
		for(int i = 0; i < maxGeneraciones; i++) {
			//Seleccion
			//Cruce
			for(int j = 0; i < tamPoblacion; i++) {
				mutacion.mutar(poblacion[j]);
			}
			//evaluar poblaci�n
		}
		//Devolver mejor
	}
}