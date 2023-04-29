package bloating;

public class PoliAndMcPhee {

	private double _k;
	
	public PoliAndMcPhee() {
		_k=0;
	}

	public double get_k() {	return _k;}
	public void set_k(double _k) {this._k = _k;}

	public void updateK(int n, int[] x, double[] y) {
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
        System.out.println(_k+ "    " + meanX);
	}
}
