package Main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import org.math.plot.*;

import Individuo.Individuo;

public class GraphicPanel<T> extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Datos necesarios del algoritmo
	private double[] mejores;
	private double[] absolutos;
	private double[] media;
	Plot2DPanel plot = new Plot2DPanel();
	//Mejor individuo
	private Individuo<Integer> mejor;
	
	public void actualizar(Individuo<Integer> mejor, JPanel panel, double[] mejores, double[] absolutos, double[] media) {
		this.mejores = mejores;
		this.mejor = mejor;
		this.absolutos = absolutos;
		this.media = media;
		plot.setPreferredSize(new Dimension(799,500));
		plot.addLegend("SOUTH");
		plot.setAxisLabels("Generaciones","Evaluación");
		initGUI(panel);
	}
	
	public void initGUI(JPanel panel) {
		
		//Eliminar generacion anterior
		plot.removeAllPlots();
		plot.removeLegend();
		plot.addLegend("SOUTH");
		//trazos
		plot.addLinePlot("Mejor absoluto", Color.blue, absolutos);
		plot.addLinePlot("Mejor de la generación", Color.red, mejores);
		plot.addLinePlot("Media de la generación", Color.green, media);
		//Dummy para la leyenda
		plot.addLinePlot("Ecuacion del mejor individuo: g(x) = " + mejor.formula(), Color.pink, new double[] {0});
		plot.addLinePlot("Error: " + mejor.getError(), Color.pink, new double[] {0});
		
		panel.add(plot);
	}
}