package com.example.customhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customhire.databinding.ActivityRegister1Binding

class Register1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            // Retrieve user input from EditText fields
            val fname = binding.fullName.text.toString()
            val addr = binding.address.text.toString()
            val age = binding.age.text.toString()
            val gender = binding.gender.text.toString()

            if (fname.isNotEmpty() && addr.isNotEmpty() && age.isNotEmpty() && gender.isNotEmpty()) {
                // Create an intent to navigate to Register2Activity and pass the user input as intent extras
                val intent = Intent(this, Register2Activity::class.java)
                intent.putExtra("full_name", fname)
                intent.putExtra("address", addr)
                intent.putExtra("age", age)
                intent.putExtra("gender", gender)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
