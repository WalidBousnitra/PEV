package analisis;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

public class VentanaAnalisis extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton boton1, boton2, boton3;
	private Parametros datos;
    
    public VentanaAnalisis(Parametros datos) {
    	
        super("Análisis de parámetros");
        this.datos = datos;
        setSize(250, 360);
        setLocationRelativeTo(null);
        
        initGUI();
    }
    
    public void initGUI() {
    	cruce();
        
        mutacion();
        
        tamPoblacion();
        
        setLayout(new FlowLayout());
        setVisible(true);
    }
    
    public void cruce(){
    	boton1 = new JButton("<html><center>Probabilidad de</center><center>Cruce</center></html>");
        boton1.setPreferredSize(new Dimension(200, 100));
        add(boton1);
        boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Rango(20,80,10,"cruce",datos);
			}
        });
    }
    
    public void mutacion() {
    	boton2 = new JButton("<html><center>Probabilidad de</center><center>Mutación</center></html>");
        boton2.setPreferredSize(new Dimension(200, 100));
        add(boton2);
        boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Rango(0,15,10,"mutacion",datos);
			}
        });
    }
    
    public void tamPoblacion() {
    	boton3 = new JButton("<html><center>Tamaño de</center><center>Población</center></html>");
        boton3.setPreferredSize(new Dimension(200, 100));
        add(boton3);
        boton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Rango(10,1000,50,"poblacion",datos);
			}
        });
    }
}

