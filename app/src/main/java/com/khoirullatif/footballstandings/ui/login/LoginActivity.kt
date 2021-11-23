package com.khoirullatif.footballstandings.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.khoirullatif.footballstandings.databinding.ActivityLoginBinding
import com.khoirullatif.footballstandings.ui.home.HomeActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private val dummyUsername = "chairullatif"
    private val dummyPassword = "12345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSkip.setOnClickListener (this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnSkip.id -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnLogin.id -> {
                if (checkInput()) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Log.d("login", checkInput().toString())
            }
        }
    }

    private fun checkInput(): Boolean {
        val inputUsername = binding.edtUsername.text.trim().toString()
        val inputPassword = binding.edtPassword.text.trim().toString()

        if (inputUsername.isNotEmpty() && inputPassword.isNotEmpty()) {
            return if (inputUsername == dummyUsername && inputPassword == dummyPassword) {
                Toast.makeText(this, "Anda berhasil masuk", Toast.LENGTH_SHORT).show()
                true
            } else {
                if (inputUsername != dummyUsername) {
                    binding.edtUsername.error = "Username tidak ditemukan"
                }
                if (inputPassword != dummyPassword) {
                    binding.edtPassword.error = "Password salah"
                }
                false
            }
        } else {
            if (inputUsername.isEmpty()) {
                binding.edtUsername.error = "Masukkan username"
            }

            if (inputPassword.isEmpty()) {
                binding.edtPassword.error = "Masukkan password"
            }
            return false
        }
    }

}