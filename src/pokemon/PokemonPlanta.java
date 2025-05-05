package pokemon;

import pokemon.ataque.Ataque;

public class PokemonPlanta extends Pokemon {
    public PokemonPlanta(String namePokemon, short hp, Ataque[] ataque, short atk, short def, short atkEsp, short defEsp, short vel, int nivel) {
        super(namePokemon, hp, TipoPokemon.PLANTA, ataque, atk, def, atkEsp, defEsp, vel, nivel);
    }

    @Override
    public void atacar() {
        System.out.println(getNamePokemon() + " usa un ataque de tipo Planta!");
    }
}