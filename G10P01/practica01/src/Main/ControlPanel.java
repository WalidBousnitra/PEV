package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel poblacionLabel, generacionesLabel, tasaCruceLabel, tasaMutacionLabel, precisionLabel, metodoSeleccionLabel, metodoCruceLabel, tasaElitismoLabel, funcionLabel, dimensionLabel;
    private JSpinner poblacionTextField, generacionesTextField, tasaCruceTextField, tasaMutacionTextField, tasaElitismoTextField, dimension;
    private JComboBox<String> seleccion,cruce, funcionspinner;
    private JTextField precisionLabelTextField;
    private JButton ejecutarButton;
    
    public ControlPanel() {
    	initGUI();
    }
    
    public void initGUI() {
    	 poblacionLabel = new JLabel("Tamaño de la población:");
         poblacionTextField = new JSpinner(new SpinnerNumberModel(100,2,10000,10));
         generacionesLabel = new JLabel("Número de generaciones:");
         generacionesTextField = new JSpinner(new SpinnerNumberModel(100,1,10000,10));
         tasaCruceLabel = new JLabel("Probabilidad de Cruce:");
         tasaCruceTextField = new JSpinner(new SpinnerNumberModel(10,0,100,1));
         tasaMutacionLabel = new JLabel("Probabiliad de Mutacion:");
         tasaMutacionTextField = new JSpinner(new SpinnerNumberModel(10,0,100,1));
         precisionLabel = new JLabel("Precision:");
         precisionLabelTextField = new JTextField("0.001");
         metodoSeleccionLabel = new JLabel("Metodo de Seleccion:");
         seleccion = new JComboBox<String>();
         seleccion.addItem("Estocastico Universal");
         seleccion.addItem("Restos");
         seleccion.addItem("Ruleta");
         seleccion.addItem("Torneo Determinista");
         seleccion.addItem("Torneo Probabilistico");
         seleccion.addItem("Truncamiento");
         metodoCruceLabel = new JLabel("Metodo de Cruce:");
         cruce = new JComboBox<String>();
         cruce.addItem("Monopunto");
         cruce.addItem("Uniforme");
         cruce.addItem("Aritmetico");
         cruce.addItem("BLX-alfa");
         tasaElitismoLabel = new JLabel("Probabilidad de elitismo:");
         tasaElitismoTextField = new JSpinner(new SpinnerNumberModel(2,0,100,1));
         funcionLabel = new JLabel("Funcion a estudiar:");
         funcionspinner = new JComboBox<String>();
         funcionspinner.addItem("Funcion1(calibracion y prueba)");
         funcionspinner.addItem("Funcion2(GrieWank)");
         funcionspinner.addItem("Funcion3(Styblinski-tang)");
         funcionspinner.addItem("Funcion4a(Michalewicz)");
         funcionspinner.addItem("Funcion4b(Michalewicz)");
         dimensionLabel = new JLabel("Dimension variables:");
         dimension = new JSpinner(new SpinnerNumberModel(1,1,100,1));
         ejecutarButton = new JButton("Ejecutar algoritmo");
         ejecutarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int tamPoblacion = (Integer)poblacionTextField.getValue();
				int maxGeneraciones = (Integer) generacionesTextField.getValue();
				double probCruce = (Double.valueOf((Integer)tasaCruceTextField.getValue()))/100;
				double probMutacion = (Double.valueOf((Integer)tasaMutacionTextField.getValue()))/100;
				double precision = Double.parseDouble(precisionLabelTextField.getText());
				String funcion = (String)funcionspinner.getSelectedItem();
				String metodoSeleccion = (String)seleccion.getSelectedItem();
				String metodoCruce = (String)cruce.getSelectedItem();
				double probElitismo = (Double.valueOf((Integer)tasaElitismoTextField.getValue()))/100;
				int d = (Integer)dimension.getValue();

				AlgoritmoGenetico instancia =  new AlgoritmoGenetico(tamPoblacion, maxGeneraciones, probCruce,
				  probMutacion, precision, funcion, metodoSeleccion, metodoCruce, probElitismo,d);
				instancia.run();
				List<double[]> datos = instancia.datos();
				new GraphicPanel(datos.get(0),datos.get(1),datos.get(2));
			}
         });
         
         setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
         add(poblacionLabel);
         add(poblacionTextField);
         add(generacionesLabel);
         add(generacionesTextField);
         add(tasaCruceLabel);
         add(tasaCruceTextField);
         add(tasaMutacionLabel);
         add(tasaMutacionTextField);
         add(precisionLabel);
         add(precisionLabelTextField);
         add(metodoSeleccionLabel);
         add(seleccion);
         add(metodoCruceLabel);
         add(cruce);
         add(funcionLabel);
         add(funcionspinner);
         add(dimensionLabel);
         add(dimension);
         add(tasaElitismoLabel);
         add(tasaElitismoTextField);
         add(new JLabel()); // celda vacía para alinear botón
         add(ejecutarButton);
    }

}
