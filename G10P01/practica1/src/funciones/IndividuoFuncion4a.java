package funciones;

import java.util.Random;

import Individuos.Individuo;

public class IndividuoFuncion4a extends Individuo<Boolean>{
	
	private Boolean[] cromosoma;
	private int tamGen;
	private double min, max;
	private int tamTotal;
	private int m, d;
	private double valorError;
	private Random rand;
	
	public IndividuoFuncion4a(){
		//d;
		this.min = 0;
		this.max = Math.PI;
		this.tamGen = this.tamGen(this.valorError, min, max);
		int tamTotal = tamGen*d;
		m = 10;
		this.cromosoma = new Boolean[tamTotal];
		for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	@Override
	public double getValor() {
		double[] x = new double[d];
		
		for (int i= 0; i<d; ++i) {
			x[i] = this.getFenotipo(i);
		}
		
		double sum = 0;
		
		for(int i = 0; i < d; ++i)
			sum+=Math.sin(x[i])*Math.pow(Math.sin((i+1)*Math.pow(x[i],2) /Math.PI),2*m);
		
		return -sum;
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
