package funciones;

import Individuos.Individuo;

public class IndividuoFuncion3 extends Individuo<Boolean>{
	
	private int d;
	
	public IndividuoFuncion3(double valorError){
		super(new int[2],valorError,new double[]{-5,-5},new double[]{5,5});
		d = 2;
	}
	
	@Override
	public double getValor() {
		double[] x = new double[] {this.getFenotipo(0), this.getFenotipo(1)};
		double sum = 0;
		
		for(int i = 0; i < d; ++i)
			sum+=Math.pow(x[i],4)-16*Math.pow(x[i],2)+5*x[i];
		
		return 0.5*sum;
	}
	
	public String getGenotipo(int var) {
		
		String sol = "";
		
		for(int i = getTamGenes()[var]*var ; i < ( getTamGenes()[var]*var+  getTamGenes()[var]); ++i)
			sol+=getCromosoma()[i].toString();
		
		return sol;
	}
}
