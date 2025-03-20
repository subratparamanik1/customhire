package com.example.customhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customhire.databinding.ActivityRegister2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth & Database
        firebaseAuth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")

        binding.button3.setOnClickListener {
            val uname = binding.editTextTextPersonName2.text.toString()
            val email = binding.editTextTextPersonName4.text.toString()
            val pass = binding.editTextTextPersonName.text.toString()
            val confirmPass = binding.editTextTextPersonName3.text.toString()

            val fullName = intent.getStringExtra("full_name")
            val address = intent.getStringExtra("address")
            val age = intent.getStringExtra("age")
            val gender = intent.getStringExtra("gender")

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    // Create user in Firebase Authentication
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val userId = firebaseAuth.currentUser?.uid

                            if (userId != null) {
                                val user = User(fullName, uname, email, address, age, gender)

                                // Store user data in Firebase Realtime Database
                                databaseRef.child(userId).setValue(user).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

                                        // Navigate to LoginActivity
                                        val profileIntent = Intent(this, LoginActivity::class.java)
                                        startActivity(profileIntent)
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Failed to store user data!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(this, "Email already exists!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
