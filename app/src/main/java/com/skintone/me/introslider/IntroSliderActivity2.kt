package com.skintone.me.introslider

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.skintone.me.R
import com.skintone.me.databinding.ActivityIntroSlider2Binding

class IntroSliderActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityIntroSlider2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroSlider2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNextLogin.setOnClickListener {
            startActivity(Intent(this, IntroSliderActivity3::class.java))
        }

    }
}