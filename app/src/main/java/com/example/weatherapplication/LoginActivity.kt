package com.example.weatherapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val goSignup = findViewById<TextView>(R.id.goSignup)

        loginBtn.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }

        goSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}