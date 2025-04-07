package modelo;
import java.util.ArrayList;
import java.util.Random;
import modelo.ataque.Ataque;

public abstract class Pokemon {
    private String namePokemon;
    private short hp;
    private TipoPokemon typePokemon;
    private Ataque[] ataquesDisponibles;
    private short valorAtaque;
    private short defensa;
    private short ataqueEspecial;
    private short defensaEspecial;
    private short velocidad;
    private int nivel;

    public enum TipoPokemon {
        FUEGO, AGUA, TIERRA, PLANTA;
    }

    public enum TipoAtaque {
        FISICO, ESPECIAL;
    }

    public Pokemon(String namePokemon, short hp, TipoPokemon typePokemon, Ataque[] ataquesDisponibles, short valorAtaque, short defensa, short ataqueEspecial, short defensaEspecial, short velocidad, int nivel) {
        this.namePokemon = namePokemon;
        this.hp = hp;
        this.typePokemon = typePokemon;
        this.ataquesDisponibles = ataquesDisponibles;
        this.valorAtaque = valorAtaque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.nivel = nivel;
    }

    public String getNamePokemon() {
        return namePokemon;
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

    public Ataque[] getAtaquesDisponibles() {
        return ataquesDisponibles;
    }

    public short getValorAtaque() {
        return valorAtaque;
    }

    public short getDefensa() {
        return defensa;
    }

    public short getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public short getDefensaEspecial() {
        return defensaEspecial;
    }

    public short getVelocidad() {
        return velocidad;
    }

    public int getNivel() {
        return nivel;
    }

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

    public abstract void atacar();
}