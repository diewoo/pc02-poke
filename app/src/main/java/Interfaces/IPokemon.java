package Interfaces;

import java.util.List;

import Clases.Atrapar;
import Clases.ListPokemones;
import Clases.Pokemon;
import Clases.Pokemoneslistar;
import Clases.ResponsePokemones;
import Clases.Respuesta;
import Clases.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

public interface IPokemon {
    //login
    @POST("/login")
    Call<Respuesta> login(@Body Usuario usuario);

    //registro
    @POST("/registro")
    Call<Respuesta> registrar(@Body Usuario usuario);


    @GET("/usuario/{username}/pokemones")
    Call<ResponsePokemones> getPokemones(@Path("username") String username);
    @GET("/mispokemones/{username}")
    Call<ListPokemones> getMisPokemones(@Path("username") String username);

    @GET("/pokedata/pokemones")
    Call<ListPokemones> getListPokemones();

    @GET("/pokedata/{id}")
    Call<Pokemoneslistar> getPokeRadar(@Path("id")int id);
    //agregar pokemones



    @POST("/addpoke")
    Call<Respuesta> registrarPokemon(@Body Atrapar atrapar);

}
