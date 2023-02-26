package Main;

import java.awt.Color;
import java.awt.Dimension;

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
	
	public void actualizar(JPanel panel, double[] mejores, double[] absolutos, double[] media) {
		this.mejores = mejores;
		this.absolutos = absolutos;
		this.media = media;
		plot.setPreferredSize(new Dimension(500,500));
		plot.addLegend("SOUTH");
		initGUI(panel);
	}
	
	public void initGUI(JPanel panel) {

		plot.removeAllPlots();
		plot.addLinePlot("absolutos", Color.blue, absolutos);
		plot.addLinePlot("mejores", Color.red, mejores);
		plot.addLinePlot("media", Color.green, media);
		
		panel.add(plot);
	}
}