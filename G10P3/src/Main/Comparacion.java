package Main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import org.math.plot.*;

public class Comparacion<T> extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Mejor individuo g(x)
	private double[] gx;
	//Mejor error
	private double error;
	//Formula gx;
	private JFrame father;
	//Valores f(x)
	private double[] fx = {	1.0000, 0.9615, 0.9262, 0.8937, 0.8641, 0.8371, 0.8126, 0.7905, 0.7707, 0.7531, 
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
	private Plot2DPanel plot = new Plot2DPanel();
	
	//Valores del eje x
	private double[] x = {	-1.00, -0.98, -0.96, -0.94, -0.91, -0.89, -0.87, -0.85, -0.83, -0.81, 
							-0.79, -0.77, -0.75, -0.73, -0.71, -0.69, -0.67, -0.65, -0.63, -0.61, 
							-0.59, -0.57, -0.55, -0.53, -0.51, -0.49, -0.47, -0.45, -0.43, -0.41, 
							-0.39, -0.37, -0.35, -0.33, -0.31, -0.29, -0.27, -0.25, -0.23, -0.21, 
							-0.19, -0.17, -0.15, -0.13, -0.11, -0.09, -0.07, -0.05, -0.03, -0.01, 
							0.00, 0.02, 0.04, 0.06, 0.08, 0.10, 0.12, 0.14, 0.16, 0.18, 
							0.20, 0.22, 0.24, 0.26, 0.28, 0.30, 0.32, 0.34, 0.36, 0.38, 
							0.40, 0.42, 0.44, 0.46, 0.48, 0.50, 0.52, 0.54, 0.56, 0.58, 
							0.60, 0.62, 0.64, 0.66, 0.68, 0.70, 0.72, 0.74, 0.76, 0.78, 
							0.80, 0.82, 0.84, 0.86, 0.88, 0.90, 0.92, 0.94, 0.96, 0.98,
							1.00 };
	
	public void actualizar(JFrame father,double[] gx, double error) {
		this.father = father;
		this.gx = gx;
		this.error = error;
		setPreferredSize(new Dimension(400,400));
		plot.setPreferredSize(new Dimension(400,400));
		plot.addLegend("SOUTH");
		plot.setAxisLabels("x","y");
		initGUI();
	}
	
	public void initGUI() {
		
		//Eliminar generacion anterior
		plot.removeAllPlots();
		plot.addLinePlot("f(x)", x, fx);
		plot.addLinePlot("g(x)", x, gx);
		plot.addLinePlot("Error = "+ error, Color.red, new double[] {0});
		add(plot);
		pack();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocation(father.getX()+800, father.getY());
		setVisible(true);
		//setLocationRelativeTo(null);
	}
}