package com.example.appmusica

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @Headers("Authorization: Bearer CwQ4MJW2gVIG3BaDVxrvlGs_Kz12u-1ZnbS2YueLjqv4Mkl9QjMQSnFX0FGHJYTd")
    @GET("/search")
    fun getSongsByName(@Query("q") cancion: String) : Call<Inicial>
    //suspend fun getSongsByName(@Url url:String): Call<ArrayList<MusicaResponse>>
}