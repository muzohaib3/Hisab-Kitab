package com.devon.hisaabkitaab

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.devon.hisaabkitaab.databinding.ActivitySplashBinding
import com.devon.hisaabkitaab.screens.HomeActivity

class SplashActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splash()

        supportActionBar?.hide()
    }

    private fun splash()
    {
        binding.btSplash.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

    }
}