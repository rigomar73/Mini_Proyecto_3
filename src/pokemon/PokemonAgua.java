package pokemon;

import pokemon.ataque.Ataque;

public class PokemonAgua extends Pokemon {
    public PokemonAgua(String namePokemon, short hp, Ataque[] ataque, short atk, short def, short atkEsp, short defEsp, short vel, int nivel) {
        super(namePokemon, hp, TipoPokemon.AGUA, ataque, atk, def, atkEsp, defEsp, vel, nivel);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Agua!");
    }
}