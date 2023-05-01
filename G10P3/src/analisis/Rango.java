package analisis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Main.AlgoritmoGenetico;

public class Rango extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos necesarios
	private int max,min,intervalo;
	private String caso;
	private Parametros datos;
	private JSpinner spinner1,spinner2,spinner3;

	public Rango(int min,int max, int intervalo, String caso, Parametros datos) {
		super("Rango del valor");
		this.max = max;
		this.min = min;
		this.intervalo = intervalo;
		this.caso = caso;
		this.datos = datos;
        setLocationRelativeTo(null);
        
        initGUI();
	}

    public void initGUI() {
        
        titulo();
        
        minMax();
        
        boton();        
        
        //ajustes ventana
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void titulo() {
    	
    	// Panel de título
    	JLabel titulo = new JLabel("Rango de Valores");
        titulo.setFont(titulo.getFont().deriveFont(20.0f));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);
    }
    
    public void minMax() {
    	
    	// Panel de valores
        JPanel valores = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        valores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        spinner1 = new JSpinner(new SpinnerNumberModel(min,min,max,intervalo));
        spinner1.setPreferredSize(new Dimension(80, 25));
        spinner2 = new JSpinner(new SpinnerNumberModel(max,min,max,intervalo));
        spinner2.setPreferredSize(new Dimension(80, 25));
        spinner3 = new JSpinner(new SpinnerNumberModel(1,0.1,10,0.1));
        spinner3.setPreferredSize(new Dimension(80, 25));
        valores.add(spinner1);
        valores.add(spinner2);
        valores.add(spinner3);
        add(valores, BorderLayout.CENTER);
    }
    
    public void boton() {
    	
        // Panel de botón
        JPanel boton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				double min = (Double.valueOf((Integer)spinner1.getValue()));
				double max = (Double.valueOf((Integer)spinner2.getValue()));
				double intervalor = (Double.valueOf((Integer)spinner3.getValue()));
				int tam = (int) ((max-min)/intervalo+1);
				double[] mejores = new double[tam];
				double[] ejex = new double[tam];
				int pos = 0;
				switch (caso) {
				case "cruce":
					tam = (int) ((max-min+0.1)/0.1)+1;
					min/=100;
					max/=100;
					for(double i = min ; i <= max; i=i+intervalor/100) {
						AlgoritmoGenetico<Integer> instancia = 
								new AlgoritmoGenetico<Integer>(	datos.getTamPoblacion(),
										datos.getMin(),datos.getMax(),
										datos.getMaxGeneraciones(),
										datos.getMarcados(), 
										datos.getProbElitismo(),
										datos.getMetodoInicializacion(), i, 
										datos.getMetodoSeleccion(),
										datos.getMetodoMutacion(), datos.getProbMutacion(),
										datos.isBloatActivado());
						instancia.run();
						mejores[pos] = instancia.getElMejor().getFitness();
						ejex[pos] = i;
						pos++;
					}
					break;
				case"mutacion":
					tam = (int) ((max-min+0.1)/0.1)+1;
					min/=100;
					max/=100;
					for(double i = min ; i <= max; i=i+ intervalor/100) {
						AlgoritmoGenetico<Integer> instancia = 
								new AlgoritmoGenetico<Integer>(	datos.getTamPoblacion(),
										datos.getMin(),datos.getMax(),
										datos.getMaxGeneraciones(),
										datos.getMarcados(), 
										datos.getProbElitismo(),
										datos.getMetodoInicializacion(), datos.getProbCruce(), 
										datos.getMetodoSeleccion(),
										datos.getMetodoMutacion(), i,
										datos.isBloatActivado());
						instancia.run();
						mejores[pos] = instancia.getElMejor().getFitness();
						ejex[pos] = i;
						pos++;
					}
					break;
				case"poblacion":
					for(int i = (int) min ; i <= max; i=i+(int)intervalor ) {
						AlgoritmoGenetico<Integer> instancia = 
								new AlgoritmoGenetico<Integer>(	i,
										datos.getMin(),datos.getMax(),
										datos.getMaxGeneraciones(),
										datos.getMarcados(), 
										datos.getProbElitismo(),
										datos.getMetodoInicializacion(), datos.getProbCruce(), 
										datos.getMetodoSeleccion(),
										datos.getMetodoMutacion(), datos.getProbMutacion(),
										datos.isBloatActivado());
						instancia.run();
						mejores[pos] = instancia.getElMejor().getFitness();
						ejex[pos] = i;
						pos++;
					}
					break;
				}
				new Grafica(ejex,mejores);
			}
        });
        boton.add(okButton);
        add(boton, BorderLayout.SOUTH);
    }
    
}







