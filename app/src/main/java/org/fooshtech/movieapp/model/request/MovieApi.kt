package org.fooshtech.movieapp.model.request



import org.fooshtech.movieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.themoviedb.org/3/"
const val END_POINT = "movie/popular"
const val END_POINT_SEARCH = "search/movie"

interface MovieApi {

    @GET(END_POINT)
    fun getMovieList(@Query("api_key") api_key: String): Call<MovieResponse>

    @GET(END_POINT_SEARCH)
    fun getSearchMovie(@Query("api_key")api_key : String , @Query("query") query :String) : Call<MovieResponse>


}


    object MovieRetrofit {

        var INSTANCE : MovieApi? = null

        fun initRetrofit(): MovieApi {
            var tempInstance = INSTANCE

            if(tempInstance !=null)
                return tempInstance

            tempInstance =   Retrofit.Builder()
                .baseUrl(BASE_URL)
               // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)

            return tempInstance

        }

}




