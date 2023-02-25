package Main;

import java.awt.Color;

import javax.swing.*;
import org.math.plot.*;

public class GraphicPanel {
	
	//arrays necesarios
	double[] mejores;
	//mejor de cada generacion (rojo)
	double[] absolutos;
	//mejor absoluto(azul)
	double[] media;
	//media aptitud(verde)
	
	
	public GraphicPanel(double[] mejores, double[] absolutos, double[] media) {
		this.mejores = mejores;
		this.absolutos = absolutos;
		this.media = media;
		initGUI();
	}
	
	public void initGUI() {
		
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
		// define the legend position
		plot.addLegend("SOUTH");
		
		// add a line plot to the PlotPanel
		plot.addLinePlot("absolutos", Color.blue, absolutos);
		// add a line plot to the PlotPanel
		plot.addLinePlot("mejores", Color.red, mejores);
		plot.addLinePlot("media", Color.green, media);
		
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("Datos generados");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}