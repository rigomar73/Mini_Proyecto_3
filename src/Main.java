import entrenador.Entrenador;
import pokemon.Pokemon;
import pokemon.element.ElementPokemon;
import pokemon.batalla.Batalla;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Batalla Pokémon - Nombres");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200); // Tamaño más compacto
            frame.setLayout(new BorderLayout());

            // Panel para ingresar nombres
            JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // Espaciado entre componentes
            JTextField entrenador1Field = new JTextField();
            JTextField entrenador2Field = new JTextField();

            inputPanel.add(new JLabel("Entrenador 1:"));
            inputPanel.add(entrenador1Field);
            inputPanel.add(new JLabel("Entrenador 2:"));
            inputPanel.add(entrenador2Field);

            JButton startButton = new JButton("Asignar Pokémon");
            inputPanel.add(startButton);

            frame.add(inputPanel, BorderLayout.CENTER);

            // Acción del botón
            startButton.addActionListener(e -> {
                String name1 = entrenador1Field.getText();
                String name2 = entrenador2Field.getText();

                if (name1.isEmpty() || name2.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese los nombres de ambos entrenadores.");
                    return;
                }

                // Crear entrenadores
                Entrenador entrenador1 = new Entrenador();
                Entrenador entrenador2 = new Entrenador();
                entrenador1.setNameTrainer(name1);
                entrenador2.setNameTrainer(name2);

                // Inicializar datos de Pokémon
                ElementPokemon.initializeData();
                ArrayList<Pokemon> availablePokemons = new ArrayList<>(List.of(ElementPokemon.getPokemons()));

                // Asignar Pokémon aleatorios
                entrenador1.setPokemons(Pokemon.randomPokemon(availablePokemons));
                entrenador2.setPokemons(Pokemon.randomPokemon(availablePokemons));

                // Mostrar equipos y pasar a la batalla
                frame.dispose(); // Cerrar la ventana actual
                Batalla batalla = new Batalla();
                batalla.iniciarBatallaGUI(entrenador1, entrenador2);
            });

            frame.setVisible(true);
        });
    }
}