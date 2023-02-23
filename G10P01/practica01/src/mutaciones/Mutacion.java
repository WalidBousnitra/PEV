package mutaciones;

import java.util.Random;
import Individuos.Individuo;

public class Mutacion{
	
	private double p;
	private Random rand;
	
	public Mutacion(double probMutacion) {
		p = probMutacion;
		rand = new Random();
	}
	
	public void setP(int p) {this.p = p;}
	
	public void mutar(Individuo<?> individuo) {
		
		for(int i = 0; i < individuo.getTamTotal(); ++i) {
			if(rand.nextDouble()<p)
				individuo.mutar(i);
		}
	}
}