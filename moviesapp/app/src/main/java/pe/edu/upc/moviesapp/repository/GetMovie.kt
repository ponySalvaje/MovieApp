package pe.edu.upc.moviesapp.repository

import pe.edu.upc.moviesapp.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovie {

    @GET(".")
    fun getMovie(@Query("apiKey") apiKey: String, @Query("t") t: String)
    : Call<Movie>

    //bb7fd48c

}