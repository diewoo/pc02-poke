package Interfaces;

import Clases.Usuario;
import Clases.Usuarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

public interface IPokemon {
    @POST("/usuarios/login")
    Call<Usuario> basicLogin(@Body Usuario usuario);
}
