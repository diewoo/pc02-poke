package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Renteria on 12/10/2016.
 */

public class ListPokemones implements Serializable {
    @SerializedName("pokemones")
    @Expose
    private List<Pokemoneslistar> pokemones = new ArrayList<Pokemoneslistar>();

    /**
     *
     * @return
     * The pokemones
     */
    public List<Pokemoneslistar> getPokemones() {
        return pokemones;
    }

    /**
     *
     * @param pokemones
     * The pokemones
     */
    public void setPokemones(List<Pokemoneslistar> pokemones) {
        this.pokemones = pokemones;
    }

}

