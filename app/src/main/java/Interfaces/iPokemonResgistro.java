package Interfaces;

import Clases.Usuarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

public interface iPokemonResgistro {
    @POST("/usuarios")
    Call<Usuarios> createUser(@Body Usuarios data);
}
