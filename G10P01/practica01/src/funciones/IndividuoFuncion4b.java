package funciones;

import java.util.Random;
import Individuos.Individuo;

public class IndividuoFuncion4b extends Individuo<Double>{
	
	private int m;
	private int d;
	
	public IndividuoFuncion4b(int d, double valorError){
		super(new int[d],valorError,rellena(d,0),rellena(d,Math.PI));
		this.d = d;
		m = 10;
	}
	
	@Override
	public Double[] iniCromosoma(Random rand) {
		Double[] cromosoma = new Double[getTamTotal()];
		for(int i = 0; i < getTamTotal(); i++) cromosoma[i] = rand.nextDouble(this.getMin()[0],this.getMax()[0]);
		return cromosoma;
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
		return getCromosoma()[var].toString();
	}
	
	public double getFenotipo(int var) {
		 return getCromosoma()[var];
	}

	@Override
	public void mutar(int i) {
		getCromosoma()[i] = getRand().nextDouble(this.getMin()[0],this.getMax()[0]);
	}
}
