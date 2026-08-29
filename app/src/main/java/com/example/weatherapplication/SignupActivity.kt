package com.example.weatherapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signupBtn = findViewById<Button>(R.id.signupBtn)

        signupBtn.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnSuccessListener {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}