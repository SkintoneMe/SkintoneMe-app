package com.skintone.me.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skintone.me.MainActivity
import com.skintone.me.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivClose.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}