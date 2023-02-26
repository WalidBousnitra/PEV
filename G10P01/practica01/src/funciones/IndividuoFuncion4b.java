package funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class IndividuoFuncion4b<T> extends Individuo<Double>{
	
	private int m;
	private int d;
	
	public IndividuoFuncion4b(int d, String valorError){
		super(new int[d],valorError,rellena(d,0),rellena(d,Math.PI));
		this.d = d;
		m = 10;
	}
	
	@Override
	public List<Double> iniCromosoma(Random rand) {
		List<Double> cromosoma = new ArrayList<Double>(getTamTotal());
		for(int i = 0; i < getTamTotal(); i++) cromosoma.add(i, rand.nextDouble(this.getMin()[0],this.getMax()[0]));
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
		return getCromosoma().get(var).toString();
	}
	
	public double getFenotipo(int var) {
		 return getCromosoma().get(var);
	}

	@Override
	public void mutar(int i) {
		getCromosoma().set(i, getRand().nextDouble(this.getMin()[0],this.getMax()[0]));
	}
}
