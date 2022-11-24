package com.example.appmusica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.appmusica.databinding.ItemMusicaBinding
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DetalleActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val musica = intent.getParcelableExtra<ResultAPI>("musica")
        if(musica !=null){
            val textView : TextView = findViewById(R.id.tvDetalleCancion)
            val imageView : ImageView = findViewById(R.id.ivDetalleCancion)

            textView.text = musica.result.cancion
//            val binding = ItemMusicaBinding.bind(imageView)
            Picasso.get().load(musica.result.fotoCancion).into(ItemMusicaBinding.bind(imageView).ivMusica)
        }
    }
}