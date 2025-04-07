package pokemon.ataque;

import pokemon.Pokemon;

public class Ataque {
    String nameAtaque;
    short daño;
    Pokemon.TipoAtaque tipoPokemon;

    public Ataque(String nameAtaque, Pokemon.TipoAtaque tipoPokemon, short daño) {
        this.nameAtaque = nameAtaque;
        this.tipoPokemon = tipoPokemon;
        this.daño = daño;
    }

    enum TipoAtaque {
        FISICO, ESPECIAL;
    };

    public String getNameAtaque() {
        return nameAtaque;
    }

    public void setNameAtaque(String nameAtaque) {
        this.nameAtaque = nameAtaque;
    }

    public short getDaño() {
        return daño;
    }

    public void setDaño(short daño) {
        this.daño = daño;
    }

    public Pokemon.TipoAtaque getTipoPokemon() {
        return tipoPokemon;
    }

    public void setTipoPokemon(Pokemon.TipoAtaque tipoPokemon) {
        this.tipoPokemon = tipoPokemon;
    }
}
