package com.example.appmusica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.appmusica.adapter.MusicaViewHolder
import com.example.appmusica.databinding.ItemMusicaBinding
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DetalleActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val musica = intent.getParcelableExtra<ResultAPI>("musica")
        if(musica !=null){
            val cancionView : TextView = findViewById(R.id.tvDetalleCancion)
            val imageView : ImageView = findViewById(R.id.ivDetalleCancion)
            val artistaView : TextView = findViewById((R.id.tvDetalleArtista))
            val fechaView : TextView = findViewById(R.id.tvDetalleFechaSalida)
            val lyricsView : TextView = findViewById(R.id.tvDetalleLinkLyrics)
            val albumView : ImageView = findViewById(R.id.ivDetalleAlbum)

            cancionView.text = musica.result.cancion
            artistaView.text = musica.result.artista
            fechaView.text = musica.result.fechaSalida
            lyricsView.text = musica.result.lyricsLink
            //val binding = ItemMusicaBinding.bind(imageView)
            //Picasso.get().load(musica.result.fotoCancion).into(binding.ivMusica)
            Glide.with(baseContext).load(musica.result.fotoAlbum).into(albumView)
            Glide.with(baseContext).load(musica.result.fotoCancion).into(imageView)

        }
    }
}