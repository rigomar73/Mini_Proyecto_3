import entrenador.Entrenador;
import pokemon.Pokemon;
import pokemon.element.ElementPokemon;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import pokemon.batalla.Batalla;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear entrenadores
        Entrenador entrenador1 = new Entrenador();
        Entrenador entrenador2 = new Entrenador();

        // Pedir nombres de los entrenadores
        System.out.println("Ingrese el nombre del primer entrenador:");
        String name1 = sc.nextLine();
        entrenador1.setNameTrainer(name1);

        System.out.println("Ingrese el nombre del segundo entrenador:");
        String name2 = sc.nextLine();
        entrenador2.setNameTrainer(name2);

        ElementPokemon.initializeData();
        ArrayList<Pokemon> availablePokemons = new ArrayList<>(List.of(ElementPokemon.getPokemons()));

        // Menú para el primer entrenador
        System.out.println("\n" + entrenador1.getNameTrainer() + ", ¿cómo quieres asignar tus Pokémon?");
        System.out.println("1. Crear tus propios Pokémon");
        System.out.println("2. Obtener Pokémon aleatorios");
        int opcion1 = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer del Scanner

        switch (opcion1) {
            case 1 -> {
                System.out.println("Creando Pokémon para " + entrenador1.getNameTrainer() + "...");
                entrenador1.setPokemons(Pokemon.createPokemon(sc));
            }
            case 2 -> {
                System.out.println("Asignando Pokémon aleatorios a " + entrenador1.getNameTrainer() + "...");
                entrenador1.setPokemons(Pokemon.randomPokemon(availablePokemons));
            }
            default -> System.out.println("Opción no válida. Se asignarán Pokémon aleatorios por defecto.");
        }

        // Menú para el segundo entrenador
        System.out.println("\n" + entrenador2.getNameTrainer() + ", ¿cómo quieres asignar tus Pokémon?");
        System.out.println("1. Crear tus propios Pokémon");
        System.out.println("2. Obtener Pokémon aleatorios");
        int opcion2 = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer del Scanner

        switch (opcion2) {
            case 1 -> {
                System.out.println("Creando Pokémon para " + entrenador2.getNameTrainer() + "...");
                entrenador2.setPokemons(Pokemon.createPokemon(sc));
            }
            case 2 -> {
                System.out.println("Asignando Pokémon aleatorios a " + entrenador2.getNameTrainer() + "...");
                entrenador2.setPokemons(Pokemon.randomPokemon(availablePokemons));
            }
            default -> System.out.println("Opción no válida. Se asignarán Pokémon aleatorios por defecto.");
        }

        // Mostrar los Pokémon asignados a cada entrenador
        System.out.println("\nPokémon del primer entrenador (" + entrenador1.getNameTrainer() + "):");
        for (Pokemon pokemon : entrenador1.getPokemons()) {
            System.out.println("- " + pokemon.getNamePokemon() + " (Tipo: " + pokemon.getTypePokemon() + ")");
        }

        System.out.println("\nPokémon del segundo entrenador (" + entrenador2.getNameTrainer() + "):");
        for (Pokemon pokemon : entrenador2.getPokemons()) {
            System.out.println("- " + pokemon.getNamePokemon() + " (Tipo: " + pokemon.getTypePokemon() + ")");
        }

        // Elegir Pokémon para la batalla
        System.out.println("\n" + entrenador1.getNameTrainer() + ", elige tu Pokémon para la batalla:");
        Pokemon pokemon1 = entrenador1.elegirPokemon(sc);

        System.out.println("\n" + entrenador2.getNameTrainer() + ", elige tu Pokémon para la batalla:");
        Pokemon pokemon2 = entrenador2.elegirPokemon(sc);

        // Iniciar la batalla
        System.out.println("\n¡Batalla Pokémon lista para comenzar!");
        Batalla batalla = new Batalla();
        batalla.iniciarBatalla(pokemon1, pokemon2, sc);
    }
}