package entrenador;

import pokemon.Pokemon;

public class Entrenador {
    private String nameTrainer; // Cambiar a un solo nombre
    private Pokemon[] pokemons; // Atributo para los Pokémon

    public Entrenador() {
        this.pokemons = new Pokemon[3]; // Cada entrenador puede tener 3 Pokémon
    }

    public String getNameTrainer() {
        return nameTrainer;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    public void setNameTrainer(String nameTrainer) {
        this.nameTrainer = nameTrainer; 
    }
}
