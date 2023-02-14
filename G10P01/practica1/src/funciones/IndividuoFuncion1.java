package funciones;

import java.util.Random;

import Individuos.Individuo;

public class IndividuoFuncion1 extends Individuo<Boolean>{
	
	private Boolean[] cromosoma;
	private int[] tamGenes;
	private double[] min;
	private double[] max;
	private int tamTotal;
	private double valorError;
	private Random rand;
	
	public IndividuoFuncion1(){
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -3.000;
		this.min[1] = 4.100;
		this.max[0] = 12.100;
		this.max[1] = 5.800;
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]);
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]);
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Boolean[tamTotal];
		for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	@Override
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}	
	
	public double getFenotipo(int var) {
		 return this.min[var] + Integer.parseInt(getGenotipo(var),2) * ((max[var] - min[var])/Math.pow(2, tamTotal)-1);
	}
	
	public String getGenotipo(int var) {
		
		String sol = "";
		int sum = 0;
		
		for( int i = 0; i < var ; ++i)
			sum+=tamGenes[i];
		
		for(int i = sum ; i< sum + tamGenes[var]; ++i)
			sol+=cromosoma[i].toString();
		
		return sol;
	}
}
