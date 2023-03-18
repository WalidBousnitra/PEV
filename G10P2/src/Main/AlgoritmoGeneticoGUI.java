package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AlgoritmoGeneticoGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] texto = {"nada","Tamaño de la población:","Número de generaciones:","Probabilidad de Cruce:",
			"Probabiliad de Mutación:","Probabilidad de elitismo:","Dimensión variables:","Método de Selección:","Método de Cruce:","Método de Mutación:"};
	private JSpinner[] spinners;
	private List<JComboBox<String>> comboBox;
    private JButton ejecutarButton;
    private JButton reset;
    private JPanel panelPrincipal;
    private JPanel panelTitulo;
    private List<JCheckBox> marcar = new ArrayList<JCheckBox>(5);
    
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AlgoritmoGeneticoGUI algortimo = new AlgoritmoGeneticoGUI();
	}
    
    public AlgoritmoGeneticoGUI() {
    	
        setTitle("Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setPreferredSize(new Dimension(1200, 600));
        // Panel Titulo
        panelTitulo = new JPanel();
		spinners = new JSpinner[7];
		comboBox = new ArrayList<JComboBox<String>>(11);
    	initGUI();
    }
    
    public void initGUI() {
    	
    	titulo();
    	
    	// Crear panel para los parámetros
    	JPanel paramsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTexto = new GridBagConstraints();
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 0;
        gbcTexto.gridwidth = 1;
        gbcTexto.gridheight = 1;
        gbcTexto.weightx = 0.5;
        gbcTexto.fill = GridBagConstraints.HORIZONTAL;
        gbcTexto.insets = new Insets(5, 5, 5, 5);

        JLabel labelTexto = new JLabel("Parámetros:");

        paramsPanel.add(labelTexto, gbcTexto);
        
        gbcTexto.gridx = 1;
        gbcTexto.weightx = 0.5;

        // Crear spinners para los parámetros

        spinners[1] = new JSpinner(new SpinnerNumberModel(100,2,10000,10));   
        spinners[2] = new JSpinner(new SpinnerNumberModel(100,1,10000,10));
        spinners[3] = new JSpinner(new SpinnerNumberModel(60,0,100,1));
        spinners[4] = new JSpinner(new SpinnerNumberModel(5,0,100,1));
        spinners[5] = new JSpinner(new SpinnerNumberModel(2,0,100,1));
        
        // Crear combo box para los parámetros
        String[] opciones = {"Estocástico Universal","Restos","Ruleta","Torneo Determinista","Torneo Probabilístico","Truncamiento"};
        comboBox.add(0,new JComboBox<>(opciones));
        comboBox.add(1,new JComboBox<>(opciones));
        comboBox.add(2,new JComboBox<>(opciones));
        comboBox.add(3,new JComboBox<>(opciones));
        comboBox.add(4,new JComboBox<>(opciones));
        comboBox.add(5,new JComboBox<>(opciones));
        comboBox.add(6,new JComboBox<>(opciones));
        comboBox.add(7,new JComboBox<>(opciones));
        String[] opciones2 = {"CO","CX","ERX",
          		 "Nuestro Cruce","OX","OXPP","OXOP","PMX"};
        comboBox.add(8,new JComboBox<>(opciones2));
        String[] opciones3 = {"Heurística","Inserción","Intercambio","Inversión","Nuestra Mutación"};
        comboBox.add(9,new JComboBox<>(opciones3));
        
        for (int i = 1; i <= 5; i++) {
            JLabel labelFila = new JLabel(texto[i]);

            gbcTexto.gridy = i;
            paramsPanel.add(labelFila, gbcTexto);

            gbcTexto.gridx = 2;
            gbcTexto.weightx = 0.0;
            if (i == 3 || i==4 || i ==5 ){
                JPanel panelElitismo = new JPanel();
                panelElitismo.setLayout(new GridLayout(0, 2, 0, 0));
                panelElitismo.add(spinners[i]);
                marcar.add(new JCheckBox("ACTIVAR"));
                marcar.get(marcar.size()-1).setSelected(true);
                if(i==5) {
                	marcar.get(marcar.size()-1).setSelected(false);	
                }
                panelElitismo.add(marcar.get(marcar.size()-1));
                paramsPanel.add(panelElitismo, gbcTexto);
            }
            else {
            	paramsPanel.add(spinners[i], gbcTexto);
            }

            gbcTexto.gridx = 1;
            gbcTexto.weightx = 0.2;
        }
        
        for (int i = 7; i <= 9; i++) {
            JLabel labelFila = new JLabel(texto[i]);
            JComboBox<String> comboFila = comboBox.get(i);

            gbcTexto.gridy = i;
            paramsPanel.add(labelFila, gbcTexto);

            gbcTexto.gridx = 2;
            gbcTexto.weightx = 0.0;
            
            paramsPanel.add(comboFila, gbcTexto);

            gbcTexto.gridx = 1;
            gbcTexto.weightx = 0.5;
        }
        
        gbcTexto.gridy = 11;
        gbcTexto.gridx = 0;
        gbcTexto.gridwidth = 3;
        gbcTexto.weightx = 0.0;
        gbcTexto.fill = GridBagConstraints.NONE;
        ejecutarButton = new JButton("EJECUTAR");
        ejecutarButton.setPreferredSize(new Dimension(200, 40));
        paramsPanel.add(ejecutarButton, gbcTexto);
        
        gbcTexto.gridy = 12;
        gbcTexto.gridx = 0;
        gbcTexto.gridwidth = 3;
        gbcTexto.weightx = 0.0;
        gbcTexto.fill = GridBagConstraints.NONE;
        reset = new JButton("<html><center>Valores</center><center>Predeterminados</center></html>");
        reset.setPreferredSize(new Dimension(150, 40));
        paramsPanel.add(reset, gbcTexto);
        
        reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				spinners[1].setValue(100);
		        spinners[1].setValue(100);
		        spinners[2].setValue(100);
		        spinners[3].setValue(60);
		        spinners[4].setValue(5);
		        spinners[5].setValue(2);
		        marcar.get(0).setSelected(true);
		        marcar.get(1).setSelected(true);
		        marcar.get(2).setSelected(false);
			}
        	
        });
        
        // Crear segundo panel vacío para la gráfica
 		JPanel panelVacio = new JPanel();
 		GraphicPanel<Integer> grafico = new GraphicPanel<Integer>();
 		panelVacio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
 		panelVacio.setPreferredSize(new Dimension(700, 600));
 		panelVacio.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0)); 
        
        ejecutarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int tamPoblacion = (Integer)spinners[1].getValue();
				int maxGeneraciones = (Integer) spinners[2].getValue();
				double probCruce = (Double.valueOf((Integer)spinners[3].getValue()))/100;
				double probMutacion = (Double.valueOf((Integer)spinners[4].getValue()))/100;
				String metodoMutacion = (String)comboBox.get(9).getSelectedItem();
				String metodoSeleccion = (String)comboBox.get(7).getSelectedItem();
				String metodoCruce = (String)comboBox.get(8).getSelectedItem();
				double probElitismo = (Double.valueOf((Integer)spinners[5].getValue()))/100;
				boolean[] marcados= new boolean[3];
				marcados[0] = false;
				for(int i = 0; i<3 ;i ++) {
					marcados[i] =  marcar.get(i).isSelected();
				}
				AlgoritmoGenetico<Integer> instancia = 
						new AlgoritmoGenetico<Integer>(tamPoblacion, maxGeneraciones,
								marcados, 
								probElitismo,
								metodoCruce, probCruce, 
								metodoSeleccion,
								metodoMutacion, probMutacion);
						instancia.run();
						List<double[]> datos = instancia.datos();
						List<Integer> mejor = instancia.obtenerMejor();
						grafico.actualizar(mejor,panelVacio,datos.get(0), datos.get(1), datos.get(2));
						panelVacio.revalidate();
			}
         });
        
        panelPrincipal.add(paramsPanel, BorderLayout.WEST);
        panelPrincipal.add(panelVacio, BorderLayout.CENTER);

        // Se agrega el panel de título al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Se agrega el panel principal a la ventana
        add(panelPrincipal);
        pack();
        setVisible(true);
		this.setLocationRelativeTo(null);
    }
    
    public void titulo() {
    	JLabel titleLabel  = new JLabel("Algoritmo Genético");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        panelTitulo.add(titleLabel);
    }

}
