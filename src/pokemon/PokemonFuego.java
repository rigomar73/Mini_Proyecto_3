package pokemon;

import pokemon.ataque.Ataque;

public class PokemonFuego extends Pokemon {
    public PokemonFuego(String namePokemon, short hp, Ataque[] ataque) {
        super(namePokemon, hp, TipoPokemon.FUEGO, ataque);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Fuego!");
    }
}