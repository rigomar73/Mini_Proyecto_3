package modelo;

import modelo.ataque.Ataque;

public class PokemonTierra extends Pokemon {
    public PokemonTierra(String namePokemon, short hp, Ataque[] ataquesDisponibles, short valorAtaque, short defensa, short ataqueEspecial, short defensaEspecial, short velocidad, int nivel) {
        super(namePokemon, hp, TipoPokemon.TIERRA, ataquesDisponibles, valorAtaque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Tierra!");
    }
}