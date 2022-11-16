package com.example.appmusica

import com.google.gson.annotations.SerializedName

data class MusicaResponse(
    @SerializedName("full_title") var cancion: String,
    @SerializedName("artist_names") var artista: String

)
data class Response(
    var hits: ArrayList<MusicaResponse>
)
data class Inicial(
    var response: Response
)