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
        Pokemon[] pokemons = new Pokemon[3]; // Cada entrenador puede crear 3 Pokémon
        for (int i = 0; i < pokemons.length; i++) {
            System.out.println("Creando Pokémon " + (i + 1) + ":");

            // Solicitar el nombre del Pokémon
            System.out.println("Ingrese el nombre del Pokémon:");
            String name = sc.next();

            // Solicitar el HP del Pokémon con validación
            short hp;
            do {
                System.out.println("Ingrese el HP del Pokémon (entre 50 y 350):");
                while (!sc.hasNextShort()) { // Validar que la entrada sea un número corto
                    System.out.println("Entrada no válida. Por favor, ingrese un número entre 50 y 350.");
                    sc.next(); // Limpiar la entrada no válida
                }
                hp = sc.nextShort();
                if (hp < 50 || hp > 350) {
                    System.out.println("El HP debe estar entre 50 y 350. Inténtalo de nuevo.");
                }
            } while (hp < 50 || hp > 350);

            // Solicitar el tipo del Pokémon con validación
            int tipo;
            do {
                System.out.println("Seleccione el tipo del Pokémon:");
                System.out.println("1. Fuego");
                System.out.println("2. Agua");
                System.out.println("3. Tierra");
                System.out.println("4. Planta");
                while (!sc.hasNextInt()) { // Validar que la entrada sea un número entero
                    System.out.println("Entrada no válida. Por favor, ingrese un número entre 1 y 4.");
                    sc.next(); // Limpiar la entrada no válida
                }
                tipo = sc.nextInt();
                if (tipo < 1 || tipo > 4) {
                    System.out.println("El tipo debe estar entre 1 y 4. Inténtalo de nuevo.");
                }
            } while (tipo < 1 || tipo > 4);

            TipoPokemon tipoPokemon = switch (tipo) {
                case 1 -> TipoPokemon.FUEGO;
                case 2 -> TipoPokemon.AGUA;
                case 3 -> TipoPokemon.TIERRA;
                case 4 -> TipoPokemon.PLANTA;
                default -> throw new IllegalArgumentException("Tipo no válido");
            };

            // Crear ataques personalizados
            Ataque[] ataques = new Ataque[4]; // Cada Pokémon tiene 4 ataques
            for (int j = 0; j < ataques.length; j++) {
                System.out.println("Creando ataque " + (j + 1) + ":");

                // Solicitar el nombre del ataque
                System.out.println("Ingrese el nombre del ataque:");
                String ataqueName = sc.next();

                // Solicitar el tipo del ataque con validación
                int tipoAtaque;
                do {
                    System.out.println("Seleccione el tipo del ataque:");
                    System.out.println("1. Físico");
                    System.out.println("2. Especial");
                    while (!sc.hasNextInt()) { // Validar que la entrada sea un número entero
                        System.out.println("Entrada no válida. Por favor, ingrese 1 o 2.");
                        sc.next(); // Limpiar la entrada no válida
                    }
                    tipoAtaque = sc.nextInt();
                    if (tipoAtaque < 1 || tipoAtaque > 2) {
                        System.out.println("El tipo de ataque debe ser 1 o 2. Inténtalo de nuevo.");
                    }
                } while (tipoAtaque < 1 || tipoAtaque > 2);

                Pokemon.TipoAtaque tipoAtaqueEnum = switch (tipoAtaque) {
                    case 1 -> Pokemon.TipoAtaque.FISICO;
                    case 2 -> Pokemon.TipoAtaque.ESPECIAL;
                    default -> throw new IllegalArgumentException("Tipo de ataque no válido");
                };

                // Solicitar el daño del ataque con validación
                short daño;
                do {
                    System.out.println("Ingrese el daño del ataque (entre 10 y 100):");
                    while (!sc.hasNextShort()) { // Validar que la entrada sea un número corto
                        System.out.println("Entrada no válida. Por favor, ingrese un número entre 10 y 100.");
                        sc.next(); // Limpiar la entrada no válida
                    }
                    daño = sc.nextShort();
                    if (daño < 10 || daño > 100) {
                        System.out.println("El daño debe estar entre 10 y 100. Inténtalo de nuevo.");
                    }
                } while (daño < 10 || daño > 100);

                // Crear el ataque
                ataques[j] = new Ataque(ataqueName, tipoAtaqueEnum, daño);
            }

            // Crear el Pokémon con los ataques personalizados
            pokemons[i] = new Pokemon(name, hp, tipoPokemon, ataques) {
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
