package com.example.appmusica

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MusicaResponse(
    @SerializedName("title") var cancion: String,
    @SerializedName("artist_names") var artista: String,
    @SerializedName("song_art_image_thumbnail_url") var fotoCancion: String,
    @SerializedName("release_date_with_abbreviated_month_for_display") var fechaSalida: String,
    @SerializedName("header_image_thumbnail_url") var fotoAlbum: String,
    @SerializedName("url") var lyricsLink: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cancion)
        parcel.writeString(artista)
        parcel.writeString(fotoCancion)
        parcel.writeString(fechaSalida)
        parcel.writeString(fotoAlbum)
        parcel.writeString(lyricsLink)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MusicaResponse> {
        override fun createFromParcel(parcel: Parcel): MusicaResponse {
            return MusicaResponse(parcel)
        }

        override fun newArray(size: Int): Array<MusicaResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class ResultAPI(
    var result: MusicaResponse
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(MusicaResponse::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(result, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultAPI> {
        override fun createFromParcel(parcel: Parcel): ResultAPI {
            return ResultAPI(parcel)
        }

        override fun newArray(size: Int): Array<ResultAPI?> {
            return arrayOfNulls(size)
        }
    }
}

data class Response(
    var hits: ArrayList<ResultAPI>
)
data class Inicial(
    var response: Response
)