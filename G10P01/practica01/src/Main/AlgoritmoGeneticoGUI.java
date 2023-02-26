package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
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

	private JSpinner[] spinners;
    private JButton ejecutarButton;
    
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AlgoritmoGeneticoGUI algortimo = new AlgoritmoGeneticoGUI();
	}
    
    public AlgoritmoGeneticoGUI() {
    	
        setTitle("Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		spinners = new JSpinner[7];
    	initGUI();
    }
    
    public void initGUI() {
    	
    	titulo();
    	
    	// Crear panel para los parámetros
        JPanel paramsPanel = new JPanel();
        paramsPanel.setLayout(new GridLayout(11, 2, 10, 10));
        paramsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Crear spinners para los parámetros
        spinners[1] = new JSpinner(new SpinnerNumberModel(100,2,10000,10));
        spinners[1].setPreferredSize(new Dimension(50, 50));
        spinners[2] = new JSpinner(new SpinnerNumberModel(100,1,10000,10));
        spinners[2].setPreferredSize(new Dimension(50, 50));
        spinners[3] = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        spinners[3].setPreferredSize(new Dimension(50, 50));
        spinners[4] = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        JTextField spinner5 = new JTextField("0.001");
        spinners[5] = new JSpinner(new SpinnerNumberModel(2,0,100,1));
        spinners[5].setPreferredSize(new Dimension(50, 50));
        JCheckBox marcar = new JCheckBox("Añadir");
        JPanel panelElitismo = new JPanel();
        panelElitismo.setLayout(new GridLayout(0, 2, 0, 0));
        panelElitismo.add(spinners[5]);
        panelElitismo.add(marcar);
        spinners[6] = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        spinners[6].setPreferredSize(new Dimension(50, 50));
        
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
 		paramsPanel.add(new JLabel("Tamaño de la población:"));
 		paramsPanel.add(spinners[1]);
 		paramsPanel.add(new JLabel("Número de generaciones:"));
 		paramsPanel.add(spinners[2]);
 		paramsPanel.add(new JLabel("Probabilidad de Cruce:"));
 		paramsPanel.add(spinners[3]);
 		paramsPanel.add(new JLabel("Probabiliad de Mutación:"));
 		paramsPanel.add(spinners[4]);
 		paramsPanel.add(new JLabel("Precisión:"));
 		paramsPanel.add(spinner5);
 		paramsPanel.add(new JLabel("Método de Selección:"));
 		paramsPanel.add(comboBox);
 		paramsPanel.add(new JLabel("Método de Cruce:"));
 		paramsPanel.add(comboBox2);
 		paramsPanel.add(new JLabel("Probabilidad de elitismo:"));
 		paramsPanel.add(panelElitismo);
 		paramsPanel.add(new JLabel("Función a estudiar:"));
 		paramsPanel.add(comboBox3);
 		paramsPanel.add(new JLabel("Dimensión variables:"));
 		paramsPanel.add(spinners[6]);
		
		add(paramsPanel, BorderLayout.WEST);
        // Crear segundo panel vacío para la gráfica
		JPanel panelVacio = new JPanel();
		GraphicPanel grafico = new GraphicPanel();
		panelVacio.add(grafico);
		panelVacio.setPreferredSize(new Dimension(600, 600));
		panelVacio.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
		
		add(panelVacio,BorderLayout.EAST);
		
        ejecutarButton = new JButton("Ejecutar");
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
        
        paramsPanel.add(ejecutarButton);
        setVisible(true);
    }
    
    public void titulo() {
    	JLabel titleLabel  = new JLabel("Algoritmo Genético");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        add(titleLabel, BorderLayout.NORTH);
    }
}
