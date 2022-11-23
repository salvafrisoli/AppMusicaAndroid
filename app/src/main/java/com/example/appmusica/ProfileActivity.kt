package com.example.appmusica

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusica.adapter.MusicaAdapter
import com.example.appmusica.adapter.Repository
import com.example.appmusica.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.start
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class ProfileActivity : AppCompatActivity(){
    private val coroutineContext: CoroutineContext = newSingleThreadContext("profile")
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var binding: ActivityProfileBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var rvMusica : RecyclerView

    private var canciones = ArrayList<ResultAPI>()
    private lateinit var adapter: MusicaAdapter

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityProfileBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        initRecyclerView()
//        binding.svMusica.setOnQueryTextListener(this)
//
//        iniciar firebase auth
//        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
//
//        //click logout user
//        binding.logoutBtn.setOnClickListener{
//            firebaseAuth.signOut()
//            checkUser()
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvMusica = findViewById<RecyclerView>(R.id.rvMusica)
        rvMusica.layoutManager = LinearLayoutManager(this)
        adapter = MusicaAdapter(canciones, this)
        rvMusica.adapter = adapter
        initRecyclerView()
        binding.svMusica.setOnQueryTextListener(this)

        //iniciar firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

//        click logout user
        binding.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }

    }
    override fun onStart() {
        super.onStart()
        start(this)
    }
    fun start(context: Context){
        scope.launch {
            canciones = Repository().fetchData(context)
            Log.d("API-DEMO", canciones.size.toString())
            Log.d("API-DEMO", canciones[1].result.cancion)
            withContext(Dispatchers.Main){
                adapter.Update(canciones)
            }
        }
    }

    private fun checkUser() {
        //obtener current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null) {
            //user not logged in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            val email = firebaseUser.email
            //binding.emailTv.text = email
        }
    }






//class ProfileActivity : AppCompatActivity(), OnQueryTextListener {

    //viewbinding
//    private lateinit var binding: ActivityProfileBinding
//    private lateinit var adapter: MusicaAdapter
//    private val musicaCanciones = mutableListOf<MusicaResponse>()
//
//    private lateinit var firebaseAuth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityProfileBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        initRecyclerView()
//        binding.svMusica.setOnQueryTextListener(this)
//
//        iniciar firebase auth
//        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
//
//        //click logout user
//        binding.logoutBtn.setOnClickListener{
//            firebaseAuth.signOut()
//            checkUser()
//        }
//    }

    //llamado al adapter
    fun initRecyclerView(){
        Log.d("ProfileActivity","RECYCLER INICIADO" )
        adapter = MusicaAdapter(canciones, this)
        binding.rvMusica.layoutManager = LinearLayoutManager(this)
        binding.rvMusica.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MusicaAdapter(MusicaProvider.musicaList)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.genius.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getSongsByName("search?q=$query")
            val canciones = call.body()
            runOnUiThread{
                if(call.isSuccessful) {
                    Log.d("ProfileActivity","SUCCESSFUL" )
                    musicaCanciones.clear()
                    val musicaC = canciones ?: emptyList()
                    musicaCanciones.addAll(musicaC)
                    adapter.notifyDataSetChanged()

                } else {
                    Log.d("ProfileActivity","EROR >:(" )
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
//    private fun checkUser() {
//        //obtener current user
//        val firebaseUser = firebaseAuth.currentUser
//        if(firebaseUser == null) {
//            //user not logged in
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//        else{
//            val email = firebaseUser.email
//            binding.emailTv.text = email
//        }
//    }