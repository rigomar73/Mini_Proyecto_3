package pokemon;
import entrenador.Entrenador;
import pokemon.ataque.Ataque;
import pokemon.element.ElementPokemon;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public abstract class Pokemon {
    private String namePokemon;
    private short hp;
    private TipoPokemon typePokemon;
    private Ataque[] ataque;

    Pokemon[] pokemons = new Pokemon[3];
    ElementPokemon element = new ElementPokemon();

    public Pokemon() {

    }

    public enum TipoAtaque {
        FISICO, ESPECIAL ;
    };

    public enum TipoPokemon{
        FUEGO, AGUA, TIERRA, PLANTA;
    }

    public Pokemon(String namePokemon, short hp, TipoPokemon typePokemon, Ataque[] ataque) {
        this.namePokemon = namePokemon;
        this.hp = hp;
        this.typePokemon = typePokemon;
        this.ataque = ataque;
    }

    public String getNamePokemon() {
        return namePokemon;
    }

    public void setNamePokemon(String namePokemon) {
        this.namePokemon = namePokemon;
    }

    public short getHp() {
        return hp;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public TipoPokemon getTypePokemon() {
        return typePokemon;
    }

    public void setTypePokemon(TipoPokemon typePokemon) {
        this.typePokemon = typePokemon;
    }

    public Ataque[] getAtaque() {
        return ataque;
    }

    public void setAtaque(Ataque[] ataque) {
        this.ataque = ataque;
    }

    public abstract void atacar(); 

    public void menuPokemon(Scanner sc, Entrenador entrenador) {
        System.out.println("¿Cómo quieres jugar?: \n1. Pokémon aleatorios\n2. Crear tus propios Pokémon");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1 -> randomPokemon(entrenador);
            case 2 -> createPokemon(sc);
            default -> System.out.println("Opción no válida.");
        }
    }

    public String randomTipoPokemon(){
        Random rand = new Random();
            return (TipoAtaque.values()[rand.nextInt(1,2)]).name();
    }

    public void randomPokemon(Entrenador entrenador) {
        Random rand = new Random();
        ArrayList<Pokemon> availablePokemons = new ArrayList<>(Arrays.asList(ElementPokemon.getPokemons()));
        Pokemon[] assignedPokemons = new Pokemon[3]; // Cada entrenador recibe 3 Pokémon

        for (int i = 0; i < assignedPokemons.length; i++) {
            int randomIndex = rand.nextInt(availablePokemons.size());
            assignedPokemons[i] = availablePokemons.remove(randomIndex); // Elimina el Pokémon seleccionado para evitar repeticiones
        }

        entrenador.setPokemons(assignedPokemons);

        System.out.println("Pokémon asignados al " + entrenador.getNameTrainer() + ":");
        for (Pokemon pokemon : assignedPokemons) {
            System.out.println("- " + pokemon.getNamePokemon() + " (Tipo: " + pokemon.getTypePokemon() + ")");
        }
    }

    // Método para crear Pokémon manualmente
    public static Pokemon[] createPokemon(Scanner sc) {
        Pokemon[] pokemons = new Pokemon[3];
        for (int i = 0; i < pokemons.length; i++) {
            System.out.println("Creando Pokémon " + (i + 1) + ":");
            System.out.println("Ingrese el nombre del Pokémon:");
            String name = sc.next();

            System.out.println("Ingrese el HP del Pokémon (entre 50 y 350):");
            short hp = sc.nextShort();

            System.out.println("Seleccione el tipo del Pokémon:");
            System.out.println("1. Fuego");
            System.out.println("2. Agua");
            System.out.println("3. Tierra");
            System.out.println("4. Planta");
            int tipo = sc.nextInt();

            TipoPokemon tipoPokemon = switch (tipo) {
                case 1 -> TipoPokemon.FUEGO;
                case 2 -> TipoPokemon.AGUA;
                case 3 -> TipoPokemon.TIERRA;
                case 4 -> TipoPokemon.PLANTA;
                default -> throw new IllegalArgumentException("Tipo no válido");
            };

            pokemons[i] = new Pokemon(name, hp, tipoPokemon, null) {
                @Override
                public void atacar() {
                    System.out.println(getNamePokemon() + " ataca con un movimiento de tipo " + getTypePokemon() + "!");
                }
            };
        }
        return pokemons; 
    }

    // Método para asignar Pokémon aleatorios
    public static Pokemon[] randomPokemon(ArrayList<Pokemon> availablePokemons) {
        Random rand = new Random();
        Pokemon[] assignedPokemons = new Pokemon[3]; // Cada entrenador recibe 3 Pokémon

        for (int i = 0; i < assignedPokemons.length; i++) {
            int randomIndex = rand.nextInt(availablePokemons.size());
            assignedPokemons[i] = availablePokemons.remove(randomIndex); // Elimina el Pokémon seleccionado para evitar repeticiones
        }

        return assignedPokemons; 
    }
}
