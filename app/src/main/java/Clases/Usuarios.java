package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

public class Usuarios {

    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    /**
     *
     * @return
     * The usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     * The usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
