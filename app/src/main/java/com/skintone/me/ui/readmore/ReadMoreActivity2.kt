package com.skintone.me.ui.readmore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.skintone.me.R
import com.skintone.me.adapter.SliderAdapter
import com.skintone.me.databinding.ActivityReadMore2Binding
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class ReadMoreActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityReadMore2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadMore2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager2)
        val dotsIndicator = findViewById<SpringDotsIndicator>(R.id.dotsIndicator2)

        val imageList = listOf(
            R.drawable.story4,
            R.drawable.story5,
            R.drawable.story6,
        )
        val adapter = SliderAdapter(imageList)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

    }
}