package entrenador;

import pokemon.Pokemon;
import java.util.Scanner;

public class Entrenador {
    private String nameTrainer; // Cambiar a un solo nombre
    private Pokemon[] pokemons; // Atributo para los Pokémon

    public Entrenador() {
        this.pokemons = new Pokemon[3]; // Cada entrenador puede tener 3 Pokémon
    }

    public String getNameTrainer() {
        return nameTrainer;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    public void setNameTrainer(String nameTrainer) {
        this.nameTrainer = nameTrainer; 
    }

    public Pokemon elegirPokemon(Scanner sc) {
        System.out.println("Pokémon disponibles para " + nameTrainer + ":");
        for (int i = 0; i < pokemons.length; i++) {
            System.out.println((i + 1) + ". " + pokemons[i].getNamePokemon() + " (HP: " + pokemons[i].getHp() + ", Tipo: " + pokemons[i].getTypePokemon() + ")");
        }

        int opcion;
        do {
            System.out.print("Elige un Pokémon ingresando el número correspondiente: ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                sc.next();
            }
            opcion = sc.nextInt();
            if (opcion < 1 || opcion > pokemons.length) {
                System.out.println("Opción no válida. Por favor, elige un número entre 1 y " + pokemons.length + ".");
            }
        } while (opcion < 1 || opcion > pokemons.length);

        return pokemons[opcion - 1];
    }
}
