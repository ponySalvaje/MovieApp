package pe.edu.upc.moviesapp.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
    companion object {
        private const val BASE_URL = "http://www.omdbapi.com/"
        private lateinit var retrofit: Retrofit
    }

    fun getRetrofitInstance() : Retrofit {
        val builder = GsonBuilder()
        val gson = builder.create()

        retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}