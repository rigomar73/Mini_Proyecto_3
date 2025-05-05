package pokemon.batalla;
import entrenador.Entrenador;
import pokemon.Pokemon;
import pokemon.ataque.Ataque;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class Batalla {

    // Método principal para iniciar la batalla
    public void iniciarBatalla(Entrenador entrenador1, Entrenador entrenador2, Scanner sc) {
        System.out.println("¡Comienza la batalla entre " + entrenador1.getNameTrainer() + " y " + entrenador2.getNameTrainer() + "!");

        int indicePokemon1 = 0;
        int indicePokemon2 = 0;

        while (indicePokemon1 < 3 && indicePokemon2 < 3) {
            Pokemon pokemon1 = entrenador1.getPokemons()[indicePokemon1];
            Pokemon pokemon2 = entrenador2.getPokemons()[indicePokemon2];

            System.out.println("\n¡" + pokemon1.getNamePokemon() + " (HP: " + pokemon1.getHp() + ") de " + entrenador1.getNameTrainer() +
                    " contra " + pokemon2.getNamePokemon() + " (HP: " + pokemon2.getHp() + ") de " + entrenador2.getNameTrainer() + "!");

            while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
                // Turno del primer entrenador
                System.out.println("\nTurno de " + entrenador1.getNameTrainer() + " (" + pokemon1.getNamePokemon() + ")");
                Ataque ataque1 = elegirAtaque(pokemon1, sc);
                realizarAtaque(pokemon1, pokemon2, ataque1);

                // Verificar si el segundo Pokémon ha sido derrotado
                if (pokemon2.getHp() <= 0) {
                    System.out.println(pokemon2.getNamePokemon() + " se ha debilitado.");
                    indicePokemon2++;
                    if (indicePokemon2 < 3) {
                        System.out.println(entrenador2.getNameTrainer() + " envía a " + entrenador2.getPokemons()[indicePokemon2].getNamePokemon() + " a la batalla.");
                    }
                    break;
                }

                // Turno del segundo entrenador
                System.out.println("\nTurno de " + entrenador2.getNameTrainer() + " (" + pokemon2.getNamePokemon() + ")");
                Ataque ataque2 = elegirAtaque(pokemon2, sc);
                realizarAtaque(pokemon2, pokemon1, ataque2);

                // Verificar si el primer Pokémon ha sido derrotado
                if (pokemon1.getHp() <= 0) {
                    System.out.println(pokemon1.getNamePokemon() + " se ha debilitado.");
                    indicePokemon1++;
                    if (indicePokemon1 < 3) {
                        System.out.println(entrenador1.getNameTrainer() + " envía a " + entrenador1.getPokemons()[indicePokemon1].getNamePokemon() + " a la batalla.");
                    }
                    break;
                }
            }
        }

        // Determinar el ganador
        if (indicePokemon1 == 3) {
            System.out.println("\n¡" + entrenador2.getNameTrainer() + " ha ganado la batalla!");
        } else {
            System.out.println("\n¡" + entrenador1.getNameTrainer() + " ha ganado la batalla!");
        }
    }

    // Método para que el jugador elija un ataque
    private Ataque elegirAtaque(Pokemon pokemon, Scanner sc) {
        System.out.println("Elige un ataque para " + pokemon.getNamePokemon() + ":");
        for (int i = 0; i < pokemon.getAtaquesDisponibles().length; i++) {
            System.out.println((i + 1) + ". " + pokemon.getAtaquesDisponibles()[i].getNameAtaque() + " (Daño: " + pokemon.getAtaquesDisponibles()[i].getDaño() + ")");
        }
        int opcion;
        do {
            System.out.print("Ingresa el número del ataque: ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                sc.next();
            }
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > pokemon.getAtaquesDisponibles().length);
        return pokemon.getAtaquesDisponibles()[opcion - 1];
    }

    // Método para realizar un ataque entre dos Pokémon
    private void realizarAtaque(Pokemon atacante, Pokemon defensor, Ataque ataque) {
        System.out.println(atacante.getNamePokemon() + " (Nivel " + atacante.getNivel() + ") usa " + ataque.getNameAtaque() + " contra " + defensor.getNamePokemon());

        // Calcular ventaja o desventaja de tipo
        double multiplicador = calcularVentaja(atacante.getTypePokemon(), defensor.getTypePokemon());

        // Determinar si el ataque es físico o especial
        int ataqueBase = (ataque.getTipoPokemon() == Pokemon.TipoAtaque.FISICO) ? atacante.getValorAtaque() : atacante.getAtaqueEspecial();
        int defensaBase = (ataque.getTipoPokemon() == Pokemon.TipoAtaque.FISICO) ? defensor.getDefensa() : defensor.getDefensaEspecial();

        // Fórmula clásica de daño
        int nivel = atacante.getNivel(); // Nivel del atacante
        int poder = ataque.getDaño(); // Poder del ataque
        int dañoBase = (((2 * nivel / 5 + 2) * poder * ataqueBase / defensaBase) / 50) + 2;

        // Aplicar multiplicador de tipo
        int dañoFinal = (int) (dañoBase * multiplicador);

        // Asegurarse de que el daño no sea negativo
        dañoFinal = Math.max(0, dañoFinal);

        // Reducir HP del defensor
        defensor.setHp((short) Math.max(0, defensor.getHp() - dañoFinal));
        System.out.println(defensor.getNamePokemon() + " recibe " + dañoFinal + " puntos de daño. Vida restante: " + defensor.getHp());
    }

    // Método para calcular la ventaja o desventaja de tipo
    private double calcularVentaja(Pokemon.TipoPokemon tipoAtacante, Pokemon.TipoPokemon tipoDefensor) {
        // Ventaja de tipos: Fuego > Planta, Planta > Agua, Agua > Fuego, Tierra > Fuego
        if ((tipoAtacante == Pokemon.TipoPokemon.FUEGO && tipoDefensor == Pokemon.TipoPokemon.PLANTA) ||
            (tipoAtacante == Pokemon.TipoPokemon.PLANTA && tipoDefensor == Pokemon.TipoPokemon.AGUA) ||
            (tipoAtacante == Pokemon.TipoPokemon.AGUA && tipoDefensor == Pokemon.TipoPokemon.FUEGO) ||
            (tipoAtacante == Pokemon.TipoPokemon.TIERRA && tipoDefensor == Pokemon.TipoPokemon.FUEGO)) {
            System.out.println("¡Es súper efectivo!");
            return 1.3; // Aumenta el daño en un 30%
        }

        // Desventaja de tipos: Fuego < Agua, Agua < Planta, Planta < Fuego, Fuego < Tierra
        if ((tipoAtacante == Pokemon.TipoPokemon.FUEGO && tipoDefensor == Pokemon.TipoPokemon.AGUA) ||
            (tipoAtacante == Pokemon.TipoPokemon.AGUA && tipoDefensor == Pokemon.TipoPokemon.PLANTA) ||
            (tipoAtacante == Pokemon.TipoPokemon.PLANTA && tipoDefensor == Pokemon.TipoPokemon.FUEGO) ||
            (tipoAtacante == Pokemon.TipoPokemon.FUEGO && tipoDefensor == Pokemon.TipoPokemon.TIERRA)) {
            System.out.println("No es muy efectivo...");
            return 0.7; // Reduce el daño en un 30%
        }

        // Sin ventaja ni desventaja
        return 1.0;
    }

    public void iniciarBatallaGUI(Entrenador entrenador1, Entrenador entrenador2) {
        JFrame frame = new JFrame("Batalla Pokémon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Etiqueta para mostrar el turno actual
        JLabel turnoLabel = new JLabel("Turno del jugador: " + entrenador1.getNameTrainer(), SwingConstants.CENTER);
        frame.add(turnoLabel, BorderLayout.NORTH);

        // Panel superior para mostrar los Pokémon en combate
        JPanel pokemonPanel = new JPanel(new GridLayout(2, 1));
        JLabel pokemon1Label = new JLabel();
        JLabel pokemon2Label = new JLabel();
        pokemonPanel.add(pokemon1Label);
        pokemonPanel.add(pokemon2Label);
        frame.add(pokemonPanel, BorderLayout.CENTER);

        // Área de registro de la batalla
        JTextArea battleLog = new JTextArea(); // Declaración de battleLog
        battleLog.setEditable(false);
        frame.add(new JScrollPane(battleLog), BorderLayout.EAST);

        // Panel inferior para elegir ataques
        JPanel actionPanel = new JPanel(new GridLayout(1, 3));
        JComboBox<String> ataqueEntrenador1 = new JComboBox<>();
        JComboBox<String> ataqueEntrenador2 = new JComboBox<>();
        JButton realizarAtaqueButton = new JButton("Realizar Ataque");
        actionPanel.add(ataqueEntrenador1);
        actionPanel.add(realizarAtaqueButton);
        actionPanel.add(ataqueEntrenador2);
        frame.add(actionPanel, BorderLayout.SOUTH);

        // Índices para los Pokémon en combate
        int[] indicePokemon1 = {0};
        int[] indicePokemon2 = {0};

        // Control de turnos
        boolean[] turnoEntrenador1 = {true}; // Comienza el entrenador 1

        // Actualizar la información de los Pokémon en combate
        Runnable actualizarPokemonInfo = () -> {
            Pokemon pokemon1 = entrenador1.getPokemons()[indicePokemon1[0]];
            Pokemon pokemon2 = entrenador2.getPokemons()[indicePokemon2[0]];

            pokemon1Label.setText("Entrenador 1: " + pokemon1.getNamePokemon() + " (HP: " + pokemon1.getHp() + ", Nivel: " + pokemon1.getNivel() + ", Tipo: " + pokemon1.getTypePokemon() + ")");
            pokemon2Label.setText("Entrenador 2: " + pokemon2.getNamePokemon() + " (HP: " + pokemon2.getHp() + ", Nivel: " + pokemon2.getNivel() + ", Tipo: " + pokemon2.getTypePokemon() + ")");

            ataqueEntrenador1.removeAllItems();
            ataqueEntrenador2.removeAllItems();

            Arrays.stream(pokemon1.getAtaquesDisponibles()).forEach(ataque -> ataqueEntrenador1.addItem(ataque.getNameAtaque()));
            Arrays.stream(pokemon2.getAtaquesDisponibles()).forEach(ataque -> ataqueEntrenador2.addItem(ataque.getNameAtaque()));

            // Habilitar/deshabilitar ataques según el turno
            ataqueEntrenador1.setEnabled(turnoEntrenador1[0]);
            ataqueEntrenador2.setEnabled(!turnoEntrenador1[0]);

            // Actualizar el mensaje del turno
            turnoLabel.setText("Turno del jugador: " + (turnoEntrenador1[0] ? entrenador1.getNameTrainer() : entrenador2.getNameTrainer()));
        };

        actualizarPokemonInfo.run();

        // Acción del botón para realizar ataques
        realizarAtaqueButton.addActionListener(e -> {
            Pokemon pokemon1 = entrenador1.getPokemons()[indicePokemon1[0]];
            Pokemon pokemon2 = entrenador2.getPokemons()[indicePokemon2[0]];

            if (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
                if (turnoEntrenador1[0]) {
                    // Turno del entrenador 1
                    Ataque ataque1 = pokemon1.getAtaquesDisponibles()[ataqueEntrenador1.getSelectedIndex()];
                    realizarAtaque(pokemon1, pokemon2, ataque1, battleLog);

                    // Verificar si el Pokémon del entrenador 2 ha sido derrotado
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
                    // Turno del entrenador 2
                    Ataque ataque2 = pokemon2.getAtaquesDisponibles()[ataqueEntrenador2.getSelectedIndex()];
                    realizarAtaque(pokemon2, pokemon1, ataque2, battleLog);

                    // Verificar si el Pokémon del entrenador 1 ha sido derrotado
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

                // Cambiar el turno
                turnoEntrenador1[0] = !turnoEntrenador1[0];
                actualizarPokemonInfo.run();
            }
        });

        frame.setVisible(true);
    }

    private void realizarAtaque(Pokemon atacante, Pokemon defensor, Ataque ataque, JTextArea battleLog) {
        battleLog.append("\n" + atacante.getNamePokemon() + " usa " + ataque.getNameAtaque() + " contra " + defensor.getNamePokemon());
        int daño = ataque.getDaño();
        defensor.setHp((short) Math.max(0, defensor.getHp() - daño));
        battleLog.append("\n" + defensor.getNamePokemon() + " recibe " + daño + " puntos de daño. Vida restante: " + defensor.getHp());
    }
}