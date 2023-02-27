package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Ventana extends JFrame {

    private static final long serialVersionUID = 1L;

    public Ventana() {
        super("Mi Ventana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setPreferredSize(new Dimension(1200, 600));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        JLabel labelTitulo = new JLabel("Título de la ventana");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        panelTitulo.add(labelTitulo);

        // Panel de texto
        JPanel panelTexto = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTexto = new GridBagConstraints();
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 0;
        gbcTexto.gridwidth = 1;
        gbcTexto.gridheight = 1;
        gbcTexto.weightx = 0.5;
        gbcTexto.fill = GridBagConstraints.HORIZONTAL;
        gbcTexto.insets = new Insets(5, 5, 5, 5);

        JLabel labelTexto = new JLabel("Texto de ejemplo:");

        panelTexto.add(labelTexto, gbcTexto);

        gbcTexto.gridx = 1;
        gbcTexto.weightx = 0.5;

        for (int i = 1; i <= 10; i++) {
            JLabel labelFila = new JLabel("Fila " + i + ": ");
            JSpinner spinnerFila = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

            gbcTexto.gridy = i;
            panelTexto.add(labelFila, gbcTexto);

            gbcTexto.gridx = 2;
            gbcTexto.weightx = 0.0;
            panelTexto.add(spinnerFila, gbcTexto);

            gbcTexto.gridx = 1;
            gbcTexto.weightx = 0.5;
        }

        gbcTexto.gridy = 11;
        gbcTexto.gridx = 0;
        gbcTexto.gridwidth = 3;
        gbcTexto.weightx = 0.0;
        gbcTexto.fill = GridBagConstraints.NONE;

        JButton boton = new JButton("Botón");
        boton.setPreferredSize(new Dimension(200, 40));
        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println("Se presionó el botón");
            }
        });

        panelTexto.add(boton, gbcTexto);

        // Panel de gráfica
        JPanel panelGrafica = new JPanel();
        panelGrafica.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelGrafica.setPreferredSize(new Dimension(700, 600));

        // Se agrega el panel de texto y el de gráfica al panel principal
        panelPrincipal.add(panelTexto, BorderLayout.WEST);
        panelPrincipal.add(panelGrafica, BorderLayout.CENTER);

        // Se agrega el panel de título al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Se agrega el panel principal a la ventana
        add(panelPrincipal);

        // Se ajusta el tamaño de la ventana
        pack();

        // Se centra la ventana en la pantalla
        setVisible(true);
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
    }
}
