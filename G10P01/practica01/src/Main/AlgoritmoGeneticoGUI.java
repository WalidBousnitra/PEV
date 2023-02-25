package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
    private JButton ejecutarButton;
    
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AlgoritmoGeneticoGUI algortimo = new AlgoritmoGeneticoGUI();
	}
    
    public AlgoritmoGeneticoGUI() {
    	
        setTitle("Ejemplo de GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setLayout(new GridLayout(1, 2));
		Dimension dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dimensionPantalla.width/2)-(this.getWidth()/2), (dimensionPantalla.height/4)-(this.getHeight()/4));
		this.setVisible(true);
    	initGUI();
    }
    
    public void initGUI() {
    	
    	// Crear panel para los parámetros
        JPanel paramsPanel = new JPanel();
        paramsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        paramsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Crear spinners para los parámetros
        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(100,2,10000,10));
        spinner1.setPreferredSize(new Dimension(10, 10));
        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(100,1,10000,10));
        spinner2.setPreferredSize(new Dimension(10, 10));
        JSpinner spinner3 = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        spinner3.setPreferredSize(new Dimension(10, 10));
        JSpinner spinner4 = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        spinner4.setPreferredSize(new Dimension(10, 10));
        JTextField spinner5 = new JTextField("0.001");
        spinner5.setPreferredSize(new Dimension(10, 10));
        JSpinner spinner6 = new JSpinner(new SpinnerNumberModel(2,0,100,1));
        spinner6.setPreferredSize(new Dimension(10, 10));
        JSpinner spinner7 = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        spinner7.setPreferredSize(new Dimension(10, 10));
        
        // Crear combo box para los parámetros
        String[] opciones = {"Estocastico Universal","Restos","Ruleta","Torneo Determinista","Torneo Probabilistico","Truncamiento"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setPreferredSize(new Dimension(30, 30));
        String[] opciones2 = {"Monopunto", "Uniforme", "Aritmetico","BLX-alfa"};
        JComboBox<String> comboBox2 = new JComboBox<>(opciones2);
        comboBox2.setPreferredSize(new Dimension(30, 30));
        String[] opciones3 = {"Funcion1(calibracion y prueba)","Funcion2(GrieWank)","Funcion3(Styblinski-tang)",
       		 "Funcion4a(Michalewicz)","Funcion4b(Michalewicz)"};
        JComboBox<String> comboBox3 = new JComboBox<>(opciones3);
        comboBox3.setPreferredSize(new Dimension(30, 30));
        
		// Añadir elementos al panel de parámetros
		paramsPanel.add(new JLabel("Tamaño de la población:"));
		paramsPanel.add(spinner1);
		paramsPanel.add(new JLabel("Número de generaciones:"));
		paramsPanel.add(spinner2);
		paramsPanel.add(new JLabel("Probabilidad de Cruce:"));
		paramsPanel.add(spinner3);
		paramsPanel.add(new JLabel("Probabiliad de Mutacion:"));
		paramsPanel.add(spinner4);
		paramsPanel.add(new JLabel("Precision:"));
		paramsPanel.add(spinner5);
		paramsPanel.add(new JLabel("Metodo de Seleccion:"));
		paramsPanel.add(comboBox);
		paramsPanel.add(new JLabel("Metodo de Cruce:"));
		paramsPanel.add(comboBox2);
		paramsPanel.add(new JLabel("Probabilidad de elitismo:"));
		paramsPanel.add(spinner6);
		paramsPanel.add(new JLabel("Funcion a estudiar:"));
		paramsPanel.add(comboBox3);
		paramsPanel.add(new JLabel("Dimension variables:"));
		paramsPanel.add(spinner7);
		
        add(paramsPanel);
        // Crear segundo panel vacío para la gráfica
		JPanel panelVacio = new JPanel();
		GraphicPanel grafico = new GraphicPanel();
		panelVacio.add(grafico);
		panelVacio.setPreferredSize(new Dimension(600, 600));
		panelVacio.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
		
		add(panelVacio);
		
        ejecutarButton = new JButton("Ejecutar");
        ejecutarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int tamPoblacion = (Integer)spinner1.getValue();
				int maxGeneraciones = (Integer) spinner2.getValue();
				double probCruce = (Double.valueOf((Integer)spinner3.getValue()))/100;
				double probMutacion = (Double.valueOf((Integer)spinner4.getValue()))/100;
				double precision = Double.parseDouble(spinner5.getText());
				String funcion = (String)comboBox3.getSelectedItem();
				String metodoSeleccion = (String)comboBox.getSelectedItem();
				String metodoCruce = (String)comboBox2.getSelectedItem();
				double probElitismo = (Double.valueOf((Integer)spinner6.getValue()))/100;
				int d = (Integer)spinner7.getValue();

				AlgoritmoGenetico instancia =  new AlgoritmoGenetico(tamPoblacion, maxGeneraciones, probCruce,
				  probMutacion, precision, funcion, metodoSeleccion, metodoCruce, probElitismo,d);
				instancia.run();
				List<double[]> datos = instancia.datos();
				grafico.actualizar(panelVacio,datos.get(0), datos.get(1), datos.get(2));
				panelVacio.revalidate();
			}
         });
        
        paramsPanel.add(ejecutarButton);
         setVisible(true);
    }

}
