package com.example.customhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customhire.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth and Database reference
        mAuth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")

        val userId = mAuth.currentUser?.uid

        if (userId != null) {
            loadUserData(userId)
        }

        binding.button6.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("fullName", binding.editTextTextPersonName5.text.toString())
            intent.putExtra("email", binding.editTextTextPersonName7.text.toString())
            intent.putExtra("address", binding.editTextTextPersonName9.text.toString())
            intent.putExtra("age", binding.editTextTextPersonName11.text.toString())
            intent.putExtra("gender", binding.editTextTextPersonName10.text.toString())
            startActivity(intent)
        }

        binding.button5.setOnClickListener {
            deleteUserAccount()
        }
    }

    private fun loadUserData(userId: String) {
        databaseRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val fullName = snapshot.child("fullName").getValue(String::class.java) ?: ""
                    val email = snapshot.child("email").getValue(String::class.java) ?: ""
                    val address = snapshot.child("address").getValue(String::class.java) ?: ""
                    val age = snapshot.child("age").getValue(String::class.java) ?: ""
                    val gender = snapshot.child("gender").getValue(String::class.java) ?: ""

                    // Set data to TextViews
                    binding.editTextTextPersonName5.text = fullName
                    binding.editTextTextPersonName7.text = email
                    binding.editTextTextPersonName9.text = address
                    binding.editTextTextPersonName11.text = age
                    binding.editTextTextPersonName10.text = gender
                } else {
                    Toast.makeText(this@ProfileActivity, "User data not found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "Failed to load data!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteUserAccount() {
        val user = mAuth.currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                databaseRef.child(user.uid).removeValue().addOnCompleteListener { deleteTask ->
                    if (deleteTask.isSuccessful) {
                        Toast.makeText(this, "User account deleted successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to delete user data!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Failed to delete user account!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
