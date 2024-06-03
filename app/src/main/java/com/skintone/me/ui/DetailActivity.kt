package com.skintone.me.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skintone.me.databinding.ActivityDetailBinding
import com.skintone.me.ui.home.FavoriteActivity

class DetailActivity : AppCompatActivity() {

    private var isEdit = false

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddFavorites.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

    }
}