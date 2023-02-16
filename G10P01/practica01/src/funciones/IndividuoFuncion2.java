package funciones;

import Individuos.Individuo;

public class IndividuoFuncion2 extends Individuo<Boolean>{
	
	private int d;
	
	public IndividuoFuncion2(double valorError){
		super(new int[2],valorError,new double[]{-600,-600},new double[]{600,600});
		d = 2;
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
	
	public String getGenotipo(int var) {
		
		String sol = "";
		
		for(int i = getTamGenes()[var]*var ; i < (getTamGenes()[var]*var+ getTamGenes()[var]); ++i)
			sol+=getCromosoma()[i].toString();
		
		return sol;
	}
}
