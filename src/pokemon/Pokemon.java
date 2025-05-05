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
    private Ataque[] ataquesDisponibles; 
    private int valorAtaque; // Valor de ataque (At)
    private short defensa; // Defensa (Df)
    private short ataqueEspecial; // Ataque Especial (AtS)
    private short defensaEspecial; // Defensa Especial (DeS)
    private short velocidad; // Velocidad
    private int nivel; // Nivel

    public Pokemon() {}

    public enum TipoAtaque {
        FISICO, ESPECIAL;
    }

    public enum TipoPokemon {
        FUEGO, AGUA, TIERRA, PLANTA;
    }

    public Pokemon(String namePokemon, short hp, TipoPokemon typePokemon, Ataque[] ataquesDisponibles, short defensa, short ataqueEspecial, short defensaEspecial, short velocidad) {
        this.namePokemon = namePokemon;
        this.hp = hp;
        this.typePokemon = typePokemon;
        this.ataquesDisponibles = ataquesDisponibles;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;

        // Asignar un nivel aleatorio entre 50 y 80
        Random random = new Random();
        this.nivel = random.nextInt(31) + 50; // Genera un número entre 50 y 80
    }

    public Pokemon(String namePokemon, short hp, TipoPokemon typePokemon, Ataque[] ataquesDisponibles, short atk, short def, short atkEsp, short defEsp, short vel, int nivel) {
        this.namePokemon = namePokemon;
        this.hp = hp;
        this.typePokemon = typePokemon;
        this.ataquesDisponibles = ataquesDisponibles;
        this.valorAtaque = atk;
        this.defensa = def;
        this.ataqueEspecial = atkEsp;
        this.defensaEspecial = defEsp;
        this.velocidad = vel;
        this.nivel = nivel;
    }

    public Pokemon(String namePokemon, short hp, TipoPokemon typePokemon, Ataque[] ataquesDisponibles) {
        this.namePokemon = namePokemon;
        this.hp = hp;
        this.typePokemon = typePokemon;
        this.ataquesDisponibles = ataquesDisponibles;

        // Asignar valores predeterminados o aleatorios a las estadísticas faltantes
        this.defensa = 50; // Valor predeterminado
        this.ataqueEspecial = 50; // Valor predeterminado
        this.defensaEspecial = 50; // Valor predeterminado
        this.velocidad = 50; // Valor predeterminado
        this.nivel = 50; // Nivel fijo
    }

    // Getters y setters para los atributos
    public short getDefensa() {
        return defensa;
    }

    public void setDefensa(short defensa) {
        this.defensa = defensa;
    }

    public short getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(short ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public short getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(short defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public short getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(short velocidad) {
        this.velocidad = velocidad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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

    public Ataque[] getAtaquesDisponibles() {
        return ataquesDisponibles;
    }

    public void setAtaquesDisponibles(Ataque[] ataquesDisponibles) {
        this.ataquesDisponibles = ataquesDisponibles;
    }

    public int getValorAtaque() {
        return valorAtaque;
    }

    public void setValorAtaque(int valorAtaque) {
        this.valorAtaque = valorAtaque;
    }

    public abstract void atacar();

    public static Pokemon[] randomPokemon(ArrayList<Pokemon> availablePokemons) {
        Random random = new Random();
        Pokemon[] equipo = new Pokemon[3];

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(availablePokemons.size());
            equipo[i] = availablePokemons.get(index);
            availablePokemons.remove(index); // Evitar duplicados
        }

        return equipo;
    }
}
