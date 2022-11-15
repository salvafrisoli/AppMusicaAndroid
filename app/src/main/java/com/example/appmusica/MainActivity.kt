package com.example.appmusica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appmusica.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class MainActivity : AppCompatActivity() {

    //viewbinding

    private lateinit var binding: ActivityMainBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    //constants
    private companion object {
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configuracion del google signin
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //boton signin, accinable
        binding.googleSignInBtn.setOnClickListener {
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }

    private fun checkUser() {
        //verificar si el usuario esta logeado o no
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //el usuario esta logeado
            //iniciar activity del perfil
            startActivity(Intent(this@MainActivity,ProfileActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //signin exitoso, luego auth con firebase
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch (e: Exception){
                //signin fallido
                Log.d(TAG, "onActivityResutl: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {  authResult ->
                //login succes
                Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")

                //get loggedIn user
                val firebaseUser = firebaseAuth.currentUser
                //get user info
                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")

                //verificar si el usuario es nuevo o existe
                if (authResult.additionalUserInfo!!.isNewUser){
                    //el usuario es nuevo - cuenta creada
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account created.. \n$email")
                    Toast.makeText(this@MainActivity, "LoggedIn... \n$email", Toast.LENGTH_SHORT).show()
                }
                //start profile activity
                startActivity(Intent(this@MainActivity,ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
                Toast.makeText(this@MainActivity, "Loggin Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


}
