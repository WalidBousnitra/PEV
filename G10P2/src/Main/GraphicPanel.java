package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.*;
import org.math.plot.*;

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
	//Lista que contendra el camino optimo
	private List<T> mejor;
	//Una lista para la salida final
	private String[] ciudades = {"Dummy","Alicante","Almería", "Avila", "Badajoz","Barcelona", "Bilbao", "Burgos","Cáceres","Cádiz","Castellón","Ciudad Real",
									"Córdoba","A Coruña","Cuenca","Gerona","Granada","Guadalajara","Huelva","Huesca","Jaén","León","Lérida","Logroño",
									"Lugo","Madrid","Málaga","Murcia"};
	
	public void actualizar(List<T> mejor, JPanel panel, double[] mejores, double[] absolutos, double[] media) {
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
		
		//trazos
		plot.addLinePlot("Mejor absoluto", Color.blue, absolutos);
		plot.addLinePlot("Mejor de la generación", Color.red, mejores);
		plot.addLinePlot("Media de la generación", Color.green, media);
		//Dummy para la leyenda
		plot.addLinePlot(optimo(), Color.black, new double[] {0});
		
		String variables = "Madrid->";
		
		for(int j = 0; j< mejor.size()/2;j++) {
			variables += ciudades[(int) mejor.get(j)]+ "->";
		}

		plot.addLinePlot(variables, Color.pink, new double[] {0});
		
		variables = "";
		
		for(int j = mejor.size()/2; j< mejor.size();j++) {
			variables += ciudades[(int) mejor.get(j)]+ "->";
		}
		
		variables += "Madrid";
		plot.addLinePlot(variables, Color.pink, new double[] {0});
		
		panel.add(plot);
	}
	
	public String optimo() {
		
		String sol = "";
		
		double maximo = absolutos[absolutos.length-1];
		int x = 0;
		int i = absolutos.length-1;
		while(i>0 && absolutos[i] == maximo) {
			x = i;
			i--;
		}
		
		sol  = "Mejor Individuo:   gen: " + x + "     Recorrido: "+ absolutos[i];
		return sol;
	}
}