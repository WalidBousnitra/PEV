package funciones;

import Individuos.Individuo;

public class IndividuoFuncion4a extends Individuo<Boolean>{
	
	private int m;
	private int d;
	
	public IndividuoFuncion4a(int d, double valorError){
		super(new int[d],valorError,rellena(d,0),rellena(d,Math.PI));
		this.d = d;
		m = 10;
	}
	
	private static double[] rellena(int d, double valor) {
		double[] sol = new double[d];
		
		for (int i = 0; i<d;i++) {
			sol[i] = valor;
		}
		
		return sol;
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
	
	public String getGenotipo(int var) {
		
		String sol = "";
		
		for(int i = getTamGenes()[var]*var ; i < (getTamGenes()[var]*var+ getTamGenes()[var]); ++i)
			sol+=getCromosoma()[i].toString();
		
		return sol;
	}
}
