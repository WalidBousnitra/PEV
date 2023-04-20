package funciones;

import Individuos.Individuo;

public class Formula<T> extends Individuo<Integer>{
	
	//Valores f(x)
	private final static double[] fx = {	1.0000, 0.9615, 0.9262, 0.8937, 0.8641, 0.8371, 0.8126, 0.7905, 0.7707, 0.7531, 
											0.7375, 0.7239, 0.7122, 0.7022, 0.6938, 0.6870, 0.6817, 0.6778, 0.6752, 0.6738, 
											0.6736, 0.6744, 0.6763, 0.6791, 0.6829, 0.6875, 0.6928, 0.6990, 0.7058, 0.7134, 
											0.7216, 0.7303, 0.7397, 0.7496, 0.7601, 0.7711, 0.7825, 0.7945, 0.8070, 0.8200, 
											0.8336, 0.8476, 0.8621, 0.8772, 0.8928, 0.9091, 0.9259, 0.9433, 0.9615, 0.9803, 
											1.0000, 1.0204, 1.0416, 1.0638, 1.0869, 1.1111, 1.1363, 1.1627, 1.1903, 1.2192, 
											1.2496, 1.2813, 1.3147, 1.3497, 1.3864, 1.4251, 1.4656, 1.5082, 1.5530, 1.6001, 
											1.6496, 1.7016, 1.7562, 1.8137, 1.8740, 1.9375, 2.0041, 2.0740, 2.1475, 2.2246, 
											2.3056, 2.3904, 2.4795, 2.5728, 2.6706, 2.7731, 2.8803, 2.9926, 3.1101, 3.2331, 
											3.3616, 3.4958, 3.6361, 3.7826, 3.9355, 4.0951, 4.2614, 4.4349, 4.6156, 4.8039, 
											5.0000 };
	private double[] gx;
	private double error;
	
	public Formula(int min, int max, String tipo) {
		super(min, max, tipo);
		gx = new double[101];
	}
	
	// Constructor de copia
	public Formula(Individuo<Integer> obj){
		super(obj);
		gx = new double[101];
	}
	
	//Funcion que calcula la media de error
	@Override
	public double getValor() {
		double sum = 0;
		
		//Distancia recorrida
		int j = 0;
		for(double i = -1.00; i< 1.00; i+=0.02) {
			gx[j] = formato(getCromosoma().calcular(i));
			sum+=Math.pow(gx[j]-fx[j],2);
			j++;
		}
		
		sum  = Math.sqrt(sum);
		error = sum;
		return sum;
	}
	
	//Devuelve un string con la ecuacion construida
	public String formula() {
		return getCromosoma().inOrden();
	}
	
	//Comparador para de individuos
	@Override
	public int compareTo(Individuo<Integer> o) {
		if(o.getFitness()== this.getFitness())
			return 0;
		else if(o.getFitness()< this.getFitness())
			return -1;
		else 
			return 1;
	}

	@Override
	public double[] gx() {
		return gx;
	}

	@Override
	public double getError() {
		return error;
	}
}
