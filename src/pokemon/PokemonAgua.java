package pokemon;

import pokemon.ataque.Ataque;

public class PokemonAgua extends Pokemon {
    public PokemonAgua(String namePokemon, short hp, Ataque[] ataque) {
        super(namePokemon, hp, TipoPokemon.AGUA, ataque);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Agua!");
    }
}