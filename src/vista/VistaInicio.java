package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaInicio {
    private JFrame frame;
    private JTextField entrenador1Field;
    private JTextField entrenador2Field;
    private JButton startButton;

    public VistaInicio() {
        frame = new JFrame("Batalla Pokémon - Nombres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        entrenador1Field = new JTextField();
        entrenador2Field = new JTextField();

        inputPanel.add(new JLabel("Entrenador 1:"));
        inputPanel.add(entrenador1Field);
        inputPanel.add(new JLabel("Entrenador 2:"));
        inputPanel.add(entrenador2Field);

        startButton = new JButton("Asignar Pokémon");
        inputPanel.add(startButton);

        frame.add(inputPanel, BorderLayout.CENTER);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }

    public String getNombreEntrenador1() {
        return entrenador1Field.getText();
    }

    public String getNombreEntrenador2() {
        return entrenador2Field.getText();
    }

    public void agregarListenerBotonInicio(ActionListener listener) {
        startButton.addActionListener(listener);
    }
}