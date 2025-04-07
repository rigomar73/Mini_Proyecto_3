package modelo;

import modelo.ataque.Ataque;

public class PokemonFuego extends Pokemon {
    public PokemonFuego(String namePokemon, short hp, Ataque[] ataquesDisponibles, short valorAtaque, short defensa, short ataqueEspecial, short defensaEspecial, short velocidad, int nivel) {
        super(namePokemon, hp, TipoPokemon.FUEGO, ataquesDisponibles, valorAtaque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Fuego!");
    }
}