package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego Renteria on 09/10/2016.
 */

public class ResponsePokemones {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("pokemones")
    @Expose
    private Pokemon pokemones;

    public ResponsePokemones(Status status, Pokemon pokemones) {
        this.status = status;
        this.pokemones = pokemones;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Pokemon getPokemones() {
        return pokemones;
    }

    public void setPokemones(Pokemon pokemones) {
        this.pokemones = pokemones;
    }
}
