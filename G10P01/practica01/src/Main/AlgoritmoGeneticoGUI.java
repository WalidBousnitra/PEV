package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		mainPanel.add(new GraphicPanel(), BorderLayout.CENTER);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		Dimension dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dimensionPantalla.width/2)-(this.getWidth()/2), (dimensionPantalla.height/4)-(this.getHeight()/4));
		this.setVisible(true);
    }

}
