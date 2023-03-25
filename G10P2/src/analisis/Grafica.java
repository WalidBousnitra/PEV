package analisis;

import java.awt.Color;
import javax.swing.*;
import org.math.plot.*;

public class Grafica extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Grafica(double[] ejex, double[] valores) {
		
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
		plot.setAxisLabels("Valores","Aptitud");
		
		// define the legend position
		plot.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		plot.addLinePlot("Aptitudes por valor", ejex, valores);
		
		//Obtener la mejor aptitud y su respectivo valor de parametro
		int pos = 0;
		double min = valores[pos];
		
		for(int i = 1; i< valores.length;i++){
			if(valores[i]<min) {
				pos = i;
				min = valores[i];
			}
		}
		
		String valor ="";
		if(ejex[pos]<1) {
			valor = String.valueOf(ejex[pos]*100)+"%";
		}
		else
			valor = String.valueOf(ejex[pos]);
		
		plot.addLinePlot("Valor óptimo: "+ valor, Color.red, new double[] {0});
		
		setSize(600, 600);
		setContentPane(plot);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

