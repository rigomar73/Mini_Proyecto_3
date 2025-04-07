package pokemon.element;

import pokemon.*;
import pokemon.ataque.Ataque;

import java.util.Scanner;

public class ElementPokemon {
    private static Ataque[] movimientosFuego;
    private static Ataque[] movimientosAgua;
    private static Ataque[] movimientosPlanta;
    private static Ataque[] movimientosTierra;
    private static Pokemon[] pokemons;

    public static void initializeMoves() {
        movimientosFuego = new Ataque[]{
                new Ataque("Lanzallamas", Pokemon.TipoAtaque.FISICO, (byte) 90),
                new Ataque("Llamarada", Pokemon.TipoAtaque.ESPECIAL, (byte) 85),
                new Ataque("Puño Fuego", Pokemon.TipoAtaque.FISICO, (byte) 75), 
                new Ataque("Brazas", Pokemon.TipoAtaque.ESPECIAL, (byte) 60)    
        };

        movimientosAgua = new Ataque[]{
                new Ataque("Hidrobomba", Pokemon.TipoAtaque.ESPECIAL, (byte) 95),
                new Ataque("Surf", Pokemon.TipoAtaque.ESPECIAL, (byte) 85),
                new Ataque("Acua Cola", Pokemon.TipoAtaque.FISICO, (byte) 90),  
                new Ataque("Agua Fría", Pokemon.TipoAtaque.FISICO, (byte) 70)   
        };

        movimientosPlanta = new Ataque[]{
                new Ataque("Hoja Afilada", Pokemon.TipoAtaque.FISICO, (byte) 80),
                new Ataque("Rayo Solar", Pokemon.TipoAtaque.ESPECIAL, (byte) 120),
                new Ataque("Drenadoras", Pokemon.TipoAtaque.ESPECIAL, (byte) 50), 
                new Ataque("Viento de Hojas", Pokemon.TipoAtaque.FISICO, (byte) 65) 
        };

        movimientosTierra = new Ataque[]{
                new Ataque("Terremoto", Pokemon.TipoAtaque.FISICO, (byte) 100),
                new Ataque("Excavar", Pokemon.TipoAtaque.FISICO, (byte) 80),
                new Ataque("Bofetón Lodo", Pokemon.TipoAtaque.ESPECIAL, (byte) 55), 
                new Ataque("Bomba Fango", Pokemon.TipoAtaque.ESPECIAL, (byte) 65)   
        };
    }

    public static void initializePokemons() {
        pokemons = new Pokemon[]{
                new PokemonFuego("Charizard", (short) 282, movimientosFuego),
                new PokemonAgua("Blastoise", (short) 292, movimientosAgua),
                new PokemonPlanta("Venusaur", (short) 300, movimientosPlanta),
                new PokemonTierra("Golem", (short) 320, movimientosTierra),
                new PokemonFuego("Typhlosion", (short) 200, movimientosFuego), 
                new PokemonPlanta("Meganium", (short) 180, movimientosPlanta),
                new PokemonAgua("Feraligatr", (short) 210, movimientosAgua)    
        };
    }

    public static void initializeData() {
        initializeMoves();
        initializePokemons();
    }

    public void createAtaques(Scanner sc, Ataque[] ataque) {
        for (int i = 0; i < ataque.length; i++) {
            System.out.println("Ingrese el nombre del ataque:");
            ataque[i].setNameAtaque(sc.nextLine());
        }
    }

    public static Pokemon[] getPokemons() {
        return pokemons;
    }
}
