package funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class IndividuoFuncion2<T> extends Individuo<Boolean>{

	
	public IndividuoFuncion2(Individuo<Boolean> obj){
		super(obj);
	}
	
	public IndividuoFuncion2(String valorError){
		super(new int[2],valorError,new double[]{-600,-600},new double[]{600,600},2);
	}
	
	@Override
	public List<Boolean> iniCromosoma(Random rand) {
		List<Boolean> cromosoma = new ArrayList<Boolean>(getTamTotal());
		for(int i = 0; i < getTamTotal(); i++) cromosoma.add(i, rand.nextBoolean());
		return cromosoma;
	}
	
	@Override
	public double getValor() {
		double[] x = new double[] {this.getFenotipo(0), this.getFenotipo(1)};
		double sum = 0, mult = 1;
		
		for(int i = 0; i < getD(); ++i) {
			sum+=Math.pow(x[i],2)/4000;
			mult*=Math.cos(x[i])/Math.sqrt(i+1);
		}
		
		return formato(sum - mult+ 1);
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
