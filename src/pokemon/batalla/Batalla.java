package pokemon.batalla;

import pokemon.Pokemon;
import pokemon.ataque.Ataque;
import java.util.Scanner;

public class Batalla {

    // Método principal para iniciar la batalla
    public void iniciarBatalla(Pokemon pokemon1, Pokemon pokemon2, Scanner sc) {
        System.out.println("¡Comienza la batalla entre " + pokemon1.getNamePokemon() + " (HP: " + pokemon1.getHp() + ") y " + pokemon2.getNamePokemon() + " (HP: " + pokemon2.getHp() + ")!");

        while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            // Determinar quién ataca primero (el Pokémon con menos HP)
            if (pokemon1.getHp() <= pokemon2.getHp()) {
                // Turno del primer Pokémon
                System.out.println("\nTurno de " + pokemon1.getNamePokemon() + " (HP: " + pokemon1.getHp() + ")");
                Ataque ataque1 = elegirAtaque(pokemon1, sc);
                realizarAtaque(pokemon1, pokemon2, ataque1);

                // Verificar si el segundo Pokémon ha sido derrotado
                if (pokemon2.getHp() <= 0) {
                    System.out.println(pokemon2.getNamePokemon() + " se ha quedado sin puntos de salud. ¡" + pokemon1.getNamePokemon() + " gana!");
                    break;
                }

                // Turno del segundo Pokémon
                System.out.println("\nTurno de " + pokemon2.getNamePokemon() + " (HP: " + pokemon2.getHp() + ")");
                Ataque ataque2 = elegirAtaque(pokemon2, sc);
                realizarAtaque(pokemon2, pokemon1, ataque2);

                // Verificar si el primer Pokémon ha sido derrotado
                if (pokemon1.getHp() <= 0) {
                    System.out.println(pokemon1.getNamePokemon() + " se ha quedado sin puntos de salud. ¡" + pokemon2.getNamePokemon() + " gana!");
                    break;
                }
            } else {
                // Turno del segundo Pokémon
                System.out.println("\nTurno de " + pokemon2.getNamePokemon() + " (HP: " + pokemon2.getHp() + ")");
                Ataque ataque2 = elegirAtaque(pokemon2, sc);
                realizarAtaque(pokemon2, pokemon1, ataque2);

                // Verificar si el primer Pokémon ha sido derrotado
                if (pokemon1.getHp() <= 0) {
                    System.out.println(pokemon1.getNamePokemon() + " se ha quedado sin puntos de salud. ¡" + pokemon2.getNamePokemon() + " gana!");
                    break;
                }

                // Turno del primer Pokémon
                System.out.println("\nTurno de " + pokemon1.getNamePokemon() + " (HP: " + pokemon1.getHp() + ")");
                Ataque ataque1 = elegirAtaque(pokemon1, sc);
                realizarAtaque(pokemon1, pokemon2, ataque1);

                // Verificar si el segundo Pokémon ha sido derrotado
                if (pokemon2.getHp() <= 0) {
                    System.out.println(pokemon2.getNamePokemon() + " se ha quedado sin puntos de salud. ¡" + pokemon1.getNamePokemon() + " gana!");
                    break;
                }
            }
        }
    }

    // Método para que el jugador elija un ataque
    private Ataque elegirAtaque(Pokemon pokemon, Scanner sc) {
        System.out.println("Elige un ataque para " + pokemon.getNamePokemon() + ":");
        for (int i = 0; i < pokemon.getAtaque().length; i++) {
            System.out.println((i + 1) + ". " + pokemon.getAtaque()[i].getNameAtaque() + " (Daño: " + pokemon.getAtaque()[i].getDaño() + ")");
        }
        int opcion;
        do {
            System.out.print("Ingresa el número del ataque: ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                sc.next();
            }
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > pokemon.getAtaque().length);
        return pokemon.getAtaque()[opcion - 1];
    }

    // Método para realizar un ataque entre dos Pokémon
    private void realizarAtaque(Pokemon atacante, Pokemon defensor, Ataque ataque) {
        System.out.println(atacante.getNamePokemon() + " usa " + ataque.getNameAtaque() + " contra " + defensor.getNamePokemon());

        // Calcular ventaja o desventaja de tipo
        double multiplicador = calcularVentaja(atacante.getTypePokemon(), defensor.getTypePokemon());
        int dañoFinal = (int) (ataque.getDaño() * multiplicador);

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
}