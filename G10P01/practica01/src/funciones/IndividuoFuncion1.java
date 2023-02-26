package funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class IndividuoFuncion1<T> extends Individuo<Boolean>{
	
	public IndividuoFuncion1(String valorError){
		super(new int[2],valorError, new double[]{-3.000,4.100},new double[]{12.100,5.800});
	}
	
	@Override
	public List<Boolean> iniCromosoma(Random rand) {
		List<Boolean> cromosoma = new ArrayList<Boolean>(getTamTotal());
		for(int i = 0; i < getTamTotal(); i++) cromosoma.add(i, rand.nextBoolean());
		return cromosoma;
	}
	
	@Override
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return formato(21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
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
