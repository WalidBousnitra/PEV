package funciones;

import java.util.Random;

import Individuos.Individuo;
public class IndividuoFuncion4a extends Individuo<Boolean>{
	
	private int m;
	private int d;
	
	public IndividuoFuncion4a(int d, double valorError){
		super(new int[d],valorError,rellena(d,0),rellena(d,Math.PI));
		this.d = d;
		m = 10;
	}
	
	@Override
	public Boolean[] iniCromosoma(Random rand) {
		Boolean[] cromosoma = new Boolean[getTamTotal()];
		for(int i = 0; i < getTamTotal(); i++) cromosoma[i] = rand.nextBoolean();
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
		
		String sol = "";
		
		for(int i = getTamGenes()[var]*var ; i < (getTamGenes()[var]*var+ getTamGenes()[var]); ++i)
			sol+=getCromosoma()[i].toString();
		
		return sol;
	}

	@Override
	public void mutar(int i) {
		getCromosoma()[i] = !getCromosoma()[i];		
	}
}
