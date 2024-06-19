package com.skintone.me.favo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skintone.me.adapter.FavoriteAdapter
import com.skintone.me.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel = ViewModelProvider(this, FavoriteFactory.getInstance(this))[FavoriteViewModel::class.java]


        adapter = FavoriteAdapter()
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)

        favoriteViewModel.getAllFavorite().observe(this) { item ->
            adapter.submitData(lifecycle, item)
        }


//        binding.ivClose.setOnClickListener {
//            onBackPressed()
//        }

    }
}