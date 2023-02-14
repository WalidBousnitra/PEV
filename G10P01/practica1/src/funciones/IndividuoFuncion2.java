package funciones;

import java.util.Random;

import Individuos.Individuo;

public class IndividuoFuncion2 extends Individuo<Boolean>{
	
	private Boolean[] cromosoma;
	private int tamGen;
	private double min, max;
	private int tamTotal;
	private int d;
	private double valorError;
	private Random rand;
	
	public IndividuoFuncion2(){
		this.min = -600;
		this.max = 600;
		this.tamGen = this.tamGen(this.valorError, min, max);
		int tamTotal = tamGen*2;
		d = 2;
		this.cromosoma = new Boolean[tamTotal];
		for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	@Override
	public double getValor() {
		double[] x = new double[] {this.getFenotipo(0), this.getFenotipo(1)};
		double sum = 0, mult = 1;
		
		for(int i = 0; i < d; ++i) {
			sum+=Math.pow(x[i],2)/4000;
			mult*=Math.cos(x[i])/Math.sqrt(i+1);
		}
		
		return sum - mult + 1;
	}	
	
	public double getFenotipo(int var) {
		 return this.min + Integer.parseInt(getGenotipo(var),2) * ((max - min)/Math.pow(2, tamTotal)-1);
	 }
	
	public String getGenotipo(int var) {
		
		String sol = "";
		
		for(int i = tamGen*var ; i < (tamGen*var+ tamGen); ++i)
			sol+=cromosoma[i].toString();
		
		return sol;
	}
}
