package com.example.customhire
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customhire.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up view binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance()

        // Login button click
        binding.loginButton.setOnClickListener {
            val email = binding.username.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                // Sign in with email and password using FirebaseAuth
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, DashboardActivity::class.java))
                    } else {
                        Toast.makeText(this, "Incorrect email or password !", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signupText.setOnClickListener {
            startActivity(Intent(this, Register1Activity::class.java))
        }
    }
}
