package funciones;

import Individuos.Individuo;

public class IndividuoFuncion1 extends Individuo<Boolean>{
	
	public IndividuoFuncion1(double valorError){
		super(new int[2],valorError,new double[]{12.100,5.800}
						,new double[]{-3.000,4.100});
	}
	
	@Override
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}
	
	public String getGenotipo(int var) {
		
		String sol = "";
		int sum = 0;
		
		for( int i = 0; i < var ; ++i)
			sum+=getTamGenes()[i];
		
		for(int i = sum ; i< sum + getTamGenes()[var]; ++i)
			sol+=getCromosoma()[i].toString();
		
		return sol;
	}
}
