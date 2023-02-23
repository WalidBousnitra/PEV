package Main;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel poblacionLabel, generacionesLabel, tasaCruceLabel, tasaMutacionLabel;
    private JTextField poblacionTextField, generacionesTextField, tasaCruceTextField, tasaMutacionTextField;
    private JButton ejecutarButton;

	public GraphicPanel() {
		//initGUI();
	}
	
	public void initGUI() {
		 poblacionLabel = new JLabel("Tamaño de la población:");
         poblacionTextField = new JTextField(10);
         generacionesLabel = new JLabel("Número de generaciones:");
         generacionesTextField = new JTextField(10);
         tasaCruceLabel = new JLabel("Tasa de cruce (entre 0 y 1):");
         tasaCruceTextField = new JTextField(10);
         tasaMutacionLabel = new JLabel("Tasa de mutación (entre 0 y 1):");
         tasaMutacionTextField = new JTextField(10);

         ejecutarButton = new JButton("Ejecutar algoritmo");

         setLayout(new GridLayout(5, 2));
         add(poblacionLabel);
         add(poblacionTextField);
         add(generacionesLabel);
         add(generacionesTextField);
         add(tasaCruceLabel);
         add(tasaCruceTextField);
         add(tasaMutacionLabel);
         add(tasaMutacionTextField);
         add(new JLabel()); // celda vacía para alinear botón
         add(ejecutarButton);
	}
	
}
