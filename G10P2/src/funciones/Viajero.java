package funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class Viajero<T> extends Individuo<Double>{
	
	private int m;
	
	public Viajero(Individuo<Double> obj){
		super(obj);
		m =10;
	}
	
	public Viajero(boolean marca, String valorError){
		super(marca, rellena2(2,1),valorError,rellena(2,0),rellena(2,Math.PI),2);
		m = 10;
	}
	
	@Override
	public List<Double> iniCromosoma(Random rand) {
		List<Double> cromosoma = new ArrayList<Double>(getTamTotal());
		for(int i = 0; i < getTamTotal(); i++) cromosoma.add(i, rand.nextDouble(this.getMin()[i],this.getMax()[i]));
		return cromosoma;
	}
	
	private static double[] rellena(int d, double valor) {
		double[] sol = new double[d];
		
		for (int i = 0; i<d;i++) {
			sol[i] = valor;
		}
		
		return sol;
	}
	
	private static int[] rellena2(int d, int valor) {
		int[] sol = new int[d];
		
		for (int i = 0; i<d;i++) {
			sol[i] = valor;
		}
		
		return sol;
	}
	
	@Override
	public double getValor() {
		
		double sum = 0;
		
		for(int i = 0; i < getD(); ++i) {
			double sin = Math.sin((i+1)*Math.pow(getCromosoma().get(i),2)/Math.PI);
			sum+=Math.sin(getCromosoma().get(i))*(Math.pow(sin, 2*m));
		}
		
		return formato(-sum);
	}	
	
	public String getGenotipo(int var) {
		return getCromosoma().get(var).toString();
	}
	
	public double getFenotipo(int var) {
		 return formato(getCromosoma().get(var));
	}

	@Override
	public void mutar(int i) {
		getCromosoma().set(i, getRand().nextDouble(this.getMin()[0],this.getMax()[0]));
	}
	
	@Override
	public int compareTo(Individuo<Double> o) {
		if(o.getFitness()== this.getFitness())
			return 0;
		else if(o.getFitness()< this.getFitness())
			return -1;
		else 
			return 1;
	}
}
