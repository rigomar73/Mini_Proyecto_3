package pokemon;

import pokemon.ataque.Ataque;

public class PokemonTierra extends Pokemon {
    public PokemonTierra(String namePokemon, short hp, Ataque[] ataque) {
        super(namePokemon, hp, TipoPokemon.TIERRA, ataque);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Tierra!");
    }
}