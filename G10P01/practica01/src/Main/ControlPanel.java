package Main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel poblacionLabel, generacionesLabel, tasaCruceLabel, tasaMutacionLabel, precisionLabel, metodoSeleccionLabel, metodoCruceLabel, tasaElitismoLabel, funcionLabel, dimensionLabel;
    private JSpinner poblacionTextField, generacionesTextField, tasaCruceTextField, tasaMutacionTextField,precisionLabelTextField, tasaElitismoTextField, dimension;
    private JComboBox<String> seleccion,cruce, funcion;
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
         precisionLabelTextField = new JSpinner(new SpinnerNumberModel(0.001,0,1,0.001));
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
         cruce.addItem("Aritmetico");
         cruce.addItem("BLX-alfa");
         cruce.addItem("Monopunto");
         cruce.addItem("Uniforme");
         tasaElitismoLabel = new JLabel("Probabilidad de elitismo:");
         tasaElitismoTextField = new JSpinner(new SpinnerNumberModel(2,0,100,1));
         funcionLabel = new JLabel("Funcion a estudiar:");
         funcion = new JComboBox<String>();
         funcion.addItem("Funcion1(calibracion y prueba)");
         funcion.addItem("Funcion2(GrieWank)");
         funcion.addItem("Funcion3(Styblinski-tang)");
         funcion.addItem("Funcion4a(Michalewicz)");
         funcion.addItem("Funcion4b(Michalewicz)");
         dimensionLabel = new JLabel("Dimension variables:");
         dimension = new JSpinner(new SpinnerNumberModel(1,1,100,1));
         ejecutarButton = new JButton("Ejecutar algoritmo");
         ejecutarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AlgoritmoGenetico instancia =  new AlgoritmoGenetico((int)poblacionTextField.getValue(),(int) generacionesTextField.getValue(),
						(double)tasaCruceTextField.getValue()/100, (double)tasaMutacionTextField.getValue()/100, (double)precisionLabelTextField.getValue()/100,
						(String)funcion.getSelectedItem(), (String)seleccion.getSelectedItem(), (String)cruce.getSelectedItem(), (double)tasaElitismoTextField.getValue(),
						(int)dimension.getValue());
				instancia.run();
			}
         });

         setLayout(new GridLayout(5, 2));
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
         add(funcion);
         add(dimensionLabel);
         add(dimension);
         add(tasaElitismoLabel);
         add(tasaElitismoTextField);
         add(new JLabel()); // celda vacía para alinear botón
         add(ejecutarButton);
    }

}
