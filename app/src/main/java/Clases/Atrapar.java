package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Diego Renteria on 09/10/2016.
 */

public class Atrapar  implements Serializable{
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("pokemones")
    @Expose
    private Pokemoneslistar pokemones;

    public Atrapar(String username, Pokemoneslistar pokemones) {
        this.username = username;
        this.pokemones = pokemones;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Pokemoneslistar getPokemon() {
        return pokemones;
    }

    public void setPokemon(Pokemoneslistar pokemon) {
        this.pokemones = pokemon;
    }
}
