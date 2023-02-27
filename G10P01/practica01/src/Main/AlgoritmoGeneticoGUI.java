package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class AlgoritmoGeneticoGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] texto = {"nada","Tamaño de la población:","Número de generaciones:","Probabilidad de Cruce:",
			"Probabiliad de Mutación:","Probabilidad de elitismo:","Dimensión variables:","Precisión:"};
	private JSpinner[] spinners;
	private JTextField spinner5;
    private JButton ejecutarButton;
    private JPanel panelPrincipal;
    private JPanel panelTitulo;
    private JSpinner spinnerFila;
    private JCheckBox marcar = new JCheckBox("Añadir");
    
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AlgoritmoGeneticoGUI algortimo = new AlgoritmoGeneticoGUI();
	}
    
    public AlgoritmoGeneticoGUI() {
    	
        setTitle("Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setPreferredSize(new Dimension(1200, 600));
        // Panel Titulo
        panelTitulo = new JPanel();
		spinners = new JSpinner[7];
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

        JLabel labelTexto = new JLabel("Parámetros necesarios:");

        paramsPanel.add(labelTexto, gbcTexto);
        
        gbcTexto.gridx = 1;
        gbcTexto.weightx = 0.5;

        // Crear spinners para los parámetros

        spinners[1] = new JSpinner(new SpinnerNumberModel(100,2,10000,10));   
        spinners[2] = new JSpinner(new SpinnerNumberModel(100,1,10000,10));
        spinners[3] = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        spinners[4] = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        spinners[5] = new JSpinner(new SpinnerNumberModel(2,0,100,1));
        spinners[6] = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        spinner5 = new JTextField("0.001");
        
        for (int i = 1; i <= 7; i++) {
            JLabel labelFila = new JLabel(texto[i]);
            if (i!=7)
            	spinnerFila = spinners[i];

            gbcTexto.gridy = i;
            paramsPanel.add(labelFila, gbcTexto);

            gbcTexto.gridx = 2;
            gbcTexto.weightx = 0.0;
            if(i==5) {
                JPanel panelElitismo = new JPanel();
                panelElitismo.setLayout(new GridLayout(0, 2, 0, 0));
                panelElitismo.add(spinners[5]);
                panelElitismo.add(marcar);
                paramsPanel.add(panelElitismo, gbcTexto);
            }
            else if(i==7){
         		paramsPanel.add(spinner5,gbcTexto);
            }
            else
            	paramsPanel.add(spinnerFila, gbcTexto);

            gbcTexto.gridx = 1;
            gbcTexto.weightx = 0.5;
        }
        
        for (int i = 1; i <= 3; i++) {
            JLabel labelFila = new JLabel(texto[i]);
            JSpinner spinnerFila = spinners[i];

            gbcTexto.gridy = i;
            paramsPanel.add(labelFila, gbcTexto);

            gbcTexto.gridx = 2;
            gbcTexto.weightx = 0.0;
            paramsPanel.add(spinnerFila, gbcTexto);

            gbcTexto.gridx = 1;
            gbcTexto.weightx = 0.5;
        }
        
        // Crear combo box para los parámetros
        String[] opciones = {"Estocástico Universal","Restos","Ruleta","Torneo Determinista","Torneo Probabilístico","Truncamiento"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setPreferredSize(new Dimension(50, 50));
        String[] opciones2 = {"Monopunto", "Uniforme", "Aritmético","BLX-alfa"};
        JComboBox<String> comboBox2 = new JComboBox<>(opciones2);
        comboBox2.setPreferredSize(new Dimension(50, 50));
        String[] opciones3 = {"Función1(calibracion y prueba)","Función2(GrieWank)","Función3(Styblinski-tang)",
       		 "Función4a(Michalewicz)","Función4b(Michalewicz)"};
        JComboBox<String> comboBox3 = new JComboBox<>(opciones3);
        comboBox3.setPreferredSize(new Dimension(50, 50));
        
        // Añadir elementos al panel de parámetros
 		paramsPanel.add(new JLabel("Método de Selección:"));
 		paramsPanel.add(comboBox);
 		paramsPanel.add(new JLabel("Método de Cruce:"));
 		paramsPanel.add(comboBox2);
 		paramsPanel.add(new JLabel("Función a estudiar:"));
 		paramsPanel.add(comboBox3);
        
        gbcTexto.gridy = 11;
        gbcTexto.gridx = 0;
        gbcTexto.gridwidth = 3;
        gbcTexto.weightx = 0.0;
        gbcTexto.fill = GridBagConstraints.NONE;
        ejecutarButton = new JButton("Ejecutar");
        ejecutarButton.setPreferredSize(new Dimension(200, 40));
        paramsPanel.add(ejecutarButton, gbcTexto);
        
        // Crear segundo panel vacío para la gráfica
 		JPanel panelVacio = new JPanel();
 		GraphicPanel grafico = new GraphicPanel();
 		panelVacio.add(grafico);
 		panelVacio.setPreferredSize(new Dimension(700, 600));
 		panelVacio.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0)); 
        
        ejecutarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int tamPoblacion = (Integer)spinners[1].getValue();
				int maxGeneraciones = (Integer) spinners[2].getValue();
				double probCruce = (Double.valueOf((Integer)spinners[3].getValue()))/100;
				double probMutacion = (Double.valueOf((Integer)spinners[4].getValue()))/100;
				String precision = spinner5.getText();
				String funcion = (String)comboBox3.getSelectedItem();
				String metodoSeleccion = (String)comboBox.getSelectedItem();
				String metodoCruce = (String)comboBox2.getSelectedItem();
				double probElitismo = (Double.valueOf((Integer)spinners[5].getValue()))/100;
				boolean marcar2 = marcar.isSelected();
				int d = (Integer)spinners[6].getValue();
				switch(funcion) {
				case "Funcion4b(Michalewicz)":
					AlgoritmoGenetico<Double> instancia =  new AlgoritmoGenetico<Double>(tamPoblacion, maxGeneraciones, probCruce, probMutacion, precision, 
							metodoSeleccion, metodoCruce, probElitismo, marcar2, funcion, d);
							instancia.run();
							List<double[]> datos = instancia.datos();
							grafico.actualizar(panelVacio,datos.get(0), datos.get(1), datos.get(2));
							panelVacio.revalidate();
					break;

				default:					
					AlgoritmoGenetico<Boolean> instancia2 =  new AlgoritmoGenetico<Boolean>(tamPoblacion, maxGeneraciones, probCruce, probMutacion, precision, 
							metodoSeleccion, metodoCruce, probElitismo, marcar2, funcion, d);
					instancia2.run();
					List<double[]> datos2 = instancia2.datos();
					grafico.actualizar(panelVacio,datos2.get(0), datos2.get(1), datos2.get(2));
					panelVacio.revalidate();
				break;
				}
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
    }
    
    public void titulo() {
    	JLabel titleLabel  = new JLabel("Algoritmo Genético");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        panelTitulo.add(titleLabel);
    }

}
