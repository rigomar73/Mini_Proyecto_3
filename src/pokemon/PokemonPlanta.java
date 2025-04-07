package pokemon;

import pokemon.ataque.Ataque;

public class PokemonPlanta extends Pokemon {
    public PokemonPlanta(String namePokemon, short hp, Ataque[] ataque) {
        super(namePokemon, hp, TipoPokemon.PLANTA, ataque);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Planta!");
    }
}