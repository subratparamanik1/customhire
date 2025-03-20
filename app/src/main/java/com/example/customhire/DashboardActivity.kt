package com.example.customhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customhire.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners for the buttons in the layout

        // Button to navigate to LoginActivity
        binding.button9.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Button to navigate to Post1Activity
        binding.button7.setOnClickListener {
            startActivity(Intent(this, Post1Activity::class.java))
        }


        // Button to navigate to ApplyJob1Activity
        binding.button.setOnClickListener {
            startActivity(Intent(this, ApplyJob1Activity::class.java))
        }

        //Navigation bar buttons
        binding.imageView3.setOnClickListener {
            Toast.makeText(this, "Already on dashboard", Toast.LENGTH_SHORT).show()
        }
        binding.pic.setOnClickListener {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
        }
        binding.imageView5.setOnClickListener {
            Toast.makeText(this, "Saved clicked", Toast.LENGTH_SHORT).show()
        }
        binding.imageView6.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}