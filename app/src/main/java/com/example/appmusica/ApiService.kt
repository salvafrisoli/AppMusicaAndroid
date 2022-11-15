package com.example.appmusica

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @Headers("Accept: application/json", "Authorization: Bearer CwQ4MJW2gVIG3BaDVxrvlGs_Kz12u-1ZnbS2YueLjqv4Mkl9QjMQSnFX0FGHJYTd")
    @GET("/search")
    fun getSongsByName(@Query("cancion") cancion: String) : Call<ArrayList<MusicaResponse>>
    //suspend fun getSongsByName(@Url url:String): Call<ArrayList<MusicaResponse>>
}