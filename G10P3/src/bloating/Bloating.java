package bloating;

public class Bloating {

	private double _k;
	private boolean _activado;
	
	public Bloating( boolean activado) {
		_k=0;
		_activado= activado;
	}

	public double get_k() {	return _k;}
	public boolean is_activado() {return _activado;}
	public void set_k(double _k) {this._k = _k;}

	public void PoliAndMcPhee(int n, int[] x, double[] y) {
		if(_activado) {
			double meanX = 0.0;
	        double meanY = 0.0;
	        for (int i = 0; i < n; i++) {
	            meanX += x[i];
	            meanY += y[i];
	        }
	        meanX /= n;
	        meanY /= n;
	
	        // Calcular la covarianza
	        double cov = 0.0;
	        for (int i = 0; i < n; i++) {
	            cov += (x[i] - meanX) * (y[i] - meanY);
	        }
	        cov /= n - 1;
	        
	        // Calcula la varianza
	        double var = 0.0;
	        for (double xi : x) {
	            var += Math.pow(xi - meanX, 2);
	        }
	        var /= (x.length - 1);
	        if(cov==0 || var == 0) {
	        	_k=0;
	        }
	        else
	        	_k =  cov/var;
		}
	}
}
