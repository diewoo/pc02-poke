package Interfaces;

import java.util.List;

import Clases.Pokemoneslistar;
import Clases.Usuario;
import Clases.Usuarios;
import Clases.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

public interface IPokemon {
    @POST("/usuarios/login")
    Call<Respuesta> basicLogin(@Body Usuario usuario);
    @POST("/usuarios")
    Call<Respuesta> createUser(@Body Usuarios data);

    @GET("/usuarios/{id_usuario}/pokemones")
    Call<List<Pokemoneslistar>> getPokemones(@Query("{id_usuario}")int id);

}
