package com.example.customhire

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.customhire.databinding.ActivityRegister1Binding

class Register1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gender.setOnClickListener {
            showGenderDialog()
        }

        binding.signupButton.setOnClickListener {
            val fname = binding.fullName.text.toString()
            val addr = binding.address.text.toString()
            val age = binding.age.text.toString()
            val gender = binding.gender.text.toString()

            if (fname.isNotEmpty() && addr.isNotEmpty() && age.isNotEmpty() && gender.isNotEmpty()) {
                val intent = Intent(this, Register2Activity::class.java)
                intent.putExtra("full_name", fname)
                intent.putExtra("address", addr)
                intent.putExtra("age", age)
                intent.putExtra("gender", gender)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showGenderDialog() {
        val genders = arrayOf("Male", "Female", "Other")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Gender")
        builder.setSingleChoiceItems(genders, -1) { dialog, which ->
            binding.gender.text = genders[which] // Set selected gender
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
}
