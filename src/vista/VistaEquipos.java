package vista;

import modelo.Entrenador;
import modelo.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaEquipos {
    private JFrame frame;
    private JTextArea equipoEntrenador1;
    private JTextArea equipoEntrenador2;
    private JButton iniciarBatallaButton;

    public VistaEquipos(Entrenador entrenador1, Entrenador entrenador2) {
        frame = new JFrame("Equipos Pokémon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        JPanel equiposPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        equipoEntrenador1 = new JTextArea();
        equipoEntrenador1.setEditable(false);
        equipoEntrenador1.setText("Equipo de " + entrenador1.getNameTrainer() + ":\n");
        for (Pokemon pokemon : entrenador1.getPokemons()) {
            equipoEntrenador1.append(
                pokemon.getNamePokemon() +
                " (HP: " + pokemon.getHp() +
                ", Nivel: " + pokemon.getNivel() +
                ", Tipo: " + pokemon.getTypePokemon() + ")\n"
            );
        }

        equipoEntrenador2 = new JTextArea();
        equipoEntrenador2.setEditable(false);
        equipoEntrenador2.setText("Equipo de " + entrenador2.getNameTrainer() + ":\n");
        for (Pokemon pokemon : entrenador2.getPokemons()) {
            equipoEntrenador2.append(
                pokemon.getNamePokemon() +
                " (HP: " + pokemon.getHp() +
                ", Nivel: " + pokemon.getNivel() +
                ", Tipo: " + pokemon.getTypePokemon() + ")\n"
            );
        }

        equiposPanel.add(new JScrollPane(equipoEntrenador1));
        equiposPanel.add(new JScrollPane(equipoEntrenador2));

        frame.add(equiposPanel, BorderLayout.CENTER);

        iniciarBatallaButton = new JButton("Iniciar Batalla");
        frame.add(iniciarBatallaButton, BorderLayout.SOUTH);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }

    public void agregarListenerIniciarBatalla(ActionListener listener) {
        iniciarBatallaButton.addActionListener(listener);
    }
}