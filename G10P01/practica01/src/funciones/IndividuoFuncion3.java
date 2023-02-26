package funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class IndividuoFuncion3<T> extends Individuo<Boolean>{
	
	private int d;
	
	public IndividuoFuncion3(double valorError){
		super(new int[2],valorError,new double[]{-5,-5},new double[]{5,5});
		d = 2;
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
		double sum = 0;
		
		for(int i = 0; i < d; ++i)
			sum+=Math.pow(x[i],4)-16*Math.pow(x[i],2)+5*x[i];
		
		return 0.5*sum;
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
}
