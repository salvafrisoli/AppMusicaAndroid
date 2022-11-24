package com.example.appmusica

import com.google.gson.annotations.SerializedName

data class MusicaResponse(
    @SerializedName("full_title") var cancion: String,
    @SerializedName("artist_names") var artista: String,
    @SerializedName("song_art_image_thumbnail_url") var fotoCancion: String

)

data class ResultAPI(
    var result: MusicaResponse
)

data class Response(
    var hits: ArrayList<ResultAPI>
)
data class Inicial(
    var response: Response
)