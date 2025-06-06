package com.example.customhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customhire.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent extras
        val fullName = intent.getStringExtra("fullName")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")

        // Set the retrieved data to corresponding EditText and TextView fields
        binding.editTextTextPersonName5.setText(fullName)
        binding.editTextTextPersonName7.text = email
        binding.editTextTextPersonName9.setText(address)
        binding.editTextTextPersonName11.setText(age)
        binding.editTextTextPersonName10.setText(gender)

        binding.button4.setOnClickListener {
            val updatedFullName = binding.editTextTextPersonName5.text.toString()
            val updatedAddress = binding.editTextTextPersonName9.text.toString()
            val updatedAge = binding.editTextTextPersonName11.text.toString()
            val updatedGender = binding.editTextTextPersonName10.text.toString()

            if (updatedFullName.isNotEmpty() && updatedAddress.isNotEmpty() && updatedAge.isNotEmpty() && updatedGender.isNotEmpty()) {
                // Create a new intent to pass the updated data back to the ProfileActivity
                val profileIntent = Intent(this, ProfileActivity::class.java)
                profileIntent.putExtra("fullName", updatedFullName)
                profileIntent.putExtra("email", email)
                profileIntent.putExtra("address", updatedAddress)
                profileIntent.putExtra("age", updatedAge)
                profileIntent.putExtra("gender", updatedGender)
                startActivity(profileIntent)

                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
