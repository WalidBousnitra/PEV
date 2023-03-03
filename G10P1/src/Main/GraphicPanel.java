package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.*;
import org.math.plot.*;

public class GraphicPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	Plot2DPanel plot = new Plot2DPanel();
	private List<Double> mejor;
	
	
	public void actualizar(List<Double> mejor, JPanel panel, double[] mejores, double[] absolutos, double[] media) {
		this.mejores = mejores;
		this.mejor = mejor;
		this.absolutos = absolutos;
		this.media = media;
		plot.setPreferredSize(new Dimension(749,500));
		plot.addLegend("SOUTH");
		plot.setAxisLabels("Generaciones","Evaluación");
		initGUI(panel);
	}
	
	public void initGUI(JPanel panel) {

		plot.removeAllPlots();
		plot.addLinePlot("Mejor absoluto", Color.blue, absolutos);
		plot.addLinePlot("Mejor de la generación", Color.red, mejores);
		plot.addLinePlot("Media de la generación", Color.green, media);
		System.out.println(absolutos[absolutos.length-1]);
		
		
		panel.add(plot);
		
		double maximo = absolutos[absolutos.length-1];
		int x = 0;
		int i = absolutos.length-1;
		while(absolutos[i] == maximo) {
			x = i;
			i--;
		}
		
		String variables = "";
		
		for(int j = 1; j<= mejor.size();j++) {
			variables += "x" + j + "= " +mejor.get(j-1)+ " ";
		}
		
		JOptionPane.showMessageDialog(this,
			    "El valor optimo de encuentra en la generacion " + x + " con valor "+ absolutos[i]+ " con variables " + variables,
			    "Datos finales",
			    JOptionPane.PLAIN_MESSAGE);
	}
}