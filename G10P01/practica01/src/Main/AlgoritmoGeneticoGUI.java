package Main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlgoritmoGeneticoGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public AlgoritmoGeneticoGUI() {
        super("Parámetros del algoritmo genético");
        initComponents();
    }

    private void initComponents() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		mainPanel.add(new ControlPanel(), BorderLayout.LINE_START);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		//Dimension dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation((dimensionPantalla.width/2)-(this.getWidth()/2), (dimensionPantalla.height/4)-(this.getHeight()/4));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
    }

}
