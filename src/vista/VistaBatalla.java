package vista;

import modelo.Entrenador;
import modelo.Pokemon;
import modelo.ataque.Ataque;
import modelo.batalla.Batalla;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class VistaBatalla {
    private JFrame frame;
    private JLabel turnoLabel;
    private JLabel pokemon1Label;
    private JLabel pokemon2Label;
    private JTextArea battleLog;
    private JComboBox<String> ataqueEntrenador1;
    private JComboBox<String> ataqueEntrenador2;
    private JButton realizarAtaqueButton;

    private int[] indicePokemon1 = {0};
    private int[] indicePokemon2 = {0};
    private boolean[] turnoEntrenador1 = {true};

    private final Entrenador entrenador1;
    private final Entrenador entrenador2;
    private final Batalla batalla;

    public VistaBatalla(Entrenador entrenador1, Entrenador entrenador2, Batalla batalla) {
        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;
        this.batalla = batalla;
        inicializarGUI();
    }

    private void inicializarGUI() {
        frame = new JFrame("Batalla Pokémon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        turnoLabel = new JLabel("Turno del jugador: " + entrenador1.getNameTrainer(), SwingConstants.CENTER);
        frame.add(turnoLabel, BorderLayout.NORTH);

        JPanel pokemonPanel = new JPanel(new GridLayout(2, 1));
        pokemon1Label = new JLabel();
        pokemon2Label = new JLabel();
        pokemonPanel.add(pokemon1Label);
        pokemonPanel.add(pokemon2Label);
        frame.add(pokemonPanel, BorderLayout.CENTER);

        battleLog = new JTextArea();
        battleLog.setEditable(false);
        frame.add(new JScrollPane(battleLog), BorderLayout.EAST);

        JPanel actionPanel = new JPanel(new GridLayout(1, 3));
        ataqueEntrenador1 = new JComboBox<>();
        ataqueEntrenador2 = new JComboBox<>();
        realizarAtaqueButton = new JButton("Realizar Ataque");
        actionPanel.add(ataqueEntrenador1);
        actionPanel.add(realizarAtaqueButton);
        actionPanel.add(ataqueEntrenador2);
        frame.add(actionPanel, BorderLayout.SOUTH);

        actualizarPokemonInfo();

        realizarAtaqueButton.addActionListener(e -> manejarTurno());

        frame.setVisible(true);
    }

    private void actualizarPokemonInfo() {
        Pokemon pokemon1 = entrenador1.getPokemons()[indicePokemon1[0]];
        Pokemon pokemon2 = entrenador2.getPokemons()[indicePokemon2[0]];

        pokemon1Label.setText("Entrenador 1: " + pokemon1.getNamePokemon() + " (HP: " + pokemon1.getHp() + ", Nivel: " + pokemon1.getNivel() + ", Tipo: " + pokemon1.getTypePokemon() + ")");
        pokemon2Label.setText("Entrenador 2: " + pokemon2.getNamePokemon() + " (HP: " + pokemon2.getHp() + ", Nivel: " + pokemon2.getNivel() + ", Tipo: " + pokemon2.getTypePokemon() + ")");

        ataqueEntrenador1.removeAllItems();
        ataqueEntrenador2.removeAllItems();

        Arrays.stream(pokemon1.getAtaquesDisponibles()).forEach(ataque -> ataqueEntrenador1.addItem(ataque.getNameAtaque()));
        Arrays.stream(pokemon2.getAtaquesDisponibles()).forEach(ataque -> ataqueEntrenador2.addItem(ataque.getNameAtaque()));

        ataqueEntrenador1.setEnabled(turnoEntrenador1[0]);
        ataqueEntrenador2.setEnabled(!turnoEntrenador1[0]);

        turnoLabel.setText("Turno del jugador: " + (turnoEntrenador1[0] ? entrenador1.getNameTrainer() : entrenador2.getNameTrainer()));
    }

    private void manejarTurno() {
        Pokemon pokemon1 = entrenador1.getPokemons()[indicePokemon1[0]];
        Pokemon pokemon2 = entrenador2.getPokemons()[indicePokemon2[0]];

        if (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            if (turnoEntrenador1[0]) {
                Ataque ataque1 = pokemon1.getAtaquesDisponibles()[ataqueEntrenador1.getSelectedIndex()];
                batalla.realizarAtaque(pokemon1, pokemon2, ataque1, battleLog);

                if (pokemon2.getHp() <= 0) {
                    battleLog.append("\n" + pokemon2.getNamePokemon() + " se ha debilitado.");
                    indicePokemon2[0]++;
                    if (indicePokemon2[0] >= 3) {
                        battleLog.append("\n¡" + entrenador1.getNameTrainer() + " ha ganado la batalla!");
                        realizarAtaqueButton.setEnabled(false);
                        return;
                    } else {
                        Pokemon siguientePokemon = entrenador2.getPokemons()[indicePokemon2[0]];
                        battleLog.append("\n" + entrenador2.getNameTrainer() + " envía a " + siguientePokemon.getNamePokemon() + " a la batalla.");
                    }
                }
            } else {
                Ataque ataque2 = pokemon2.getAtaquesDisponibles()[ataqueEntrenador2.getSelectedIndex()];
                batalla.realizarAtaque(pokemon2, pokemon1, ataque2, battleLog);

                if (pokemon1.getHp() <= 0) {
                    battleLog.append("\n" + pokemon1.getNamePokemon() + " se ha debilitado.");
                    indicePokemon1[0]++;
                    if (indicePokemon1[0] >= 3) {
                        battleLog.append("\n¡" + entrenador2.getNameTrainer() + " ha ganado la batalla!");
                        realizarAtaqueButton.setEnabled(false);
                        return;
                    } else {
                        Pokemon siguientePokemon = entrenador1.getPokemons()[indicePokemon1[0]];
                        battleLog.append("\n" + entrenador1.getNameTrainer() + " envía a " + siguientePokemon.getNamePokemon() + " a la batalla.");
                    }
                }
            }
            turnoEntrenador1[0] = !turnoEntrenador1[0];
            actualizarPokemonInfo();
        }
    }
}