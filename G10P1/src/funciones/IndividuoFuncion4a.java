package funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Individuos.Individuo;
public class IndividuoFuncion4a<T> extends Individuo<Boolean>{
	
	private int m;
	
	public IndividuoFuncion4a(Individuo<Boolean> obj){
		super(obj);
		m=10;
	}
	
	public IndividuoFuncion4a(int d, String valorError){
		super(new int[d],valorError,rellena(d,0),rellena(d,Math.PI),d);
		m = 10;
	}
	
	@Override
	public List<Boolean> iniCromosoma(Random rand) {
		List<Boolean> cromosoma = new ArrayList<Boolean>(getTamTotal());
		for(int i = 0; i < getTamTotal(); i++) cromosoma.add(i, rand.nextBoolean());
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
		double[] x = new double[getD()];
		
		for (int i= 0; i<getD(); ++i) {
			x[i] = this.getFenotipo(i);
		}
		
		double sum = 0;
		
		for(int i = 0; i < getD(); ++i)
			sum+=Math.sin(x[i])*Math.pow(Math.sin((i+1)*Math.pow(x[i],2) /Math.PI),2*m);
		
		return formato(-sum);
	}	
	
	public String getGenotipo(int var) {
		
		String sol = "";
		int sum = 0;
		
		for( int i = 0; i < var ; ++i)
			sum+=getTamGenes()[i];
		
		for(int  i =sum ; i< sum + getTamGenes()[var]; ++i) {
			if(getCromosoma().get(i))
				sol += "1";
			else
				sol+="0";
		}
		return sol;
	}

	@Override
	public void mutar(int i) {
		getCromosoma().set(i,!getCromosoma().get(i));		
	}
	
	@Override
	public int compareTo(Individuo<Boolean> o) {
		if(o.getFitness()== this.getFitness())
			return 0;
		else if(o.getFitness()< this.getFitness())
			return -1;
		else 
			return 1;
	}
}
