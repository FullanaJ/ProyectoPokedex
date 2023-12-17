package com.fullana.proyectopokedex.poqueapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiService {
    String BASE_URL = "https://pokeapi.co/api/v2/";
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") String id);
    @GET("pokemon")
    Call<PokemonList> getPokemonList(@Query("limit") int limit,
                                          @Query("offset") int offset);
    @GET("berry/{id}")
    Call<Berry>getBerryByID(@Path("id")String id);
    @GET("item/{id}")
    Call<Item>getItemByID(@Path("id")String id);
    @GET("{url}")
    Call<Item>getItemByURL(@Path("url")String url);
}
